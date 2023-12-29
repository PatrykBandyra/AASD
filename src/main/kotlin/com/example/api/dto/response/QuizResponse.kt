package com.example.api.dto.response

data class QuizResponse(
        val questions: List<QuizQuestion>
) {
    data class QuizQuestion(
            val question: String,
            val answers: List<QuizAnswer>
    ) {
        data class QuizAnswer(
                val name: String,
                val value: String
        )
    }
}
