package com.crew.demo

class FetchResource {
    fun getResource(path: String): String? {
        return this::class.java.classLoader.getResource(path)?.readText()
    }
}