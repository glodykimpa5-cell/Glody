package com.example.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.FolderZip
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.GlodyOfficialAvatar
import com.example.ui.theme.AppThemeMode
import com.example.util.DownloadExportManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    currentThemeMode: AppThemeMode,
    onThemeModeChanged: (AppThemeMode) -> Unit
) {
    val context = LocalContext.current
    var showThemeDialog by remember { mutableStateOf(false) }
    var showMedicalSafetyDialog by remember { mutableStateOf(false) }
    var showPrivacyDialog by remember { mutableStateOf(false) }
    var showSourcesDialog by remember { mutableStateOf(false) }
    var showApkDownloadDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "👤 Mon Profil & Paramètres",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .testTag("profile_screen"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Carte Créateur & Photo officielle
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        GlodyOfficialAvatar(size = 96.dp, borderWidth = 3.dp)

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "Glody Kimpa Kiampa",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold)
                        )

                        Text(
                            text = "Créateur de Glody Apprentis",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Niveau d'études ciblé : A2 Sciences infirmières",
                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.outline)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "« Apprends. Comprends. Révise. Réussis. »",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.secondary
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            // Paramètres de l'application (Exigences n° 21, 23, 24)
            item {
                Text(
                    text = "Paramètres de l'application",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }

            // Thème d'affichage
            item {
                ProfileOptionItem(
                    icon = Icons.Default.DarkMode,
                    title = "Mode sombre / clair",
                    subtitle = when (currentThemeMode) {
                        AppThemeMode.LIGHT -> "☀️ Mode clair"
                        AppThemeMode.DARK -> "🌙 Mode sombre"
                        AppThemeMode.SYSTEM -> "⚙️ Automatique (système)"
                    },
                    onClick = { showThemeDialog = true }
                )
            }

            // Taille du texte
            item {
                ProfileOptionItem(
                    icon = Icons.Default.FormatSize,
                    title = "Taille du texte & Accessibilité",
                    subtitle = "Police standard optimisée pour la lecture clinique",
                    onClick = {}
                )
            }

            // Langue
            item {
                ProfileOptionItem(
                    icon = Icons.Default.Language,
                    title = "Langue d'apprentissage",
                    subtitle = "Français (Programme officiel RDC ITM)",
                    onClick = {}
                )
            }

            // Notifications de rappel
            item {
                ProfileOptionItem(
                    icon = Icons.Default.Notifications,
                    title = "Rappels de révision",
                    subtitle = "Actifs pour la régularité des QCM",
                    onClick = {}
                )
            }

            // Section Téléchargements & Hors-ligne (Téléchargement direct dans l'application)
            item {
                Text(
                    text = "Téléchargements & Accès hors-ligne",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }

            item {
                ProfileOptionItem(
                    icon = Icons.Default.Download,
                    title = "Télécharger le Syllabus complet A2 (.txt)",
                    subtitle = "Enregistre tous les cours et la banque de QCM sur votre téléphone",
                    onClick = {
                        DownloadExportManager.downloadFullSyllabus(context)
                    }
                )
            }

            item {
                ProfileOptionItem(
                    icon = Icons.Default.FolderZip,
                    title = "Télécharger / Installer l'application APK",
                    subtitle = "Accéder au lien de téléchargement direct de l'APK sur mobile",
                    onClick = { showApkDownloadDialog = true }
                )
            }

            item {
                ProfileOptionItem(
                    icon = Icons.Default.Share,
                    title = "Partager l'application avec des camarades",
                    subtitle = "Envoyer l'application à vos collègues étudiants de l'ITM",
                    onClick = {
                        DownloadExportManager.shareLessonOrApp(
                            context,
                            "📚 Rejoins-moi sur GLODY APPRENTIS ! L'application dédiée aux étudiants en sciences infirmières A2 (ITM) : cours, QCM d'examen et assistant IA.\nAccède à l'application ici : https://ais-pre-356ifmayxvl42kevlgu3lg-943041746203.europe-west2.run.app"
                        )
                    }
                )
            }

            // Section Informations & Légal
            item {
                Text(
                    text = "Sécurité médicale & Légal",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }

            item {
                ProfileOptionItem(
                    icon = Icons.Default.Security,
                    title = "Avertissement & Sécurité médicale",
                    subtitle = "Règles d'utilisation en milieu de soins",
                    onClick = { showMedicalSafetyDialog = true }
                )
            }

            item {
                ProfileOptionItem(
                    icon = Icons.Default.Info,
                    title = "Sources & Références officielles (OMS)",
                    subtitle = "Documentation institutionnelle vérifiée",
                    onClick = { showSourcesDialog = true }
                )
            }

            item {
                ProfileOptionItem(
                    icon = Icons.Default.Info,
                    title = "Politique de confidentialité (RGPD / Play Store)",
                    subtitle = "Protection des données et respect de la vie privée",
                    onClick = { showPrivacyDialog = true }
                )
            }

            // Version
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Glody Apprentis v1.0.0 (Build 1)",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.outline,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Text(
                        text = "Conçu avec dévouement pour les Instituts Techniques Médicaux (RDC)",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.outline
                        )
                    )
                }
            }
        }
    }

    // Dialogue Sélection du Mode Thème
    if (showThemeDialog) {
        AlertDialog(
            onDismissRequest = { showThemeDialog = false },
            title = { Text("Mode d'affichage") },
            text = {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onThemeModeChanged(AppThemeMode.LIGHT)
                                showThemeDialog = false
                            }
                    ) {
                        RadioButton(
                            selected = currentThemeMode == AppThemeMode.LIGHT,
                            onClick = {
                                onThemeModeChanged(AppThemeMode.LIGHT)
                                showThemeDialog = false
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("☀️ Mode clair")
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onThemeModeChanged(AppThemeMode.DARK)
                                showThemeDialog = false
                            }
                    ) {
                        RadioButton(
                            selected = currentThemeMode == AppThemeMode.DARK,
                            onClick = {
                                onThemeModeChanged(AppThemeMode.DARK)
                                showThemeDialog = false
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("🌙 Mode sombre")
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onThemeModeChanged(AppThemeMode.SYSTEM)
                                showThemeDialog = false
                            }
                    ) {
                        RadioButton(
                            selected = currentThemeMode == AppThemeMode.SYSTEM,
                            onClick = {
                                onThemeModeChanged(AppThemeMode.SYSTEM)
                                showThemeDialog = false
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("⚙️ Automatique (selon le système Android)")
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showThemeDialog = false }) { Text("Fermer") }
            }
        )
    }

    // Dialogue Sécurité Médicale (Exigence n° 27)
    if (showMedicalSafetyDialog) {
        AlertDialog(
            onDismissRequest = { showMedicalSafetyDialog = false },
            title = { Text("Sécurité médicale & Décharge") },
            text = {
                Text(
                    text = "Glody Apprentis est une application éducative destinée aux apprenants en sciences infirmières.\n\nElle ne remplace pas l'enseignement d'un professionnel, les protocoles de soins, les recommandations officielles ou l'avis d'un professionnel de santé.\n\nEn situation clinique réelle, suivre les protocoles de l'établissement et demander l'avis d'un professionnel qualifié.",
                    lineHeight = 22.sp
                )
            },
            confirmButton = {
                TextButton(onClick = { showMedicalSafetyDialog = false }) { Text("J'ai compris") }
            }
        )
    }

    // Dialogue Sources & Références (Exigence n° 16)
    if (showSourcesDialog) {
        AlertDialog(
            onDismissRequest = { showSourcesDialog = false },
            title = { Text("Sources & Références officielles") },
            text = {
                Text(
                    text = "Les cours et questions de l'application s'appuient sur :\n\n• Directives cliniques de l'Organisation mondiale de la Santé (OMS)\n• Programme national de formation A2 en Sciences Infirmières des Instituts Techniques Médicaux (ITM/RDC)\n• Protocoles du Ministère de la Santé Publique, Hygiène et Prévention de la RDC (PNSR, PNLP, PCIME)",
                    lineHeight = 22.sp
                )
            },
            confirmButton = {
                TextButton(onClick = { showSourcesDialog = false }) { Text("Fermer") }
            }
        )
    }

    // Dialogue Confidentialité (Exigence n° 28)
    if (showPrivacyDialog) {
        AlertDialog(
            onDismissRequest = { showPrivacyDialog = false },
            title = { Text("Politique de Confidentialité") },
            text = {
                Text(
                    text = "Protection de la vie privée :\n\n• L'application Glody Apprentis ne collecte aucune donnée personnelle sensible.\n• Elle ne recueille jamais de données médicales relatives aux patients.\n• Toute la progression des cours et les notes de QCM sont stockées localement sur votre appareil Android.",
                    lineHeight = 22.sp
                )
            },
            confirmButton = {
                TextButton(onClick = { showPrivacyDialog = false }) { Text("Fermer") }
            }
        )
    }

    // Dialogue Téléchargement de l'APK (Installation directe sur téléphone)
    if (showApkDownloadDialog) {
        AlertDialog(
            onDismissRequest = { showApkDownloadDialog = false },
            title = { Text("📥 Télécharger l'application APK") },
            text = {
                Column {
                    Text(
                        text = "Pour installer l'application GLODY APPRENTIS sur votre smartphone Android :\n\n1. Cliquez sur le bouton ci-dessous pour ouvrir le téléchargement direct de l'APK.\n2. Autorisez l'installation d'applications depuis cette source dans les paramètres de votre téléphone.\n3. Cliquez sur Installer puis Ouvrir !",
                        lineHeight = 21.sp
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        DownloadExportManager.openApkDownloadLink(
                            context,
                            "https://ais-pre-356ifmayxvl42kevlgu3lg-943041746203.europe-west2.run.app"
                        )
                        showApkDownloadDialog = false
                    }
                ) {
                    Text("Ouvrir le lien de téléchargement 🚀", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showApkDownloadDialog = false }) {
                    Text("Annuler")
                }
            }
        )
    }
}

@Composable
fun ProfileOptionItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold)
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.outline)
                )
            }
        }
    }
}
