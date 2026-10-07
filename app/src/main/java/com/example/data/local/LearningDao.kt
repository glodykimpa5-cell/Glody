package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LearningDao {

    @Query("SELECT * FROM lesson_progress")
    fun getAllProgress(): Flow<List<LessonProgressEntity>>

    @Query("SELECT * FROM lesson_progress WHERE lessonId = :lessonId")
    fun getProgressForLesson(lessonId: String): Flow<LessonProgressEntity?>

    @Query("SELECT * FROM lesson_progress WHERE isFavorite = 1")
    fun getFavoriteLessons(): Flow<List<LessonProgressEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateProgress(progress: LessonProgressEntity)

    @Query("UPDATE lesson_progress SET isCompleted = :completed WHERE lessonId = :lessonId")
    suspend fun setLessonCompleted(lessonId: String, completed: Boolean)

    @Query("UPDATE lesson_progress SET isFavorite = :isFavorite WHERE lessonId = :lessonId")
    suspend fun setLessonFavorite(lessonId: String, isFavorite: Boolean)

    @Query("SELECT * FROM quiz_result ORDER BY timestamp DESC")
    fun getAllQuizResults(): Flow<List<QuizResultEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizResult(result: QuizResultEntity)

    @Query("SELECT COUNT(*) FROM lesson_progress WHERE isCompleted = 1")
    fun getCompletedLessonsCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM quiz_result")
    fun getTotalQuizzesTakenCount(): Flow<Int>
}
