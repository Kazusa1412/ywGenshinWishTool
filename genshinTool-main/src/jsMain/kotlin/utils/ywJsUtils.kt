package com.elouyi.utils

import kotlinx.browser.window
import kotlinx.coroutines.await

suspend fun request(url: String) = window.fetch(url).await().text().await().toString()