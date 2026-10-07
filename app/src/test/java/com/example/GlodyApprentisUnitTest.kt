package com.example

import com.example.ai.GlodyAiAssistant
import com.example.data.datasource.EducationalLessonsData
import com.example.data.datasource.EducationalQuizData
import com.example.data.datasource.EducationalSubjectsData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class GlodyApprentisUnitTest {

    @Test
    fun testAllRequiredSubjectsArePresent() {
        val subjects = EducationalSubjectsData.subjects
        assertEquals(8, subjects.size)

        val ids = subjects.map { it.id }.toSet()
        assertTrue(ids.contains("soins_infirmiers"))
        assertTrue(ids.contains("obstetrique"))
        assertTrue(ids.contains("gynecologie"))
        assertTrue(ids.contains("pathologie"))
        assertTrue(ids.contains("pediatrie"))
        assertTrue(ids.contains("puericulture"))
        assertTrue(ids.contains("gestion"))
        assertTrue(ids.contains("revision_generale"))
    }

    @Test
    fun testEducationalLessonsStructure() {
        val lessons = EducationalLessonsData.lessons
        assertTrue(lessons.isNotEmpty())

        lessons.forEach { lesson ->
            assertFalse(lesson.title.isBlank())
            assertFalse(lesson.presentation.isBlank())
            assertTrue("Lesson must have at least one definition", lesson.definitions.isNotEmpty())
            assertTrue("Lesson must have key points", lesson.keyPoints.isNotEmpty())
            assertTrue("Lesson must have clinical content sections", lesson.contentSections.isNotEmpty())
            assertTrue("Lesson must have nursing roles defined", lesson.nursingRole.isNotEmpty())
            assertFalse(lesson.summary.isBlank())
            assertTrue("Lesson must declare official sources", lesson.sources.isNotEmpty())
        }
    }

    @Test
    fun testQuizQuestionsValidity() {
        val questions = EducationalQuizData.questions
        assertTrue(questions.isNotEmpty())

        questions.forEach { q ->
            assertFalse(q.question.isBlank())
            assertFalse(q.optionA.isBlank())
            assertFalse(q.optionB.isBlank())
            assertFalse(q.optionC.isBlank())
            assertFalse(q.optionD.isBlank())
            assertTrue("Correct answer index must be between 0 and 3", q.correctAnswer in 0..3)
            assertFalse(q.explanation.isBlank())
        }
    }

    @Test
    fun testAiAssistantPreeclampsiaResponseStructure() = runBlocking {
        val assistant = GlodyAiAssistant()
        val result = assistant.askAssistant("Explique-moi la pré-éclampsie simplement.")
        assertTrue(result.isSuccess)
        val text = result.getOrNull() ?: ""

        assertTrue(text.contains("1. Définition"))
        assertTrue(text.contains("2. Causes"))
        assertTrue(text.contains("3. Signes"))
        assertTrue(text.contains("4. Complications"))
        assertTrue(text.contains("5. Principes de prise en charge"))
        assertTrue(text.contains("6. Rôle infirmier"))
        assertTrue(text.contains("7. Points importants"))
        assertTrue(text.contains("8. Petit QCM"))
        assertTrue(text.contains("Sécurité médicale"))
    }
}
