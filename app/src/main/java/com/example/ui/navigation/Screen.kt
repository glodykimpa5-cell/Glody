package com.example.ui.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Home : Screen("home")
    data object Courses : Screen("courses")
    data object Quiz : Screen("quiz")
    data object AiAssistant : Screen("ai_assistant")
    data object Progress : Screen("progress")
    data object Favorites : Screen("favorites")
    data object Profile : Screen("profile")
    data object Search : Screen("search")
    data object SubjectDetail : Screen("subject_detail")
    data object LessonDetail : Screen("lesson_detail")
}
