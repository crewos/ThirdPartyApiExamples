package com.crew.demo.model

data class DemoConfiguration(
    val oAuthToken: String,
    val urlBase: String,
    val enterpriseId: String,
    val organizationId: String,
    val userId: String,
    val groupId: String,
    val cardId: String,
    val message: String
)