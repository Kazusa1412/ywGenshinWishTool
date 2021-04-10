package com.elouyi

import com.elouyi.data.WishResponse
import com.elouyi.data.buildWishUrl
import com.elouyi.data.getUrlDataFromUrl
import com.elouyi.net.localazy.Http
import com.elouyi.utils.deserialize
import com.elouyi.utils.readLocalFile
import platform.posix.exit

fun main() {
    val url = readLocalFile()
    if (url.isEmpty()) {
        println("没有找到链接，请进入游戏打开抽卡界面的历史记录")
        readLine()
        exit(0)
    }

    println("您的链接是\n$url")

    val data = getUrlDataFromUrl(url)
    println("data is $data")

    val wishUrl = buildWishUrl {
        withUrlData(data)
        page = 10
    }
    println("wishUrl is $wishUrl")
    val res = Http.get(wishUrl, emptyMap())
    val obj = deserialize<WishResponse>(res!!)
    println("res is $res")
    println("obj is $obj")
    //val get = Http.get("https://www.baidu.com", emptyMap())


    readLine()
}