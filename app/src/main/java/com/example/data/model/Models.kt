package com.example.data.model

data class Subject(
    val id: String,
    val title: String,
    val iconEmoji: String,
    val subtitle: String,
    val description: String,
    val objectives: List<String>,
    val colorHex: Long = 0xFF026A75
)

data class Lesson(
    val id: String,
    val subjectId: String,
    val title: String,
    val category: String,
    val readTimeMinutes: Int,
    val presentation: String,
    val definitions: List<DefinitionItem>,
    val keyPoints: List<String>,
    val contentSections: List<ContentSection>,
    val nursingRole: List<String>,
    val summary: String,
    val sources: List<SourceReference>
)

data class DefinitionItem(
    val term: String,
    val definition: String
)

data class ContentSection(
    val title: String,
    val paragraphs: List<String>,
    val bullets: List<String> = emptyList()
)

data class SourceReference(
    val title: String,
    val organization: String,
    val year: String,
    val details: String = ""
)

enum class QuizLevel(val label: String) {
    LEVEL_1("Niveau 1 : Débutant"),
    LEVEL_2("Niveau 2 : Intermédiaire"),
    LEVEL_3("Niveau 3 : Avancé"),
    LEVEL_4("Niveau 4 : Examen")
}

data class QuizQuestion(
    val id: String,
    val subjectId: String,
    val lessonId: String? = null,
    val question: String,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,
    val correctAnswer: Int, // 0 for A, 1 for B, 2 for C, 3 for D
    val explanation: String,
    val level: QuizLevel = QuizLevel.LEVEL_1
)

data class UserBadge(
    val id: String,
    val title: String,
    val description: String,
    val iconEmoji: String,
    val isUnlocked: Boolean,
    val progressPercent: Float = 0f
)
