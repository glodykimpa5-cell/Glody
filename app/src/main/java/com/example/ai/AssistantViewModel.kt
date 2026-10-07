package com.example.ai

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ui.screens.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AssistantUiState(
    val messages: List<ChatMessage> = listOf(
        ChatMessage(
            id = "welcome",
            isUser = false,
            text = """
Bonjour ! Je suis l'**ASSISTANT GLODY 🤖**, ton tuteur pédagogique pour les sciences infirmières niveau A2 (ITM).

Pose-moi une question sur une pathologie, un geste de soins, l'obstétrique, ou utilise les boutons ci-dessous !

Pour chaque notion, je te présenterai :
1. Définition
2. Causes / facteurs de risque
3. Signes et symptômes
4. Complications
5. Principes de prise en charge
6. Rôle infirmier
7. Points importants
8. Petit QCM avec correction

⚠️ *Outil pédagogique A2 : ne remplace pas l'avis d'un professionnel ou les protocoles officiels.*
            """.trimIndent()
        )
    ),
    val inputText: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class AssistantViewModel(
    private val aiAssistant: GlodyAiAssistant = GlodyAiAssistant()
) : ViewModel() {

    private val _uiState = MutableStateFlow(AssistantUiState())
    val uiState: StateFlow<AssistantUiState> = _uiState.asStateFlow()

    fun onInputTextChanged(newText: String) {
        _uiState.update { it.copy(inputText = newText) }
    }

    fun sendMessage(prompt: String? = null, modifierMode: String = "") {
        val query = (prompt ?: _uiState.value.inputText).trim()
        if (query.isBlank() || _uiState.value.isLoading) return

        val userMessage = ChatMessage(
            id = System.currentTimeMillis().toString(),
            isUser = true,
            text = query
        )

        _uiState.update { current ->
            current.copy(
                messages = current.messages + userMessage,
                inputText = "",
                isLoading = true,
                errorMessage = null
            )
        }

        viewModelScope.launch {
            val result = aiAssistant.askAssistant(query, modifierMode)
            val replyText = result.getOrElse {
                "Désolé, une erreur s'est produite lors de la génération de la réponse pédagogique. Veuillez réessayer."
            }

            val assistantMessage = ChatMessage(
                id = (System.currentTimeMillis() + 1).toString(),
                isUser = false,
                text = replyText
            )

            _uiState.update { current ->
                current.copy(
                    messages = current.messages + assistantMessage,
                    isLoading = false
                )
            }
        }
    }

    fun handleQuickAction(actionType: String) {
        val currentInput = _uiState.value.inputText.trim()
        when (actionType) {
            "SIMPLE" -> {
                val subject = if (currentInput.isNotBlank()) currentInput else "la pré-éclampsie"
                sendMessage("Explique simplement : $subject", "EXPLICATION SIMPLE")
            }
            "DETAILED" -> {
                val subject = if (currentInput.isNotBlank()) currentInput else "le paludisme grave"
                sendMessage("Explication détaillée approfondie : $subject", "EXPLICATION DÉTAILLÉE")
            }
            "SUMMARY" -> {
                val subject = if (currentInput.isNotBlank()) currentInput else "les constantes vitales"
                sendMessage("Fais un résumé synthétique de : $subject", "FAIRE UN RÉSUMÉ")
            }
            "QUIZ" -> {
                val subject = if (currentInput.isNotBlank()) currentInput else "les soins infirmiers"
                sendMessage("Donne-moi un QCM avec 4 choix et explication sur : $subject", "ME DONNER UN QCM")
            }
        }
    }
}
