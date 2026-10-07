package com.example.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import com.example.data.datasource.EducationalLessonsData
import com.example.data.datasource.EducationalQuizData
import com.example.data.datasource.EducationalSubjectsData
import com.example.data.model.Lesson
import java.io.File
import java.io.FileWriter

object DownloadExportManager {

    /**
     * Télécharge / Exporte un cours infirmier A2 sous forme de fiche pédagogique textuelle (.txt / .doc)
     * directement dans le dossier "Download" du téléphone de l'étudiant.
     */
    fun downloadLessonSummary(context: Context, lesson: Lesson): Boolean {
        return try {
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            if (!downloadsDir.exists()) {
                downloadsDir.mkdirs()
            }

            val sanitizedTitle = lesson.title.replace(Regex("[^a-zA-Z0-9_-]"), "_").take(30)
            val file = File(downloadsDir, "GLODY_APPRENTIS_${sanitizedTitle}.txt")

            val content = buildString {
                appendLine("=========================================================")
                appendLine("GLODY APPRENTIS — SCIENCES INFIRMIÈRES NIVEAU A2 (ITM)")
                appendLine("Créateur : Glody Kimpa Kiampa")
                appendLine("Devise : « Apprends. Comprends. Révise. Réussis. »")
                appendLine("=========================================================\n")
                appendLine("COURS : ${lesson.title.uppercase()}")
                appendLine("Catégorie : ${lesson.category} (Lecture estimée : ${lesson.readTimeMinutes} min)\n")
                appendLine("--- 1. PRÉSENTATION ---")
                appendLine(lesson.presentation)
                appendLine("\n--- 2. NOTIONS IMPORTANTES À RETENIR ---")
                lesson.keyPoints.forEach { appendLine("• $it") }
                appendLine("\n--- 3. DÉFINITIONS CLÉS ---")
                lesson.definitions.forEach { appendLine("• ${it.term} : ${it.definition}") }
                appendLine("\n--- 4. CONTENU CLINIQUE DU COURS ---")
                lesson.contentSections.forEach { section ->
                    appendLine("\n[ ${section.title} ]")
                    section.paragraphs.forEach { appendLine(it) }
                    if (section.bullets.isNotEmpty()) {
                        section.bullets.forEach { appendLine("  - $it") }
                    }
                }
                appendLine("\n--- 5. RÔLE INFIRMIER & DÉMARCHE DE SOINS ---")
                lesson.nursingRole.forEach { appendLine("• $it") }
                appendLine("\n--- 6. RÉSUMÉ SYNTHÉTIQUE ---")
                appendLine(lesson.summary)
                appendLine("\n--- 7. SOURCES OFFICIELLES ---")
                lesson.sources.forEach {
                    appendLine("• ${it.title} — ${it.organization} (${it.year})")
                }
                appendLine("\n=========================================================")
                appendLine("⚠️ AVERTISSEMENT : Document pédagogique pour apprenants A2.")
                appendLine("Ne remplace pas les protocoles officiels de service.")
                appendLine("=========================================================")
            }

            FileWriter(file).use { writer ->
                writer.write(content)
            }

            Toast.makeText(
                context,
                "✅ Fiche téléchargée dans Téléchargements :\n${file.name}",
                Toast.LENGTH_LONG
            ).show()

            true
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "Erreur lors du téléchargement : ${e.localizedMessage}",
                Toast.LENGTH_SHORT
            ).show()
            false
        }
    }

    /**
     * Télécharge l'ensemble du syllabus de révision A2 de l'application
     * (toutes les fiches de cours + QCM complets) dans le dossier Téléchargements.
     */
    fun downloadFullSyllabus(context: Context): Boolean {
        return try {
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            if (!downloadsDir.exists()) {
                downloadsDir.mkdirs()
            }

            val file = File(downloadsDir, "GLODY_APPRENTIS_SYLLABUS_COMPLET_A2.txt")
            val content = buildString {
                appendLine("=================================================================")
                appendLine("GLODY APPRENTIS — SYLLABUS OFFICIEL DE RÉVISION NIVEAU A2 (ITM)")
                appendLine("Par Glody Kimpa Kiampa")
                appendLine("« Apprends. Comprends. Révise. Réussis. »")
                appendLine("=================================================================\n")

                appendLine("SOMMAIRE DES MATIÈRES :")
                EducationalSubjectsData.subjects.forEachIndexed { i, s ->
                    appendLine("${i + 1}. ${s.iconEmoji} ${s.title} — ${s.subtitle}")
                }
                appendLine("\n-----------------------------------------------------------------\n")

                EducationalLessonsData.lessons.forEach { lesson ->
                    appendLine("COURS : ${lesson.title}")
                    appendLine("Catégorie : ${lesson.category}")
                    appendLine(lesson.presentation)
                    appendLine("\nPoints clés :")
                    lesson.keyPoints.forEach { appendLine(" - $it") }
                    appendLine("\nDémarche et rôle infirmier :")
                    lesson.nursingRole.forEach { appendLine(" - $it") }
                    appendLine("\n-----------------------------------------------------------------\n")
                }

                appendLine("\nBANQUE DE QUESTIONS QCM ITM :")
                EducationalQuizData.questions.forEachIndexed { idx, q ->
                    appendLine("Q${idx + 1} : ${q.question}")
                    appendLine("  A. ${q.optionA}")
                    appendLine("  B. ${q.optionB}")
                    appendLine("  C. ${q.optionC}")
                    appendLine("  D. ${q.optionD}")
                    val bonneLettre = when (q.correctAnswer) {
                        0 -> "A"
                        1 -> "B"
                        2 -> "C"
                        else -> "D"
                    }
                    appendLine("  => Bonne réponse : $bonneLettre")
                    appendLine("  Explication : ${q.explanation}\n")
                }
            }

            FileWriter(file).use { writer ->
                writer.write(content)
            }

            Toast.makeText(
                context,
                "✅ Syllabus complet téléchargé dans Téléchargements :\n${file.name}",
                Toast.LENGTH_LONG
            ).show()

            true
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "Erreur de téléchargement : ${e.localizedMessage}",
                Toast.LENGTH_SHORT
            ).show()
            false
        }
    }

    /**
     * Permet d'ouvrir le lien de téléchargement direct de l'APK ou du projet
     * dans le navigateur du téléphone.
     */
    fun openApkDownloadLink(context: Context, downloadUrl: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(downloadUrl)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Impossible d'ouvrir le navigateur : ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Permet à l'étudiant de partager un cours ou l'application avec ses camarades de promotion
     */
    fun shareLessonOrApp(context: Context, textToShare: String) {
        try {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "GLODY APPRENTIS — Sciences Infirmières A2")
                putExtra(Intent.EXTRA_TEXT, textToShare)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(Intent.createChooser(intent, "Partager avec mes collègues ITM"))
        } catch (e: Exception) {
            Toast.makeText(context, "Erreur de partage : ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
        }
    }
}
