package com.elouyi.net

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.get
import io.ktor.client.request.*
import io.ktor.client.statement.*

suspend fun request(url: String,block: suspend HttpResponse.() -> Unit) {
    val client = HttpClient() {
        BrowserUserAgent()

    }
    client.get<HttpResponse>(url){
        header("Origin","https://webstatic.mihoyo.com")
        header("Referer","https://webstatic.mihoyo.com")
        header("Sec-Fetch-Site","same-site")
        header("Sec-Fetch-Mode","no-cors")
        headers["Sec-Fetch-Mode"] = "no-cors"
    }.block()
    client.close()
}