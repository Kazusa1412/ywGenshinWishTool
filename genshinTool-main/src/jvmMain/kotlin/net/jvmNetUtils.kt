package com.elouyi.net

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.HttpURLConnection
import java.net.URL

actual fun simpleGet(url: String): String {
    var res = ""

    val mUrl = URL(url)
    val connection = mUrl.openConnection() as HttpURLConnection
    connection.apply {
        requestMethod = "GET"
    }
    val input = connection.inputStream
    res = String(input.readBytes())
    input.close()
    return res

    // todo
    // jar包没问题，exe发行版报错
    // 把这个改成 suspend
    /*
    runBlocking(Dispatchers.Main) {
        val client = HttpClient(CIO) {
            BrowserUserAgent()
        }
        res = client.get(url)
        client.close()
    }
    return res

     */
}