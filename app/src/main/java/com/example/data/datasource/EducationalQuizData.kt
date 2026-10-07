package com.example.data.datasource

import com.example.data.model.QuizLevel
import com.example.data.model.QuizQuestion

object EducationalQuizData {
    val questions: List<QuizQuestion> = listOf(
        // Techniques de soins infirmiers
        QuizQuestion(
            id = "q_soins_1",
            subjectId = "soins_infirmiers",
            lessonId = "soins_constantes",
            question = "Quelle est la valeur normale de la fréquence cardiaque (pouls) chez un adulte sain au repos ?",
            optionA = "40 à 60 battements par minute",
            optionB = "60 à 100 battements par minute",
            optionC = "100 à 140 battements par minute",
            optionD = "80 à 120 battements par minute",
            correctAnswer = 1,
            explanation = "Chez l'adulte sain au repos, le pouls normal oscille entre 60 et 100 battements/minute. En dessous de 60 bpm, on parle de bradycardie ; au-dessus de 100 bpm, on parle de tachycardie.",
            level = QuizLevel.LEVEL_1
        ),
        QuizQuestion(
            id = "q_soins_2",
            subjectId = "soins_infirmiers",
            lessonId = "soins_injections",
            question = "Dans quel quadrant de la fesse doit être pratiquée une injection intramusculaire pour éviter de léser le nerf sciatique ?",
            optionA = "Quadrant supéro-interne",
            optionB = "Quadrant inféro-interne",
            optionC = "Quadrant supéro-externe",
            optionD = "Quadrant inféro-externe",
            correctAnswer = 2,
            explanation = "L'injection intramusculaire fessière doit toujours être effectuée dans le quadrant supéro-externe pour éviter tout risque de traumatisme ou de perforation du nerf sciatique.",
            level = QuizLevel.LEVEL_1
        ),
        QuizQuestion(
            id = "q_soins_3",
            subjectId = "soins_infirmiers",
            lessonId = "soins_constantes",
            question = "Lors de la mesure de la pression artérielle par méthode auscultatoire, à quoi correspond la Phase I de Korotkoff ?",
            optionA = "À la pression artérielle moyenne",
            optionB = "À la pression artérielle diastolique",
            optionC = "À la pression artérielle systolique",
            optionD = "À la pression différentielle",
            correctAnswer = 2,
            explanation = "Le premier bruit net perçu au stéthoscope lors du dégonflement du brassard correspond à la Phase I de Korotkoff et définit la Pression Artérielle Systolique (PAS).",
            level = QuizLevel.LEVEL_2
        ),

        // Obstétrique
        QuizQuestion(
            id = "q_obs_1",
            subjectId = "obstetrique",
            lessonId = "obstetrique_preeclampsie",
            question = "À partir de quel seuil tensionnel et de quel terme gestationnel définit-on l'hypertension artérielle gravidique ?",
            optionA = "PAS ≥ 130 et/ou PAD ≥ 85 mmHg avant 12 SA",
            optionB = "PAS ≥ 140 et/ou PAD ≥ 90 mmHg après 20 SA",
            optionC = "PAS ≥ 160 et/ou PAD ≥ 100 mmHg après 36 SA",
            optionD = "PAS ≥ 150 et/ou PAD ≥ 95 mmHg quel que soit le terme",
            correctAnswer = 1,
            explanation = "Selon les critères OMS, l'HTA gravidique est caractérisée par une PAS ≥ 140 mmHg et/ou une PAD ≥ 90 mmHg apparaissant après la 20e semaine d'aménorrhée chez une femme normotendue avant la grossesse.",
            level = QuizLevel.LEVEL_1
        ),
        QuizQuestion(
            id = "q_obs_2",
            subjectId = "obstetrique",
            lessonId = "obstetrique_preeclampsie",
            question = "Quel est le médicament de référence pour la prévention et le traitement des crises d'éclampsie ?",
            optionA = "Diazépam injectable",
            optionB = "Sulfate de magnésium (MgSO4)",
            optionC = "Furosémide IV",
            optionD = "Nifédipine per os",
            correctAnswer = 1,
            explanation = "Le sulfate de magnésium (MgSO4) est le traitement de choix validé par l'OMS pour prévenir et contrôler les convulsions d'éclampsie. Il est supérieur au diazépam.",
            level = QuizLevel.LEVEL_2
        ),
        QuizQuestion(
            id = "q_obs_3",
            subjectId = "obstetrique",
            lessonId = "obstetrique_preeclampsie",
            question = "Quel est l'antidote à préparer obligatoirement en cas de toxicité ou surdosage en Sulfate de Magnésium ?",
            optionA = "Vitamine K1",
            optionB = "Naloxone",
            optionC = "Gluconate de calcium 10%",
            optionD = "Atropine",
            correctAnswer = 2,
            explanation = "Le gluconate de calcium à 10% (1 g IV lent sur 5-10 minutes) est l'antidote spécifique du sulfate de magnésium en cas de disparition des réflexes rotuliens ou de bradypnée < 16 cpm.",
            level = QuizLevel.LEVEL_3
        ),
        QuizQuestion(
            id = "q_obs_4",
            subjectId = "obstetrique",
            lessonId = "obstetrique_cpn",
            question = "En RDC, à partir de quel trimestre de grossesse doit-on administrer le Traitement Préventif Intermittent (TPI) à la Sulfadoxine-Pyriméthamine ?",
            optionA = "Dès la 4e semaine (1er trimestre)",
            optionB = "Dès le 2e trimestre (après 13 SA ou dès la perception des mouvements fœtaux)",
            optionC = "Uniquement au 3e trimestre (après 32 SA)",
            optionD = "Pendant le travail d'accouchement",
            correctAnswer = 1,
            explanation = "Le TPI-SP débute obligatoirement au début du 2e trimestre de la grossesse (dès 13 SA ou dès les premiers mouvements fœtaux) et chaque dose doit être espacée d'au moins 4 semaines jusqu'à l'accouchement.",
            level = QuizLevel.LEVEL_2
        ),

        // Pathologie
        QuizQuestion(
            id = "q_patho_1",
            subjectId = "pathologie",
            lessonId = "patho_paludisme",
            question = "Quel est le traitement médicamenteux de première intention recommandé par l'OMS pour le paludisme grave ?",
            optionA = "Quinine per os",
            optionB = "Chloroquine injectable",
            optionC = "Artésunate injectable (IV ou IM)",
            optionD = "Paracétamol en perfusion",
            correctAnswer = 2,
            explanation = "L'artésunate injectable (IV ou IM à 2,4 mg/kg chez l'adulte ou 3,0 mg/kg chez l'enfant < 20 kg) est le traitement de choix du paludisme grave, plus efficace et moins toxique que la quinine.",
            level = QuizLevel.LEVEL_2
        ),
        QuizQuestion(
            id = "q_patho_2",
            subjectId = "pathologie",
            lessonId = "patho_paludisme",
            question = "Devant tout patient comateux suspect de paludisme grave, quelle anomalie biologique fréquente doit être immédiatement vérifiée et corrigée ?",
            optionA = "Hyperkaliémie",
            optionB = "Hypoglycémie",
            optionC = "Hypernatrémie",
            optionD = "Hypercalcémie",
            correctAnswer = 1,
            explanation = "L'hypoglycémie est une complication fréquente et létale du paludisme grave (consommation accrue de glucose par le parasite et altération de la néoglucogenèse hépatique). Elle doit être systématiquement dépistée et traitée avec du sérum glucosé à 10% ou 50%.",
            level = QuizLevel.LEVEL_3
        ),

        // Pédiatrie
        QuizQuestion(
            id = "q_ped_1",
            subjectId = "pediatrie",
            lessonId = "pediatrie_deshydratation",
            question = "Selon le protocole PCIME, quel soluté de perfusion est recommandé en première intention pour réhydrater un enfant en Plan C (déshydratation sévère) ?",
            optionA = "Sérum glucosé pur à 5%",
            optionB = "Ringer Lactate (ou Sérum physiologique à 0,9%)",
            optionC = "Sérum salé hypertonique à 3%",
            optionD = "Plasma frais congelé",
            correctAnswer = 1,
            explanation = "Le Ringer Lactate est le soluté de réanimation hydro-électrolytique de choix pour le Plan C en pédiatrie car sa composition est la plus proche du sérum extracellulaire et contient des précurseurs de bicarbonates.",
            level = QuizLevel.LEVEL_2
        ),
        QuizQuestion(
            id = "q_ped_2",
            subjectId = "pediatrie",
            lessonId = "pediatrie_deshydratation",
            question = "Pendant combien de jours doit-on administrer la supplémentation en Zinc lors d'un épisode de diarrhée aiguë chez l'enfant ?",
            optionA = "1 à 2 jours",
            optionB = "10 à 14 jours",
            optionC = "30 jours complets",
            optionD = "Uniquement le jour de l'admission",
            correctAnswer = 1,
            explanation = "Le sulfate de zinc doit être poursuivi pendant 10 à 14 jours consécutifs pour raccourcir la durée de la diarrhée et prévenir de nouvelles récidives dans les 2 à 3 mois suivants.",
            level = QuizLevel.LEVEL_1
        ),

        // Gestion
        QuizQuestion(
            id = "q_gest_1",
            subjectId = "gestion",
            lessonId = "gestion_pharmacie",
            question = "Que signifie la règle logistique de gestion FEFO dans la pharmacie d'une unité de soins ?",
            optionA = "Premier entré, premier sorti",
            optionB = "Premier périmé, premier sorti (First Expired, First Out)",
            optionC = "Plus cher, premier sorti",
            optionD = "Dernier entré, premier sorti",
            correctAnswer = 1,
            explanation = "FEFO signifie First Expired, First Out (Premier Périmé, Premier Sorti). Les boîtes dont la date de péremption est la plus rapprochée doivent être placées devant et dispensées en premier.",
            level = QuizLevel.LEVEL_1
        ),
        QuizQuestion(
            id = "q_gest_2",
            subjectId = "gestion",
            lessonId = "gestion_pharmacie",
            question = "Dans quelle plage de température doivent être conservés les vaccins et produits thermosensibles dans la chaîne du froid hospitalière ?",
            optionA = "-10°C à 0°C",
            optionB = "+2°C à +8°C",
            optionC = "+15°C à +25°C",
            optionD = "0°C à +2°C",
            correctAnswer = 1,
            explanation = "La chaîne du froid vaccinale et des médicaments thermolabiles (ex: oxytocine, insuline entamée) exige une température rigoureusement maintenue entre +2°C et +8°C.",
            level = QuizLevel.LEVEL_2
        )
    )
}
