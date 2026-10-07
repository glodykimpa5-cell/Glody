package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.local.AppDatabase
import com.example.data.repository.LearningRepository
import com.example.ui.navigation.Screen
import com.example.ui.screens.AiAssistantScreen
import com.example.ui.screens.CoursesListScreen
import com.example.ui.screens.FavoritesScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LessonDetailScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.ProgressScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.SearchScreen
import com.example.ui.screens.SplashScreen
import com.example.ui.screens.SubjectDetailScreen
import com.example.ui.theme.AppThemeMode
import com.example.ui.theme.GlodyApprentisTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(applicationContext)
        val repository = LearningRepository(database.learningDao())

        setContent {
            var themeMode by remember { mutableStateOf(AppThemeMode.SYSTEM) }

            GlodyApprentisTheme(themeMode = themeMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GlodyApprentisApp(
                        repository = repository,
                        themeMode = themeMode,
                        onThemeModeChanged = { themeMode = it }
                    )
                }
            }
        }
    }
}

@Composable
fun GlodyApprentisApp(
    repository: LearningRepository,
    themeMode: AppThemeMode,
    onThemeModeChanged: (AppThemeMode) -> Unit
) {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }
    var selectedSubjectId by remember { mutableStateOf<String?>(null) }
    var selectedLessonId by remember { mutableStateOf<String?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var aiInitialPrompt by remember { mutableStateOf<String?>(null) }
    var quizInitialSubjectId by remember { mutableStateOf<String?>(null) }

    // Gérer le bouton retour physique Android (BackHandler)
    BackHandler(enabled = currentScreen != Screen.Home && currentScreen != Screen.Splash) {
        when (currentScreen) {
            Screen.LessonDetail -> {
                if (selectedSubjectId != null) {
                    currentScreen = Screen.SubjectDetail
                } else {
                    currentScreen = Screen.Courses
                }
            }
            Screen.SubjectDetail -> currentScreen = Screen.Home
            Screen.Search -> currentScreen = Screen.Home
            Screen.Favorites -> currentScreen = Screen.Home
            Screen.Progress -> currentScreen = Screen.Home
            Screen.Courses, Screen.Quiz, Screen.AiAssistant, Screen.Profile -> currentScreen = Screen.Home
            else -> currentScreen = Screen.Home
        }
    }

    val isBottomBarVisible = currentScreen in listOf(
        Screen.Home,
        Screen.Courses,
        Screen.Quiz,
        Screen.AiAssistant,
        Screen.Profile
    )

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            AnimatedVisibility(
                visible = isBottomBarVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 6.dp,
                    modifier = Modifier.testTag("bottom_nav_bar")
                ) {
                    // Accueil
                    NavigationBarItem(
                        selected = currentScreen == Screen.Home,
                        onClick = { currentScreen = Screen.Home },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Accueil") },
                        label = { Text("Accueil") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                        )
                    )

                    // Cours
                    NavigationBarItem(
                        selected = currentScreen == Screen.Courses,
                        onClick = { currentScreen = Screen.Courses },
                        icon = { Icon(Icons.Default.Book, contentDescription = "Cours") },
                        label = { Text("Cours") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                        )
                    )

                    // QCM
                    NavigationBarItem(
                        selected = currentScreen == Screen.Quiz,
                        onClick = {
                            quizInitialSubjectId = null
                            currentScreen = Screen.Quiz
                        },
                        icon = { Icon(Icons.Default.Quiz, contentDescription = "QCM") },
                        label = { Text("QCM") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                        )
                    )

                    // Assistant IA
                    NavigationBarItem(
                        selected = currentScreen == Screen.AiAssistant,
                        onClick = {
                            aiInitialPrompt = null
                            currentScreen = Screen.AiAssistant
                        },
                        icon = { Icon(Icons.Default.AutoAwesome, contentDescription = "Assistant IA") },
                        label = { Text("Assistant IA") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                        )
                    )

                    // Profil
                    NavigationBarItem(
                        selected = currentScreen == Screen.Profile,
                        onClick = { currentScreen = Screen.Profile },
                        icon = { Icon(Icons.Default.Person, contentDescription = "Profil") },
                        label = { Text("Profil") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentScreen) {
                Screen.Splash -> {
                    SplashScreen(
                        onSplashFinished = { currentScreen = Screen.Home }
                    )
                }

                Screen.Home -> {
                    HomeScreen(
                        repository = repository,
                        onNavigateToSubject = { subId ->
                            selectedSubjectId = subId
                            currentScreen = Screen.SubjectDetail
                        },
                        onNavigateToCourses = { currentScreen = Screen.Courses },
                        onNavigateToQuizzes = {
                            quizInitialSubjectId = null
                            currentScreen = Screen.Quiz
                        },
                        onNavigateToAiAssistant = {
                            aiInitialPrompt = null
                            currentScreen = Screen.AiAssistant
                        },
                        onNavigateToProgress = { currentScreen = Screen.Progress },
                        onNavigateToFavorites = { currentScreen = Screen.Favorites },
                        onNavigateToSearch = { query ->
                            searchQuery = query
                            currentScreen = Screen.Search
                        }
                    )
                }

                Screen.Courses -> {
                    CoursesListScreen(
                        repository = repository,
                        onNavigateToLesson = { lessonId ->
                            selectedLessonId = lessonId
                            currentScreen = Screen.LessonDetail
                        }
                    )
                }

                Screen.SubjectDetail -> {
                    val subId = selectedSubjectId ?: "soins_infirmiers"
                    SubjectDetailScreen(
                        subjectId = subId,
                        repository = repository,
                        onNavigateBack = { currentScreen = Screen.Home },
                        onNavigateToLesson = { lessonId ->
                            selectedLessonId = lessonId
                            currentScreen = Screen.LessonDetail
                        },
                        onNavigateToSubjectQuizzes = { sId ->
                            quizInitialSubjectId = sId
                            currentScreen = Screen.Quiz
                        }
                    )
                }

                Screen.LessonDetail -> {
                    val lId = selectedLessonId ?: "soins_constantes"
                    LessonDetailScreen(
                        lessonId = lId,
                        repository = repository,
                        onNavigateBack = {
                            if (selectedSubjectId != null) {
                                currentScreen = Screen.SubjectDetail
                            } else {
                                currentScreen = Screen.Courses
                            }
                        },
                        onAskAiAboutLesson = { lessonTitle ->
                            aiInitialPrompt = lessonTitle
                            currentScreen = Screen.AiAssistant
                        },
                        onTakeQuiz = { sId ->
                            quizInitialSubjectId = sId
                            currentScreen = Screen.Quiz
                        }
                    )
                }

                Screen.Quiz -> {
                    QuizScreen(
                        repository = repository,
                        initialSubjectId = quizInitialSubjectId,
                        onNavigateBack = if (quizInitialSubjectId != null) {
                            { currentScreen = Screen.Home }
                        } else null
                    )
                }

                Screen.AiAssistant -> {
                    AiAssistantScreen(
                        initialPrompt = aiInitialPrompt
                    )
                }

                Screen.Progress -> {
                    ProgressScreen(
                        repository = repository
                    )
                }

                Screen.Favorites -> {
                    FavoritesScreen(
                        repository = repository,
                        onNavigateToLesson = { lessonId ->
                            selectedLessonId = lessonId
                            currentScreen = Screen.LessonDetail
                        }
                    )
                }

                Screen.Search -> {
                    SearchScreen(
                        initialQuery = searchQuery,
                        repository = repository,
                        onNavigateBack = { currentScreen = Screen.Home },
                        onNavigateToLesson = { lessonId ->
                            selectedLessonId = lessonId
                            currentScreen = Screen.LessonDetail
                        },
                        onNavigateToSubject = { subId ->
                            selectedSubjectId = subId
                            currentScreen = Screen.SubjectDetail
                        }
                    )
                }

                Screen.Profile -> {
                    ProfileScreen(
                        currentThemeMode = themeMode,
                        onThemeModeChanged = onThemeModeChanged
                    )
                }
            }
        }
    }
}
