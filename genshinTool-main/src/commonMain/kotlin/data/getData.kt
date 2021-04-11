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

fun getWishShowData(res: List<WishResponse>): WishShowData {
    return WishShowData().apply {
        endTime = res.first().data.list.first().time.run { slice(0 until indexOf(" ")) }
        startTime = res.last().data.list.last().time.run { slice(0 until indexOf(" ")) }
        res.reversed().forEach { wishRes ->
            wishRes.data.list.reversed().forEach {
                total++
                count5++
                when(it.rank_type) {
                    "5" -> {
                        if (it.item_type == "角色") ch_5++ else weapon_5++
                        s_5[it.name] = count5
                        count5 = 0
                    }
                    "4" -> if (it.item_type == "角色") ch_4++ else weapon_4++
                    "3" -> weapon_3++
                }
            }
        }
    }
}