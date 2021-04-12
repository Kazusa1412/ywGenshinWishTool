package com.elouyi.utils

import com.elouyi.App
import com.elouyi.data.WishJsonFile
import com.elouyi.data.WishResponse
import com.elouyi.data.buildEchartsOption
import com.elouyi.data.getWishShowData
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.await
import org.w3c.fetch.NO_CORS
import org.w3c.fetch.RequestInit
import org.w3c.fetch.RequestMode

suspend fun request(url: String) = window.fetch(url, RequestInit().apply {
    mode = RequestMode.NO_CORS
}).await().json().await() as WishResponse

actual fun save2File(filePath: String,content: String) {
    throw Exception()
}

fun generateOption(da: WishJsonFile): dynamic = buildEchartsOption {
    var ch = listOf<WishResponse>()
    var weapon = listOf<WishResponse>()
    var std = listOf<WishResponse>()
    for (d in da.data) {
        if (d.isEmpty()) continue
        when(d[0].data.list[0].gacha_type) {
            "301" -> ch = d
            "302" -> weapon = d
            "200" -> std = d
        }
    }
    val d = listOf(ch,weapon,std)
    val chw = getWishShowData(ch)
    val weaponw = getWishShowData(weapon)
    val stdw = getWishShowData(std)
    val items = arrayOf("5⭐角色","5⭐武器","4⭐角色","4⭐武器","3⭐武器")
    legend {
        orient = "horizontal"
        legendData = items
    }
    series {
        sets(
            {
                type = "pie"
                left = "0%"
                width = "30%"
                top = "8%"
                seriesDatas(
                    {
                        value = chw.ch_5
                        name = items[0]
                    },
                    {
                        value = chw.ch_4
                        name = items[2]
                    },
                    {
                        value = chw.weapon_4
                        name = items[3]
                    },
                    {
                        value = chw.weapon_3
                        name = items[4]
                    }
                )
            },
            {
                type = "pie"
                left = "30%"
                width = "30%"
                top = "8%"
                seriesDatas(
                    {
                        value = weaponw.weapon_5
                        name = items[1]
                    },
                    {
                        value = weaponw.ch_4
                        name = items[2]
                    },
                    {
                        value = weaponw.weapon_4
                        name = items[3]
                    },
                    {
                        value = weaponw.weapon_3
                        name = items[4]
                    }
                )
            },
            {
                type = "pie"
                left = "60%"
                width = "30%"
                top = "8%"
                seriesDatas(
                    {
                        value = stdw.ch_5
                        name = items[0]
                    },
                    {
                        value = stdw.weapon_5
                        name = items[1]
                    },
                    {
                        value = stdw.ch_4
                        name = items[2]
                    },
                    {
                        value = stdw.weapon_4
                        name = items[3]
                    },
                    {
                        value = stdw.weapon_3
                        name = items[4]
                    }
                )
            }
        )
    }
}