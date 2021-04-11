package com.elouyi.net

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

actual fun simpleGet(url: String): String {
    var res = ""
    runBlocking(Dispatchers.IO) {
        val client = HttpClient(CIO) {
            BrowserUserAgent()
        }
        res = client.get(url)
        client.close()
    }
    return res
}