package com.elouyi.data

interface DataProducer {

    suspend fun fetchData(url: String): WishResponse

    suspend fun fetchAllData(url: String): List<WishResponse>
}
