package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.QuizLevel
import com.example.data.model.QuizQuestion
import com.example.data.repository.LearningRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    repository: LearningRepository,
    initialSubjectId: String? = null,
    onNavigateBack: (() -> Unit)? = null
) {
    val subjects = repository.getAllSubjects()
    var selectedSubjectId by remember { mutableStateOf(initialSubjectId) }
    var selectedLevel by remember { mutableStateOf<QuizLevel?>(null) }

    // Quiz Session State
    var isQuizActive by remember { mutableStateOf(false) }
    var isMockExamMode by remember { mutableStateOf(false) }
    var questionsList by remember { mutableStateOf<List<QuizQuestion>>(emptyList()) }
    var currentIndex by remember { mutableIntStateOf(0) }
    var selectedAnswerIndex by remember { mutableStateOf<Int?>(null) }
    var hasAnsweredCurrent by remember { mutableStateOf(false) }
    var correctCount by remember { mutableIntStateOf(0) }
    var wrongCount by remember { mutableIntStateOf(0) }
    var isFinished by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isMockExamMode) "📝 EXAMEN BLANC A2" else "📝 Évaluation QCM",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                navigationIcon = {
                    if (onNavigateBack != null || isQuizActive) {
                        IconButton(onClick = {
                            if (isQuizActive) {
                                isQuizActive = false
                                isFinished = false
                            } else {
                                onNavigateBack?.invoke()
                            }
                        }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .testTag("quiz_screen")
        ) {
            if (!isQuizActive) {
                // Écran de configuration des QCM et Examen Blanc
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Carte Examen Blanc (Exigence n° 14)
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Column(modifier = Modifier.padding(18.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("🎯 ", fontSize = 26.sp)
                                    Column {
                                        Text(
                                            text = "EXAMEN BLANC A2",
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontWeight = FontWeight.ExtraBold,
                                                color = MaterialTheme.colorScheme.onPrimary
                                            )
                                        )
                                        Text(
                                            text = "Simulation d'épreuve certificative ITM",
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                                            )
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                Text(
                                    text = "Mesure tes compétences en conditions réelles avec des questions transversales issues du programme de soins infirmiers.",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f)
                                    )
                                )

                                Spacer(modifier = Modifier.height(14.dp))

                                Button(
                                    onClick = {
                                        val examQuestions = repository.generateMockExam(selectedSubjectId, 10)
                                        if (examQuestions.isNotEmpty()) {
                                            questionsList = examQuestions
                                            currentIndex = 0
                                            correctCount = 0
                                            wrongCount = 0
                                            selectedAnswerIndex = null
                                            hasAnsweredCurrent = false
                                            isFinished = false
                                            isMockExamMode = true
                                            isQuizActive = true
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.surface,
                                        contentColor = MaterialTheme.colorScheme.primary
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .testTag("start_mock_exam_button")
                                ) {
                                    Icon(Icons.Default.PlayArrow, contentDescription = null)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("LANCER L'EXAMEN BLANC (10 QUESTIONS)", fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }

                    // Sélecteur de niveau (Exigence n° 13)
                    item {
                        Text(
                            text = "Niveau de difficulté",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            QuizLevel.entries.forEach { lvl ->
                                FilterChip(
                                    selected = selectedLevel == lvl,
                                    onClick = {
                                        selectedLevel = if (selectedLevel == lvl) null else lvl
                                    },
                                    label = { Text(lvl.label.substringBefore(" :")) },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }

                    // Filtre par matière
                    item {
                        Text(
                            text = "Choisir une matière spécifique",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                    }

                    item {
                        FilterChip(
                            selected = selectedSubjectId == null,
                            onClick = { selectedSubjectId = null },
                            label = { Text("Toutes les matières (${repository.getAllQuizzes(selectedLevel).size} QCM)") }
                        )
                    }

                    subjects.forEach { subject ->
                        val questionsCount = repository.getQuizzesForSubject(subject.id, selectedLevel).size
                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedSubjectId = subject.id
                                        val qList = repository.getQuizzesForSubject(subject.id, selectedLevel)
                                        if (qList.isNotEmpty()) {
                                            questionsList = qList
                                            currentIndex = 0
                                            correctCount = 0
                                            wrongCount = 0
                                            selectedAnswerIndex = null
                                            hasAnsweredCurrent = false
                                            isFinished = false
                                            isMockExamMode = false
                                            isQuizActive = true
                                        }
                                    },
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = subject.iconEmoji, fontSize = 24.sp)
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = subject.title,
                                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                                        )
                                        Text(
                                            text = "$questionsCount questions disponibles",
                                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.outline)
                                        )
                                    }
                                    Button(
                                        onClick = {
                                            val qList = repository.getQuizzesForSubject(subject.id, selectedLevel)
                                            if (qList.isNotEmpty()) {
                                                questionsList = qList
                                                currentIndex = 0
                                                correctCount = 0
                                                wrongCount = 0
                                                selectedAnswerIndex = null
                                                hasAnsweredCurrent = false
                                                isFinished = false
                                                isMockExamMode = false
                                                isQuizActive = true
                                            }
                                        },
                                        enabled = questionsCount > 0
                                    ) {
                                        Text("Commencer")
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (isFinished) {
                // Écran de Résultats et Bilan (Exigence n° 13 et n° 14)
                val total = questionsList.size
                val percentage = if (total > 0) (correctCount * 100) / total else 0
                val note20 = if (total > 0) (correctCount * 20) / total else 0

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                        .testTag("quiz_results_screen"),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = if (percentage >= 70) "🎉 Félicitations !" else "📖 Continue tes révisions !",
                                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.ExtraBold)
                            )

                            Spacer(modifier = Modifier.height(14.dp))

                            Text(
                                text = "NOTE : $note20/20",
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.Black,
                                    color = if (percentage >= 60) MaterialTheme.colorScheme.primary else Color(0xFFDC2626)
                                )
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "TAUX DE RÉUSSITE : $percentage %",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = "✅ $correctCount",
                                        style = MaterialTheme.typography.titleLarge.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF16A34A)
                                        )
                                    )
                                    Text("Bonnes réponses", style = MaterialTheme.typography.bodySmall)
                                }

                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = "❌ $wrongCount",
                                        style = MaterialTheme.typography.titleLarge.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFDC2626)
                                        )
                                    )
                                    Text("Mauvaises réponses", style = MaterialTheme.typography.bodySmall)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            isQuizActive = false
                            isFinished = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("RETOUR AUX SESSIONS QCM", fontWeight = FontWeight.Bold)
                    }
                }
            } else {
                // Session active d'une question
                val currentQuestion = questionsList.getOrNull(currentIndex)
                if (currentQuestion != null) {
                    val progressFloat = (currentIndex + 1).toFloat() / questionsList.size

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        // En-tête progression de la session
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Question ${currentIndex + 1} / ${questionsList.size}",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "Score actuel : $correctCount",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        LinearProgressIndicator(
                            progress = { progressFloat },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp)),
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            // Énoncé de la question
                            item {
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Text(
                                            text = currentQuestion.question,
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                lineHeight = 24.sp
                                            )
                                        )
                                    }
                                }
                            }

                            // Les 4 Choix A, B, C, D
                            val options = listOf(
                                "A. " + currentQuestion.optionA,
                                "B. " + currentQuestion.optionB,
                                "C. " + currentQuestion.optionC,
                                "D. " + currentQuestion.optionD
                            )

                            items(options.size) { index ->
                                val isSelected = selectedAnswerIndex == index
                                val isCorrect = index == currentQuestion.correctAnswer

                                val cardColor = when {
                                    !hasAnsweredCurrent -> if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surface
                                    isCorrect -> Color(0xFFDCFCE7) // Vert clair
                                    isSelected && !isCorrect -> Color(0xFFFEE2E2) // Rouge clair
                                    else -> MaterialTheme.colorScheme.surface
                                }

                                val borderColor = when {
                                    !hasAnsweredCurrent -> if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
                                    isCorrect -> Color(0xFF16A34A)
                                    isSelected && !isCorrect -> Color(0xFFDC2626)
                                    else -> Color.Transparent
                                }

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable(enabled = !hasAnsweredCurrent) {
                                            selectedAnswerIndex = index
                                        }
                                        .testTag("quiz_option_$index"),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = cardColor),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(14.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = options[index],
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                fontWeight = if (isSelected || (hasAnsweredCurrent && isCorrect)) FontWeight.Bold else FontWeight.Normal
                                            ),
                                            modifier = Modifier.weight(1f)
                                        )

                                        if (hasAnsweredCurrent) {
                                            if (isCorrect) {
                                                Icon(
                                                    Icons.Default.CheckCircle,
                                                    contentDescription = "Correct",
                                                    tint = Color(0xFF16A34A)
                                                )
                                            } else if (isSelected) {
                                                Icon(
                                                    Icons.Default.Close,
                                                    contentDescription = "Incorrect",
                                                    tint = Color(0xFFDC2626)
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                            // Résultat et Explication pédagogique
                            if (hasAnsweredCurrent) {
                                item {
                                    val isUserCorrect = selectedAnswerIndex == currentQuestion.correctAnswer
                                    Card(
                                        modifier = Modifier.fillMaxWidth(),
                                        shape = RoundedCornerShape(14.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = if (isUserCorrect) Color(0xFFF0FDF4) else Color(0xFFFEF2F2)
                                        )
                                    ) {
                                        Column(modifier = Modifier.padding(16.dp)) {
                                            Text(
                                                text = if (isUserCorrect) "✅ Bonne réponse !" else "❌ Mauvaise réponse",
                                                style = MaterialTheme.typography.titleMedium.copy(
                                                    fontWeight = FontWeight.Bold,
                                                    color = if (isUserCorrect) Color(0xFF16A34A) else Color(0xFFDC2626)
                                                )
                                            )
                                            Spacer(modifier = Modifier.height(6.dp))
                                            Text(
                                                text = "Explication pédagogique :",
                                                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                                            )
                                            Spacer(modifier = Modifier.height(2.dp))
                                            Text(
                                                text = currentQuestion.explanation,
                                                style = MaterialTheme.typography.bodyMedium,
                                                lineHeight = 20.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        // Bouton d'action en bas
                        Spacer(modifier = Modifier.height(12.dp))

                        if (!hasAnsweredCurrent) {
                            Button(
                                onClick = {
                                    if (selectedAnswerIndex != null) {
                                        hasAnsweredCurrent = true
                                        if (selectedAnswerIndex == currentQuestion.correctAnswer) {
                                            correctCount++
                                        } else {
                                            wrongCount++
                                        }
                                    }
                                },
                                enabled = selectedAnswerIndex != null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("validate_answer_button")
                            ) {
                                Text("VALIDER LA RÉPONSE", fontWeight = FontWeight.Bold)
                            }
                        } else {
                            Button(
                                onClick = {
                                    if (currentIndex + 1 < questionsList.size) {
                                        currentIndex++
                                        selectedAnswerIndex = null
                                        hasAnsweredCurrent = false
                                    } else {
                                        // Fin du quiz
                                        isFinished = true
                                        scope.launch {
                                            repository.recordQuizResult(
                                                subjectId = selectedSubjectId ?: "general",
                                                totalQuestions = questionsList.size,
                                                correctAnswers = correctCount,
                                                isMockExam = isMockExamMode
                                            )
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("next_question_button")
                            ) {
                                Text(
                                    text = if (currentIndex + 1 < questionsList.size) "QUESTION SUIVANTE ➔" else "VOIR LE RÉSULTAT FINAL 🏁",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
