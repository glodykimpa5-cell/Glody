package com.example.data.datasource

import com.example.data.model.Subject

object EducationalSubjectsData {
    val subjects = listOf(
        Subject(
            id = "soins_infirmiers",
            title = "Techniques de soins infirmiers",
            iconEmoji = "🩺",
            subtitle = "Gestes, protocoles, constantes & hygiène",
            description = "Apprentissage des techniques de soins de base et avancées selon le programme A2 des Instituts Techniques Médicaux (ITM) : hygiène, asepsie, prise des constantes, administration des médicaments, injections, pansements et surveillance clinique.",
            objectives = listOf(
                "Maîtriser les mesures d'asepsie et d'hygiène hospitalière",
                "Mesurer, interpréter et consigner les constantes vitales",
                "Appliquer les règles de sécurité lors de l'administration médicamenteuse",
                "Réaliser les pansements simples et les soins de plaies dans le respect des règles d'asepsie"
            ),
            colorHex = 0xFF026A75
        ),
        Subject(
            id = "obstetrique",
            title = "Obstétrique",
            iconEmoji = "🤰",
            subtitle = "Grossesse, CPN, travail & accouchement",
            description = "Enseignement obstétrical pour l'infirmier(e) de niveau A2 : physiologie de la grossesse, consultations prénatales (CPN), surveillance du travail et du partogramme, accouchement eutocique, soins immédiats du nouveau-né, et dépistage précoce des urgences obstétricales (pré-éclampsie, éclampsie, hémorragies).",
            objectives = listOf(
                "Conduire un suivi prénatal de qualité et identifier les facteurs de risque",
                "Surveiller le travail d'accouchement à l'aide du partogramme",
                "Assurer l'accueil et les premiers soins du nouveau-né en salle de naissance",
                "Dépister les urgences obstétricales majeures et amorcer la prise en charge initiale"
            ),
            colorHex = 0xFFD97706
        ),
        Subject(
            id = "gynecologie",
            title = "Gynécologie",
            iconEmoji = "👩‍⚕️",
            subtitle = "Santé génitale, cycle & planification familiale",
            description = "Anatomie et physiologie de l'appareil reproducteur féminin, régulation du cycle menstruel, prévention et dépistage des infections de l'appareil génital (IST/salpingite), planification familiale/contraception, et éducation sanitaire communautaire.",
            objectives = listOf(
                "Comprendre le fonctionnement neuro-hormonal du cycle menstruel",
                "Identifier les principales infections gynécologiques et conseiller les patientes",
                "Conseiller les différentes méthodes contraceptives selon les critères OMS",
                "Promouvoir le dépistage du cancer du col utérin et la santé reproductive"
            ),
            colorHex = 0xFF9333EA
        ),
        Subject(
            id = "pathologie",
            title = "Pathologie",
            iconEmoji = "🦠",
            subtitle = "Maladies médico-chirurgicales & rôle infirmier",
            description = "Étude clinique des grandes pathologies prévalentes : paludisme grave, pneumopathies, tuberculose, diabète, hypertension artérielle, appendicite aiguë. Analyse des signes, complications, protocoles thérapeutiques et plan de soins infirmiers.",
            objectives = listOf(
                "Décrire les signes cardinaux des grandes pathologies infectieuses et chroniques",
                "Reconnaître les signes de gravité et complications vitales",
                "Exécuter la prescription médicale et surveiller les effets secondaires",
                "Élaborer et appliquer une démarche de soins infirmiers adaptée"
            ),
            colorHex = 0xFFDC2626
        ),
        Subject(
            id = "pediatrie",
            title = "Pédiatrie",
            iconEmoji = "👶",
            subtitle = "Développement, pathologies de l'enfant & urgences",
            description = "Surveillance du développement psycho-moteur et staturo-pondéral de l'enfant, prise en charge des affections pédiatriques fréquentes (paludisme infantile, déshydratation diarrhéique, pneumonie, malnutrition), calendrier vaccinal PEV et gestes d'urgence pédiatrique.",
            objectives = listOf(
                "Évaluer la croissance et l'état nutritionnel d'un enfant (courbes OMS, PB)",
                "Prendre en charge la déshydratation aiguë selon le protocole PCIME",
                "Maîtriser le calendrier vaccinal du Programme Élargi de Vaccination (PEV)",
                "Surveiller un enfant hospitalisé et adapter les posologies thérapeutiques"
            ),
            colorHex = 0xFF2563EB
        ),
        Subject(
            id = "puericulture",
            title = "Puériculture",
            iconEmoji = "🍼",
            subtitle = "Soins du nouveau-né & alimentation",
            description = "Soins d'hygiène et de bien-être du nouveau-né à terme et prématuré, promotion de l'allaitement maternel exclusif (AME), alimentation de complément, prévention de l'hypothermie (méthode Mère Kangourou) et suivi du nourrisson.",
            objectives = listOf(
                "Promouvoir et accompagner l'allaitement maternel exclusif jusqu'à 6 mois",
                "Pratiquer les soins du cordon ombilical et la prévention des infections néonatales",
                "Appliquer les techniques de maintien de la température chez le prématuré",
                "Éduquer les mères sur l'hygiène, le sevrage et la diversification alimentaire"
            ),
            colorHex = 0xFF059669
        ),
        Subject(
            id = "gestion",
            title = "Gestion",
            iconEmoji = "📋",
            subtitle = "Organisation des soins, éthique & pharmacie",
            description = "Principes de gestion et management pour l'infirmier responsable : organisation des services hospitaliers et de centre de santé, gestion des stocks de médicaments essentiels, tenue des registres et dossiers médicaux, éthique professionnelle et déontologie.",
            objectives = listOf(
                "Gérer la pharmacie d'unité de soins selon les règles FEFO (Premier périmé, premier sorti)",
                "Tenir rigoureusement les registres de soins et assurer les transmissions ciblées",
                "Appliquer les principes de déontologie infirmière et de secret professionnel",
                "Organiser les plannings et collaborer efficacement au sein de l'équipe soignante"
            ),
            colorHex = 0xFF4F46E5
        ),
        Subject(
            id = "revision_generale",
            title = "Révision générale",
            iconEmoji = "📚",
            subtitle = "Synthèse d'examen A2 & cas cliniques",
            description = "Regroupement transversal des notions fondamentales du programme A2 Sciences infirmières ITM : cas cliniques d'intégration, aide-mémoire des normes biologiques, calculs de doses et préparations aux épreuves certificatives.",
            objectives = listOf(
                "Intégrer les connaissances théoriques dans la résolution de cas cliniques pratiques",
                "Calculer les débits de perfusion et dilutions sans erreur",
                "Mémoriser les normes physiologiques essentielles pour le jury d'examen",
                "Réussir les épreuves théoriques et pratiques du diplôme A2"
            ),
            colorHex = 0xFF0D9488
        )
    )
}
