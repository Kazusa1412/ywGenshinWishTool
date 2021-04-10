package com.elouyi.data

import com.elouyi.net.simpleGet
import com.elouyi.utils.deserialize
import com.elouyi.utils.save2File
import com.elouyi.utils.serialize

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
    val jsonFile = WishJsonFile(listOf(chList,weaponList,stdList))
    val jsonStr = serialize(jsonFile)
    val uid = chList[0].data.list[0].uid
    save2File("data/${uid}_wish_data.json",jsonStr)
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
        resList.add(res)
        mPage ++
        endId = res.data.list.last().id
    }
    return resList
}