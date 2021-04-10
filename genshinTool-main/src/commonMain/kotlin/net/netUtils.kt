package com.elouyi.net

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.get
import io.ktor.client.request.*
import io.ktor.client.statement.*

/**
 * 公共 http get 请求方法，但在三个平台都有差异
 */
@Deprecated("使用对应平台的实现，此方法不适用于所有平台")
suspend fun request(url: String,block: suspend HttpResponse.() -> Unit) {
    val client = HttpClient {
        // 浏览器 userAgent
        BrowserUserAgent()
    }
    client.get<HttpResponse>(url).block()
    client.close()
}