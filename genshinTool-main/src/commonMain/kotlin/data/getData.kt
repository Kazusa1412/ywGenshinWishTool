package com.elouyi.data

import com.elouyi.net.simpleGet
import com.elouyi.utils.deserialize

/**
 * 由 [com.elouyi.YwFactory] 构建
 */
@Deprecated("没啥用")
interface DataProducer {

    suspend fun fetchData(url: String): WishResponse

    suspend fun fetchAllData(url: String): List<WishResponse>
}

fun getWishData(data: UrlData) {
    val chList = getSingleWishData(data,GachaType.character)
    val weaponList = getSingleWishData(data,GachaType.weapon)
    val stdList = getSingleWishData(data,GachaType.standard)
}

fun getSingleWishData(data: UrlData,type: Int): List<WishResponse> {
    var mPage = 0
    var endId = "0"
    val resList = mutableListOf<WishResponse>()
    while (true) {
        val wishUrl = buildWishUrl {
            withUrlData(data)
            page = mPage
            end_id = endId
            gacha_type = type
        }
        val resStr = simpleGet(wishUrl)
        val res = deserialize<WishResponse>(resStr)
        if (res.data.list.isEmpty()) {
            break
        }
        println(res)
        resList.add(res)
        mPage ++
        endId = res.data.list.last().id
    }
    return resList
}