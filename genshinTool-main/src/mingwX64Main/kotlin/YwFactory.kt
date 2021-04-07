package com.elouyi

import com.elouyi.data.DataProducer
import com.elouyi.data.WishResponse

actual object YwFactory {
    actual fun createDataProducer() = object : DataProducer {
        override suspend fun fetchData(url: String): WishResponse {
            throw Exception("native 还没协程")
        }

        override suspend fun fetchAllData(url: String): List<WishResponse> {
            throw Exception("native 还没协程")
        }
    }
}