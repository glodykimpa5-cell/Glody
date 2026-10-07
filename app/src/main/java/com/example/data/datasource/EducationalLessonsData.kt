package com.example.data.datasource

import com.example.data.model.ContentSection
import com.example.data.model.DefinitionItem
import com.example.data.model.Lesson
import com.example.data.model.SourceReference

object EducationalLessonsData {
    val lessons: List<Lesson> = listOf(
        // TECHNIQUES DE SOINS INFIRMIERS
        Lesson(
            id = "soins_constantes",
            subjectId = "soins_infirmiers",
            title = "Prise et interprétation des constantes vitales",
            category = "Surveillance clinique",
            readTimeMinutes = 8,
            presentation = "La prise des constantes vitales constitue le geste clinique élémentaire de tout infirmier. Elle permet d'apprécier en temps réel le fonctionnement des grandes fonctions vitales (hémodynamique, respiratoire, thermique) et de détecter précocement une détérioration de l'état du patient.",
            definitions = listOf(
                DefinitionItem("Pouls", "Perception tactile du choc vibratoire de l'onde sanguine contre la paroi d'une artère superficielle comprimée sur un plan dur osseux."),
                DefinitionItem("Tension artérielle (TA)", "Force exercée par le sang sur la paroi des artères systémiques lors de la systole ventriculaire (PAS) et de la diastole (PAD)."),
                DefinitionItem("Fréquence respiratoire (FR)", "Nombre de cycles complets (inspiration + expiration) exécutés par un individu en une minute au repos."),
                DefinitionItem("Température corporelle", "Équilibre entre la production de chaleur (thermogenèse) et la déperdition (thermololyse) régulé par l'hypothalamus.")
            ),
            keyPoints = listOf(
                "Normes adulte au repos : Pouls 60-100 bpm ; FR 12-20 cpm ; TA 120/80 mmHg ; Température axillaire 36,5°C - 37,2°C",
                "Ne jamais prendre le pouls radial avec son propre pouce (risque de confondre son propre pouls)",
                "Compter la fréquence respiratoire discrètement sans avertir le patient pour éviter une modification volontaire du rythme",
                "La fièvre chez l'adulte est définie par une température axillaire ≥ 37,5°C ou rectale ≥ 38,0°C"
            ),
            contentSections = listOf(
                ContentSection(
                    title = "1. Mesure de la Tension Artérielle (TA)",
                    paragraphs = listOf(
                        "Le patient doit être assis ou couché, au repos complet depuis au moins 5 minutes, sans avoir fumé ni pris de caféine dans les 30 minutes précédentes.",
                        "Le brassard doit être adapté à la circonférence du bras et placé 2 cm au-dessus du pli du coude, le repère de l'artère brachiale aligné sur l'artère."
                    ),
                    bullets = listOf(
                        "Gonfler jusqu'à disparition du pouls radial + 30 mmHg",
                        "Dégonfler lentement à 2 mmHg par seconde",
                        "Phase I de Korotkoff = premier bruit net = Pression Artérielle Systolique (PAS)",
                        "Phase V de Korotkoff = disparition complète des bruits = Pression Artérielle Diastolique (PAD)"
                    )
                ),
                ContentSection(
                    title = "2. Mesure du Pouls et de la Respiration",
                    paragraphs = listOf(
                        "Le pouls est le plus fréquemment mesuré à l'artère radiale. Il faut apprécier sa fréquence, son rythme (régulier ou irrégulier) et son amplitude (filant ou bien frappé).",
                        "Chez un patient dyspnéique ou comateux, toujours associer la prise de la fréquence respiratoire à l'évaluation des signes de lutte (tirage sus-sternal, battement des ailes du nez, balancement thoraco-abdominal)."
                    ),
                    bullets = listOf(
                        "Tachycardie : > 100 bpm chez l'adulte au repos",
                        "Bradycardie : < 60 bpm chez l'adulte au repos",
                        "Polypnée / Tachypnée : > 20 cpm",
                        "Bradypnée : < 12 cpm"
                    )
                )
            ),
            nursingRole = listOf(
                "Vérifier le bon étalonnage du tensiomètre et la propreté du thermomètre",
                "Rassurer le patient pour limiter l'effet « blouse blanche »",
                "Inscrire immédiatement les valeurs obtenues sur la feuille de température / dossier de soins",
                "Alerter sans délai le médecin en cas d'hypotension sévère (PAS < 90 mmHg) ou d'hyperthermie majeure (> 39,5°C)"
            ),
            summary = "La prise rigoureuse des constantes vitales (pouls, TA, FR, T°) est la base de l'observation infirmière. Toute anomalie observée doit être corrélée à l'état clinique général du malade et immédiatement consignée et transmise.",
            sources = listOf(
                SourceReference("Recommandations pour la prise en charge clinique et surveillance", "Organisation mondiale de la Santé (OMS)", "2020", "Directives de pratique clinique"),
                SourceReference("Guide des techniques de soins infirmiers pour les ITM", "Ministère de la Santé Publique RDC - Direction de l'Enseignement des Sciences de Santé", "2019", "Programme national A2")
            )
        ),
        Lesson(
            id = "soins_injections",
            subjectId = "soins_infirmiers",
            title = "Techniques d'administration des injections et sécurité",
            category = "Administration médicamenteuse",
            readTimeMinutes = 9,
            presentation = "L'administration parentérale des médicaments est un acte infirmier majeur exigeant le respect scrupuleux des 5 B (Bon patient, Bon médicament, Bonne dose, Bonne voie, Bon moment) ainsi qu'une asepsie rigoureuse afin d'éviter les abcès d'inoculation et les accidents iatrogènes.",
            definitions = listOf(
                DefinitionItem("Voie Intramusculaire (IM)", "Introduction d'un liquide médicamenteux dans la masse musculaire profonde, richement vascularisée."),
                DefinitionItem("Voie Sous-Cutanée (SC)", "Injection dans le tissu conjonctif lâche sous-cutané (hypoderme)."),
                DefinitionItem("Voie Intraveineuse (IV)", "Introduction directe d'une solution stérile dans la lumière d'une veine périphérique ou centrale.")
            ),
            keyPoints = listOf(
                "Respecter la règle des 5 Bons avant toute préparation",
                "Angle d'insertion : 90° pour l'IM ; 45° ou 90° (avec pli cutané) pour la SC ; 15° à 30° pour l'IV",
                "Pour l'IM au quadrant fessier : injecter strictement dans le quadrant supéro-externe pour préserver le nerf sciatique",
                "Ne jamais recapuchonner une aiguille souillée (prévention des accidents d'exposition au sang - AES)"
            ),
            contentSections = listOf(
                ContentSection(
                    title = "1. Préparation et asepsie",
                    paragraphs = listOf(
                        "Effectuer un lavage antiseptique des mains ou une friction hydro-alcoolique. Vérifier l'étiquette de l'ampoule, la date de péremption et l'absence de turbidité.",
                        "Désinfecter l'opercule ou le col de l'ampoule avec une compresse imbibée d'alcool à 70° ou de polyvidone iodée."
                    )
                ),
                ContentSection(
                    title = "2. Repérage des sites anatomiques",
                    paragraphs = listOf(
                        "Pour l'injection intramusculaire chez l'adulte : quadrant supéro-externe de la fesse, face antéro-latérale de la cuisse (muscle vaste externe), ou muscle deltoïde pour les petits volumes (< 1 ml).",
                        "Toujours aspirer légèrement avant d'injecter en IM : en cas de retour sanguin, retirer l'aiguille immédiatement et recommencer."
                    ),
                    bullets = listOf(
                        "IM : volume maximum 3 à 5 ml dans la fesse de l'adulte",
                        "SC : héparine, insuline (abdomen, cuisses, bras)",
                        "Éliminer les aiguilles dans la boîte de sécurité rigide dès la fin du geste"
                    )
                )
            ),
            nursingRole = listOf(
                "Informer le patient du médicament administré et de ses effets attendus",
                "Surveiller le patient au moins 15 minutes après l'injection (risque de choc anaphylactique)",
                "Noter la date, l'heure, le site d'injection et signer la feuille de traitement",
                "Assurer la gestion rigoureuse et sécurisée des déchets d'activités de soins à risques infectieux (DASRI)"
            ),
            summary = "La sécurité des injections repose sur l'application méthodique des règles d'asepsie, le repérage anatomique précis (quadrant supéro-externe) et la vigilance pharmacologique permanente de l'infirmier.",
            sources = listOf(
                SourceReference("Guide pratique de sécurité des injections", "Organisation mondiale de la Santé (OMS)", "2018", "Protocoles de prévention des AES"),
                SourceReference("Techniques de soins infirmiers fondamentales", "Manuel ITM RDC", "2021", "Module Pharmacologie et Soins")
            )
        ),

        // OBSTÉTRIQUE
        Lesson(
            id = "obstetrique_preeclampsie",
            subjectId = "obstetrique",
            title = "La Pré-éclampsie : Dépistage, gravité et rôle infirmier",
            category = "Complications obstétricales",
            readTimeMinutes = 10,
            presentation = "La pré-éclampsie est une complication majeure de la seconde moitié de la grossesse (après 20 semaines d'aménorrhée). Elle associe une hypertension artérielle gravidique à une protéinurie significative. C'est l'une des premières causes de mortalité maternelle et périnatale en RDC si elle n'est pas dépistée et traitée à temps.",
            definitions = listOf(
                DefinitionItem("Pré-éclampsie", "Association d'une PAS ≥ 140 mmHg et/ou PAD ≥ 90 mmHg survenant après 20 SA chez une femme auparavant normotendue, avec une protéinurie ≥ 2+ à la bandelette ou ≥ 0,3 g/24h."),
                DefinitionItem("Éclampsie", "Survenue de crises convulsives tonico-cloniques généralisées chez une femme enceinte pré-éclamptique, non imputables à une autre cause neurologique."),
                DefinitionItem("Protéinurie gravidique", "Présence anormale de protéines dans les urines, témoin de l'atteinte glomérulaire endothéliale.")
            ),
            keyPoints = listOf(
                "Toute PAD ≥ 90 mmHg après 20 SA impose la recherche immédiate d'une protéinurie à la bandelette",
                "Signes fonctionnels d'alerte (imminence d'éclampsie) : céphalées tenaces, troubles visuels (phosphènes, flou), acouphènes, barre épigastrique",
                "Médicament de référence pour la prévention et le traitement des crises d'éclampsie : Sulfate de Magnésium (MgSO4)",
                "L'unique traitement curatif définitif est l'interruption de la grossesse (délivrance fœto-placentaire)"
            ),
            contentSections = listOf(
                ContentSection(
                    title = "1. Critères de gravité clinique",
                    paragraphs = listOf(
                        "Une pré-éclampsie est dite sévère en présence d'au moins un des critères suivants : PAS ≥ 160 mmHg ou PAD ≥ 110 mmHg, protéinurie massive (> 3+), oligurie (< 500 ml/24h), œdème aigu du poumon, ou signes neurologiques précurseurs d'éclampsie.",
                        "Le HELLP syndrome (Hémolyse, élévation des enzymes hépatiques, thrombopénie) constitue une complication hématologique et hépatique gravissime."
                    )
                ),
                ContentSection(
                    title = "2. Protocole du Sulfate de Magnésium (MgSO4)",
                    paragraphs = listOf(
                        "Le protocole standard comprend une dose de charge (habituellement 4 g IV lente sur 15-20 min associée à 10 g IM répartis dans chaque fesse), suivie d'une dose d'entretien de 5 g IM toutes les 4 heures en alternant les fesses jusqu'à 24h après l'accouchement.",
                        "Une surveillance stricte des signes de toxicité du magnésium est impérative avant chaque injection d'entretien."
                    ),
                    bullets = listOf(
                        "Présence obligatoire du réflexe rotulien (patellaire)",
                        "Fréquence respiratoire ≥ 16 cycles par minute",
                        "Diurèse horaire ≥ 30 ml/heure (sonde vésicale à demeure)",
                        "Antidote prêt à portée de main : Gluconate de Calcium 10% (1 g IV lent)"
                    )
                )
            ),
            nursingRole = listOf(
                "Installer la patiente au calme, en décubitus latéral gauche (DLG) pour favoriser le flux utéro-placentaire",
                "Poser une voie veineuse de gros calibre (16G ou 18G) et une sonde urinaire pour surveiller la diurèse horaire",
                "Surveiller les constantes (TA, pouls, FR, réflexes rotuliens) toutes les 15 à 30 minutes",
                "Préparer le matériel de réanimation néonatale et maternelle (aspiration, oxygène, MgSO4, Gluconate de calcium)"
            ),
            summary = "La pré-éclampsie est une urgence médico-obstétricale. L'infirmier A2 joue un rôle capital dans son dépistage à chaque consultation prénatale (TA + bandelette urinaire) et dans l'administration rigoureusement surveillée du sulfate de magnésium.",
            sources = listOf(
                SourceReference("Recommandations pour la prévention et le traitement de la pré-éclampsie et de l'éclampsie", "Organisation mondiale de la Santé (OMS)", "2021", "Directives de santé maternelle"),
                SourceReference("Protocole National de Prise en Charge des Urgences Obstétricales", "Ministère de la Santé Publique RDC", "2020", "Programme National de Santé de la Reproduction (PNSR)")
            )
        ),
        Lesson(
            id = "obstetrique_cpn",
            subjectId = "obstetrique",
            title = "Consultation Prénatale (CPN) recentrée",
            category = "Suivi de la grossesse",
            readTimeMinutes = 7,
            presentation = "La consultation prénatale (CPN) est l'occasion privilégiée pour surveiller la santé de la femme enceinte et du fœtus, dépister les grossesses à risque, administrer les soins préventifs (TPI contre le paludisme, fer/folates, VAT) et préparer le plan d'accouchement.",
            definitions = listOf(
                DefinitionItem("CPN recentrée", "Modèle de soins prénatals préconisant un minimum de contacts de qualité (modèle OMS à 8 contacts) axé sur la détection précoce des risques et les interventions préventives éprouvées."),
                DefinitionItem("TPI (Traitement Préventif Intermittent)", "Administration systématique de Sulfadoxine-Pyriméthamine (SP) dès le 2e trimestre de la grossesse espacée d'au moins 1 mois pour prévenir le paludisme gestationnel."),
                DefinitionItem("Hauteur utérine (HU)", "Distance mesurée au ruban métrique entre le bord supérieur de la symphyse pubienne et le fond utérin.")
            ),
            keyPoints = listOf(
                "Mesure de la hauteur utérine : HU = Âge gestationnel en mois x 4 cm (entre 4 et 7 mois environ)",
                "Dépistage systématique du VIH, de la syphilis et de l'anémie à la 1re CPN",
                "Administration de la SP sous observation directe (TDO) dès le début des mouvements fœtaux",
                "Élaboration d'un plan d'accouchement individualisé avec identification du lieu de naissance et des moyens de transport"
            ),
            contentSections = listOf(
                ContentSection(
                    title = "1. Examen clinique de la gestante",
                    paragraphs = listOf(
                        "L'examen comprend la prise du poids, de la tension artérielle, la recherche des œdèmes des membres inférieurs et des signes d'anémie clinique (conjonctives pâles).",
                        "L'examen obstétrical comprend la palpation des manœuvres de Léopold (présentation fœtale), la mesure de la hauteur utérine et l'auscultation des bruits du cœur fœtal (BCF au stéthoscope de Pinard ou doppler)."
                    )
                ),
                ContentSection(
                    title = "2. Interventions préventives majeures en RDC",
                    paragraphs = listOf(
                        "En zone endémique comme la RDC, le paludisme et l'anémie constituent les risques majeurs pour la mère et le fœtus.",
                        "Les interventions gratuites ou subventionnées doivent être impérativement tracées dans le carnet de santé maternelle."
                    ),
                    bullets = listOf(
                        "Distribution d'une Moustiquaire Imprégnée d'Insecticide à Longue Durée d'Action (MIILDA)",
                        "Supplémentation en Fer (60 mg) et Acide folique (400 mcg) par jour",
                        "Vaccination antitétanique (VAT) selon le statut vaccinal",
                        "Déparasitage systématique au mébendazole au 2e trimestre"
                    )
                )
            ),
            nursingRole = listOf(
                "Accueillir la gestante avec empathie et respect de la confidentialité",
                "Remplir soigneusement la fiche de CPN et le carnet de santé de la mère",
                "Informer sur les signes de danger de la grossesse (saignements, fièvre, écoulement de liquide, céphalées)",
                "Sensibiliser à l'accouchement assisté en structure de santé qualifiée"
            ),
            summary = "La CPN est une passerelle stratégique de réduction de la morbi-mortalité maternelle. Elle combine examen clinique rigoureux, interventions préventives fondamentales et éducation thérapeutique.",
            sources = listOf(
                SourceReference("Recommandations de l'OMS sur les soins prénatals pour une expérience positive de la grossesse", "OMS", "2018", "Lignes directrices mondiales"),
                SourceReference("Guide du prestataire en CPN recentrée", "Ministère de la Santé Publique RDC", "2019", "PNSR")
            )
        ),

        // PATHOLOGIE
        Lesson(
            id = "patho_paludisme",
            subjectId = "pathologie",
            title = "Paludisme grave et simple : Physiopathologie, prise en charge et rôle infirmier",
            category = "Maladies infectieuses",
            readTimeMinutes = 9,
            presentation = "Le paludisme (malaria) est une érythrocytopathie parasitaire fébrile causée par Plasmodium falciparum, transmise par la piqûre de l'anophèle femelle. En République démocratique du Congo, il représente la première cause de consultation hospitalière et de mortalité chez les enfants de moins de 5 ans.",
            definitions = listOf(
                DefinitionItem("Paludisme simple", "Accès palustre avec confirmation biologique (TDR ou goutte épaisse positive) sans signe de défaillance vitale ni atteinte neurologique."),
                DefinitionItem("Paludisme grave", "Accès palustre à Plasmodium falciparum avec présence d'au moins un critère de gravité clinique ou biologique (coma, détresse respiratoire, anémie sévère, collapsus, hémoglobinurie, ictère, hypoglycémie)."),
                DefinitionItem("Neuropaludisme", "Forme encéphalopathique du paludisme grave définie par un coma fébrile (score de Blantyre ≤ 2 chez l'enfant ou Glasgow < 11 chez l'adulte) persistant après correction d'une hypoglycémie.")
            ),
            keyPoints = listOf(
                "Le diagnostic biologique (TDR ou Frottis/Goutte épaisse) est obligatoire avant tout traitement antipaludique",
                "Traitement de première intention du paludisme simple : CTA (Combinaison Thérapeutique à base d'Artémisinine) per os (ex. Artéméther-Luméfantrine ou Artésunate-Amodiaquine)",
                "Traitement de référence du paludisme grave : Artésunate injectable (IV ou IM) dosé à 2,4 mg/kg (ou 3 mg/kg chez l'enfant < 20 kg) à H0, H12, H24 puis toutes les 24h",
                "Toujours rechercher et corriger systématiquement l'hypoglycémie devant tout trouble de la conscience"
            ),
            contentSections = listOf(
                ContentSection(
                    title = "1. Signes de gravité selon les critères OMS",
                    paragraphs = listOf(
                        "L'infirmier doit rechercher méthodiquement les critères de défaillance suivants :",
                        "Atteinte cérébrale (coma, convulsions répétées), acidose métabolique (respiration ample de Kussmaul), anémie sévère (Hb < 5 g/dl chez l'enfant ou < 7 g/dl chez l'adulte), insuffisance rénale aiguë (oligurie), œdème pulmonaire et collapsus cardio-vasculaire."
                    )
                ),
                ContentSection(
                    title = "2. Protocole de l'Artésunate injectable",
                    paragraphs = listOf(
                        "L'artésunate injectable se prépare par dissolution de la poudre avec le solvant fourni (bicarbonate de sodium) puis dilution avec du sérum physiologique 0,9% ou glucosé 5%.",
                        "Dès que le patient tolère la voie orale et a reçu au moins 24h de traitement injectable, relayer impérativement par une cure complète de 3 jours de CTA par voie orale."
                    )
                )
            ),
            nursingRole = listOf(
                "Poser une voie veineuse périphérique et prélever pour TDR/GE, glycémie et hémogramme",
                "Surveiller l'état de conscience, la température, la fréquence respiratoire et la diurèse horaire",
                "Assurer les mesures de nursing chez le patient comateux : position latérale de sécurité, soins de bouche et prévention des escarres",
                "Sensibiliser la famille à l'utilisation systématique de la moustiquaire imprégnée"
            ),
            summary = "Le paludisme grave est une urgence médicale absolue en RDC. Le traitement précoce par artésunate injectable sauve des vies. La vigilance infirmière sur la glycémie, la respiration et la conscience est décisive.",
            sources = listOf(
                SourceReference("Lignes directrices de l'OMS pour la prise en charge du paludisme", "OMS", "2022", "Recommandations thérapeutiques internationales"),
                SourceReference("Guide national de prise en charge du paludisme en RDC", "Programme National de Lutte contre le Paludisme (PNLP)", "2021", "MSP RDC")
            )
        ),

        // PÉDIATRIE
        Lesson(
            id = "pediatrie_deshydratation",
            subjectId = "pediatrie",
            title = "Prise en charge de la déshydratation diarrhéique de l'enfant (PCIME)",
            category = "Urgences pédiatriques",
            readTimeMinutes = 8,
            presentation = "La maladie diarrhéique aiguë est une cause majeure de morbidité et de mortalité chez les nourrissons en milieu tropical. Le danger vital immédiat réside dans la déshydratation et le déséquilibre hydro-électrolytique. La stratégie PCIME (Prise en Charge Intégrée des Maladies de l'Enfant) permet une classification standardisée et un traitement efficace.",
            definitions = listOf(
                DefinitionItem("Diarrhée aiguë", "Émission d'au moins 3 selles liquides ou anormalement molles par 24 heures, évoluant depuis moins de 14 jours."),
                DefinitionItem("Pli cutané", "Test clinique évaluant l'élasticité cutanée au niveau de l'abdomen ; en cas de déshydratation, le pli s'efface lentement (> 2 secondes)."),
                DefinitionItem("SRO (Sels de Réhydratation Orale)", "Solution de réhydratation à osmolarité réduite (OMS) contenant sodium, potassium, chlorure, citrate et glucose.")
            ),
            keyPoints = listOf(
                "Classifier selon la PCIME : Déshydratation sévère (Plan C), Déshydratation modérée (Plan B), Pas de signe de déshydratation (Plan A)",
                "Deux signes parmi : léthargie/inconscience, yeux enfoncés, incapable de boire, pli cutané très lent = Plan C (Urgence IV)",
                "Associer systématiquement le Zinc pendant 10 à 14 jours (10 mg/j si < 6 mois, 20 mg/j si ≥ 6 mois)",
                "Ne jamais interrompre l'allaitement maternel pendant l'épisode diarrhéique"
            ),
            contentSections = listOf(
                ContentSection(
                    title = "1. Classification et plans de traitement",
                    paragraphs = listOf(
                        "Plan A : Traitement à domicile. Donner plus de liquides que d'habitude (SRO, eau de riz), continuer l'alimentation normale, prescrire le zinc et enseigner les signes de danger.",
                        "Plan B : Réhydratation orale au centre de santé. Donner des SRO (environ 75 ml/kg sur 4 heures) à la cuillère ou à la tasse. Réévaluer l'enfant au bout de 4 heures.",
                        "Plan C : Réhydratation intraveineuse urgente avec du Ringer Lactate (100 ml/kg) selon le protocole adapté à l'âge (plus rapide chez l'enfant de plus de 1 an)."
                    )
                )
            ),
            nursingRole = listOf(
                "Peser précisément l'enfant à l'admission pour calculer les volumes de réhydratation",
                "Apprendre à la maman à administrer le SRO lentement à la cuillère sans forcer",
                "Surveiller les signes de surcharge hydrique lors de la perfusion (fréquence respiratoire, œdèmes)",
                "Éduquer sur les mesures d'hygiène alimentaire et le lavage des mains à l'eau propre et au savon"
            ),
            summary = "La déshydratation diarrhéique de l'enfant se traite efficacement grâce aux protocoles standardisés PCIME : réhydratation précoce par SRO et Ringer Lactate, supplémentation en zinc et maintien impératif de l'alimentation.",
            sources = listOf(
                SourceReference("Prise en charge intégrée des maladies de l'enfant (PCIME) : Livret clinique", "OMS / UNICEF", "2019", "Guide mondial"),
                SourceReference("Guide pratique de prise en charge pédiatrique en RDC", "Ministère de la Santé Publique RDC", "2020", "Direction de la Santé de la Famille")
            )
        ),

        // GESTION
        Lesson(
            id = "gestion_pharmacie",
            subjectId = "gestion",
            title = "Gestion rationnelle des médicaments essentiels et de la pharmacie d'unité",
            category = "Administration & Logistique",
            readTimeMinutes = 7,
            presentation = "L'infirmier responsable de salle ou d'unité de soins assure la gestion du stock de médicaments et consommables médicaux. Une mauvaise gestion entraîne ruptures de stock, péremptions, gaspillages financiers et mise en danger des malades hospitalisés.",
            definitions = listOf(
                DefinitionItem("Médicament essentiel", "Médicament qui satisfait aux besoins de santé prioritaires de la majorité de la population, devant être disponible à tout moment en quantité suffisante."),
                DefinitionItem("Principe FEFO (First Expired, First Out)", "Règle logistique selon laquelle les produits dont la date d'expiration est la plus proche doivent être distribués ou utilisés en premier."),
                DefinitionItem("Fiche de stock", "Document de gestion indispensable enregistrant pour chaque médicament les entrées, sorties, pertes et solde disponible en temps réel.")
            ),
            keyPoints = listOf(
                "Appliquer strictement le principe FEFO (Premier Périmé, Premier Sorti)",
                "Tenir à jour une fiche de stock individuelle pour chaque forme et dosage de médicament",
                "Sécuriser l'armoire des stupéfiants et toxiques sous double clé sous la responsabilité directe de l'infirmier",
                "Vérifier la chaîne du froid pour les vaccins et insulines (température maintenue entre +2°C et +8°C)"
            ),
            contentSections = listOf(
                ContentSection(
                    title = "1. Règles de rangement et conservation",
                    paragraphs = listOf(
                        "Les médicaments doivent être rangés sur des étagères propres, aérées, à l'abri de l'humidité, de la poussière et des rayons directs du soleil.",
                        "Ne jamais poser les cartons de solutés ou de médicaments à même le sol (utiliser des palettes d'au moins 10 cm)."
                    )
                ),
                ContentSection(
                    title = "2. Réapprovisionnement et inventaire",
                    paragraphs = listOf(
                        "L'infirmier calcule la consommation moyenne mensuelle (CMM) pour déterminer le stock de sécurité et le stock d'alerte.",
                        "Un inventaire physique contradictoire doit être réalisé mensuellement pour réconcilier le stock théorique et le stock physique réel."
                    )
                )
            ),
            nursingRole = listOf(
                "Enregistrer immédiatement toute sortie de médicament sur la fiche de stock correspondante",
                "Relever quotidiennement la température du réfrigérateur de la pharmacie sur la fiche de contrôle",
                "Isoler sans délai tout médicament périmé ou altéré dans un carton clairement étiqueté pour destruction sécurisée",
                "Assurer la stricte conformité des prescriptions médicales avant délivrance"
            ),
            summary = "La gestion des médicaments en soins infirmiers repose sur l'organisation méthodique (FEFO), la traçabilité rigoureuse (fiches de stock) et le respect des normes de conservation pour garantir la sécurité des soins.",
            sources = listOf(
                SourceReference("Guide pratique de gestion des approvisionnements pharmaceutiques", "OMS", "2019", "Normes et directives pharmaceutiques"),
                SourceReference("Manuel de gestion des structures sanitaires de premier échelon", "MSP RDC", "2021", "Direction de la Pharmacie et du Médicament")
            )
        )
    )
}
