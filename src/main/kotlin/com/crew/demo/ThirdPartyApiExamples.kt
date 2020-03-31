package com.crew.demo

import com.google.gson.Gson
import com.crew.demo.model.DemoConfiguration
import com.crew.demo.model.MessageRequest

fun main() {
    val fetchResource = FetchResource()
    val fileContent = fetchResource.getResource("config.json")
        ?: throw RuntimeException("There was a problem fetching the configuration json file.")
    val gson = Gson()
    val configuration = gson.fromJson(fileContent, DemoConfiguration::class.java)
    val thirdPartyService = ThirdPartyService(configuration.urlBase, configuration.oAuthToken)
    thirdPartyService.getAllOrganizationsForEnterprise(configuration.enterpriseId)
    thirdPartyService.getOrganizationById(configuration.organizationId)
    thirdPartyService.getAllMembersByOrganizationId(configuration.organizationId)
    thirdPartyService.getUserById(configuration.userId)
    thirdPartyService.getAllConversationsByOrganizationId(configuration.organizationId)
    thirdPartyService.sendMessage(
        MessageRequest(configuration.cardId, configuration.groupId, configuration.message)
    )
}