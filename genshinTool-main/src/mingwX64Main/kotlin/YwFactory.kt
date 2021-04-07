package com.elouyi

import com.elouyi.data.DataProducer
import com.elouyi.data.WishResponse

actual object YwFactory {
    actual fun createDataProducer(): DataProducer {
        return object : DataProducer {
            override suspend fun fetchData(url: String): WishResponse {
                TODO("Not yet implemented")
            }

            override suspend fun fetchAllData(): List<WishResponse> {
                TODO("Not yet implemented")
            }
        }
    }
}