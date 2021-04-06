package com.elouyi.data

data class SingleData(
    val uid: String,
    val gachaType: String,
    val itemId: String,
    val count: String,
    val time: String,
    val name: String,
    val lang: String,
    val itemType: String,
    val rankType: String,
    val id: String,
)

data class WishData(
    val page: String,
    val size: String,
    val total: String,
    val list: List<SingleData>
)

data class WishResponse(
    val retcode: Int,
    val message: String,
    val data: WishData
)