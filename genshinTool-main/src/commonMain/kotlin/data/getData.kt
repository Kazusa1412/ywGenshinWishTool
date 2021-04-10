package com.elouyi.data

/**
 * 由 [com.elouyi.YwFactory] 构建
 */
interface DataProducer {

    suspend fun fetchData(url: String): WishResponse

    suspend fun fetchAllData(url: String): List<WishResponse>
}

