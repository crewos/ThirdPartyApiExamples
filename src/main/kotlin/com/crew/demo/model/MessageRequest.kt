package com.crew.demo.model

data class MessageRequest(
    val cardId: String,
    val groupId: String,
    val message: String
)