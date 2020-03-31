package com.crew.demo

import com.crew.demo.model.MessageRequest
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils

class ThirdPartyService(
    private val urlBase: String,
    private val oAuthToken: String
) {
    fun getAllOrganizationsForEnterprise(enterpriseId: String) {
        println("Getting all organizations for enterprise [$enterpriseId]")
        val enterpriseOrganizationsUrl = "$urlBase/connect/enterprises/$enterpriseId/organizations"
        getAndPrintResponse(enterpriseOrganizationsUrl)
    }

    fun getOrganizationById(organizationId: String) {
        println("Getting organization by id [$organizationId]")
        val organizationUrl = "$urlBase/connect/organizations/$organizationId"
        getAndPrintResponse(organizationUrl)
    }

    fun getAllMembersByOrganizationId(organizationId: String) {
        println("Getting all members for organization [$organizationId]")
        val organizationMembersUrl = "$urlBase/connect/organizations/$organizationId/members"
        getAndPrintResponse(organizationMembersUrl)
    }

    fun getUserById(userId: String) {
        println("Getting user [$userId]")
        val userUrl = "$urlBase/connect/users/$userId"
        getAndPrintResponse(userUrl)
    }

    fun getAllConversationsByOrganizationId(organizationId: String) {
        println("Getting all conversations for organization [$organizationId]")
        val organizationConversationsUrl = "$urlBase/connect/organizations/$organizationId/conversations"
        getAndPrintResponse(organizationConversationsUrl)
    }

    fun sendMessage(
        messageRequest: MessageRequest
    ) {
        println("Sending message to group [${messageRequest.groupId}] and card [${messageRequest.cardId}]")
        val messageUrl = "$urlBase/connect/messages"
        postAndPrintResponse(messageUrl, "")
    }

    private fun getAndPrintResponse(url: String) {
        val httpClient = HttpClients.createDefault()
        try {
            val httpRequest = httpGetRequest(url)

            httpClient.execute(httpRequest).use {
                println("Response Code: ${it.statusLine.statusCode}")
                it.entity?.let { entity ->
                    println("Response: ${EntityUtils.toString(entity)}")
                }
                println()
            }
        } catch (e: Exception) {
            println("Error: $e\n")
        } finally {
            httpClient.close()
        }
    }

    private fun httpGetRequest(url: String): HttpGet {
        val httpRequest = HttpGet(url)
        httpRequest.setHeader("Accept", "application/json")
        httpRequest.setHeader("Authorization", "Bearer $oAuthToken")
        return httpRequest
    }

    private fun postAndPrintResponse(url: String, body: String) {
        val httpClient = HttpClients.createDefault()
        try {
            val httpRequest = httpPostRequest(url, body)

            httpClient.execute(httpRequest).use {
                println("Response Code: ${it.statusLine.statusCode}")
                it.entity?.let { entity ->
                    println("Response: ${EntityUtils.toString(entity)}")
                }
                println()
            }
        } catch (e: Exception) {
            println("Error: $e\n")
        } finally {
            httpClient.close()
        }
    }

    private fun httpPostRequest(url: String, body: String): HttpPost {
        val httpRequest = HttpPost(url)
        httpRequest.setHeader("Accept", "application/json")
        httpRequest.setHeader("Content-Type", "application/json")
        httpRequest.setHeader("Authorization", "Bearer $oAuthToken")
        httpRequest.entity = StringEntity(body)
        return httpRequest
    }
}