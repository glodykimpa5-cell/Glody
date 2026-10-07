package com.example.data.repository

import com.example.data.datasource.EducationalLessonsData
import com.example.data.datasource.EducationalQuizData
import com.example.data.datasource.EducationalSubjectsData
import com.example.data.local.LearningDao
import com.example.data.local.LessonProgressEntity
import com.example.data.local.QuizResultEntity
import com.example.data.model.Lesson
import com.example.data.model.QuizLevel
import com.example.data.model.QuizQuestion
import com.example.data.model.Subject
import com.example.data.model.UserBadge
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class LearningRepository(
    private val learningDao: LearningDao
) {
    // Subjects
    fun getAllSubjects(): List<Subject> = EducationalSubjectsData.subjects

    fun getSubjectById(subjectId: String): Subject? =
        EducationalSubjectsData.subjects.find { it.id == subjectId }

    // Lessons
    fun getAllLessons(): List<Lesson> = EducationalLessonsData.lessons

    fun getLessonsForSubject(subjectId: String): List<Lesson> =
        EducationalLessonsData.lessons.filter { it.subjectId == subjectId }

    fun getLessonById(lessonId: String): Lesson? =
        EducationalLessonsData.lessons.find { it.id == lessonId }

    // Search
    fun searchAll(query: String): SearchResults {
        val q = query.trim().lowercase()
        if (q.isEmpty()) return SearchResults()

        val matchedLessons = EducationalLessonsData.lessons.filter {
            it.title.lowercase().contains(q) ||
            it.presentation.lowercase().contains(q) ||
            it.summary.lowercase().contains(q)
        }

        val matchedDefinitions = EducationalLessonsData.lessons.flatMap { lesson ->
            lesson.definitions.filter { it.term.lowercase().contains(q) || it.definition.lowercase().contains(q) }
                .map { DefinitionMatch(lesson, it.term, it.definition) }
        }

        val matchedQuizzes = EducationalQuizData.questions.filter {
            it.question.lowercase().contains(q) ||
            it.explanation.lowercase().contains(q)
        }

        val matchedSubjects = EducationalSubjectsData.subjects.filter {
            it.title.lowercase().contains(q) ||
            it.description.lowercase().contains(q)
        }

        return SearchResults(
            subjects = matchedSubjects,
            lessons = matchedLessons,
            definitions = matchedDefinitions,
            quizzes = matchedQuizzes
        )
    }

    // Local Progress and Favorites
    fun getAllProgress(): Flow<List<LessonProgressEntity>> = learningDao.getAllProgress()

    fun getProgressForLesson(lessonId: String): Flow<LessonProgressEntity?> =
        learningDao.getProgressForLesson(lessonId)

    fun getFavoriteLessons(): Flow<List<Lesson>> =
        learningDao.getFavoriteLessons().map { entities ->
            val favIds = entities.map { it.lessonId }.toSet()
            EducationalLessonsData.lessons.filter { it.id in favIds }
        }

    suspend fun toggleFavorite(lessonId: String, currentFav: Boolean, subjectId: String) {
        learningDao.insertOrUpdateProgress(
            LessonProgressEntity(
                lessonId = lessonId,
                subjectId = subjectId,
                isFavorite = !currentFav,
                lastAccessedTimestamp = System.currentTimeMillis()
            )
        )
    }

    suspend fun toggleLessonCompleted(lessonId: String, currentCompleted: Boolean, subjectId: String) {
        learningDao.insertOrUpdateProgress(
            LessonProgressEntity(
                lessonId = lessonId,
                subjectId = subjectId,
                isCompleted = !currentCompleted,
                lastAccessedTimestamp = System.currentTimeMillis()
            )
        )
    }

    // Quizzes
    fun getQuizzesForSubject(subjectId: String, level: QuizLevel? = null): List<QuizQuestion> {
        return EducationalQuizData.questions.filter {
            it.subjectId == subjectId && (level == null || it.level == level)
        }
    }

    fun getAllQuizzes(level: QuizLevel? = null): List<QuizQuestion> {
        return if (level == null) {
            EducationalQuizData.questions
        } else {
            EducationalQuizData.questions.filter { it.level == level }
        }
    }

    fun generateMockExam(subjectId: String?, count: Int): List<QuizQuestion> {
        val pool = if (subjectId.isNullOrEmpty() || subjectId == "all") {
            EducationalQuizData.questions
        } else {
            EducationalQuizData.questions.filter { it.subjectId == subjectId }
        }
        return pool.shuffled().take(count)
    }

    suspend fun recordQuizResult(
        subjectId: String,
        totalQuestions: Int,
        correctAnswers: Int,
        isMockExam: Boolean
    ) {
        val percentage = if (totalQuestions > 0) (correctAnswers * 100) / totalQuestions else 0
        learningDao.insertQuizResult(
            QuizResultEntity(
                subjectId = subjectId,
                totalQuestions = totalQuestions,
                correctAnswers = correctAnswers,
                scorePercentage = percentage,
                isMockExam = isMockExam,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    fun getAllQuizResults(): Flow<List<QuizResultEntity>> = learningDao.getAllQuizResults()

    // Badges calculation
    fun getBadges(): Flow<List<UserBadge>> {
        return combine(
            learningDao.getAllProgress(),
            learningDao.getAllQuizResults()
        ) { progressList, quizResults ->
            val completedCount = progressList.count { it.isCompleted }
            val quizCount = quizResults.size
            val avgScore = if (quizCount > 0) quizResults.map { it.scorePercentage }.average() else 0.0

            listOf(
                UserBadge(
                    id = "badge_first_course",
                    title = "Premier cours",
                    description = "Avoir terminé son tout premier cours d'apprentissage",
                    iconEmoji = "🏆",
                    isUnlocked = completedCount >= 1,
                    progressPercent = (completedCount.toFloat() / 1f).coerceIn(0f, 1f)
                ),
                UserBadge(
                    id = "badge_5_courses",
                    title = "5 cours terminés",
                    description = "Avoir validé au moins 5 modules ou cours complets",
                    iconEmoji = "📚",
                    isUnlocked = completedCount >= 5,
                    progressPercent = (completedCount.toFloat() / 5f).coerceIn(0f, 1f)
                ),
                UserBadge(
                    id = "badge_10_quizzes",
                    title = "10 QCM",
                    description = "Avoir complété au moins 10 sessions d'évaluation QCM",
                    iconEmoji = "🎯",
                    isUnlocked = quizCount >= 10,
                    progressPercent = (quizCount.toFloat() / 10f).coerceIn(0f, 1f)
                ),
                UserBadge(
                    id = "badge_90_avg",
                    title = "90 % de moyenne",
                    description = "Atteindre une moyenne générale d'au moins 90% aux QCM",
                    iconEmoji = "🥇",
                    isUnlocked = quizCount >= 3 && avgScore >= 90.0,
                    progressPercent = (avgScore.toFloat() / 90f).coerceIn(0f, 1f)
                ),
                UserBadge(
                    id = "badge_7_days",
                    title = "Assiduité ITM",
                    description = "Régularité dans l'apprentissage et les révisions",
                    iconEmoji = "🔥",
                    isUnlocked = quizCount >= 5 || completedCount >= 3,
                    progressPercent = ((completedCount + quizCount).toFloat() / 8f).coerceIn(0f, 1f)
                ),
                UserBadge(
                    id = "badge_master",
                    title = "Maître des révisions",
                    description = "Avoir passé avec brio au moins un examen blanc",
                    iconEmoji = "👑",
                    isUnlocked = quizResults.any { it.isMockExam && it.scorePercentage >= 75 },
                    progressPercent = if (quizResults.any { it.isMockExam }) 1f else 0f
                )
            )
        }
    }
}

data class SearchResults(
    val subjects: List<Subject> = emptyList(),
    val lessons: List<Lesson> = emptyList(),
    val definitions: List<DefinitionMatch> = emptyList(),
    val quizzes: List<QuizQuestion> = emptyList()
) {
    val isEmpty: Boolean get() = subjects.isEmpty() && lessons.isEmpty() && definitions.isEmpty() && quizzes.isEmpty()
}

data class DefinitionMatch(
    val lesson: Lesson,
    val term: String,
    val definition: String
)
