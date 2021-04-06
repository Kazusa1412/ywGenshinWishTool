package com.elouyi.data

interface DataProducer {

    suspend fun fetchData(url: String): WishResponse

    suspend fun fetchAllData(): List<WishResponse>
}

expect object YwFactory {
    fun createDataProducer(): DataProducer
}