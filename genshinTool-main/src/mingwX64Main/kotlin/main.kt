package com.elouyi

import com.elouyi.data.*
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

    getWishData(data)
    readLine()
}