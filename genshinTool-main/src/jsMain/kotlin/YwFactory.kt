package com.elouyi

import com.elouyi.data.DataProducer
import com.elouyi.data.IUrlDataFromMap
import com.elouyi.data.CommonUrlDataFromMap
import com.elouyi.data.WishResponse
import kotlinx.browser.window
import kotlinx.coroutines.await

actual object YwFactory {

    actual fun createDataProducer(): DataProducer = object : DataProducer {

        override suspend fun fetchData(url: String): WishResponse = window.fetch(url).await().json().await() as WishResponse

        override suspend fun fetchAllData(url: String): List<WishResponse> {
            TODO("Not yet implemented")
        }
    }

    actual fun urlDataFromMap(): IUrlDataFromMap {
        return CommonUrlDataFromMap
    }

}