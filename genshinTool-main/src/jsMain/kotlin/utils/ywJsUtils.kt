package com.elouyi.utils

import com.elouyi.data.WishResponse
import kotlinx.browser.window
import kotlinx.coroutines.await
import org.w3c.fetch.NO_CORS
import org.w3c.fetch.RequestInit
import org.w3c.fetch.RequestMode

suspend fun request(url: String) = window.fetch(url, RequestInit().apply {
    mode = RequestMode.NO_CORS
}).await().json().await() as WishResponse

actual fun save2File(filePath: String,content: String) {
    TODO()
}