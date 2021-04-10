package com.elouyi

import com.elouyi.data.*
import com.elouyi.utils.readLocalFile
import com.elouyi.utils.save2File
import platform.posix.exit
import platform.posix.system

fun main() {
    init()
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

fun init() {
    println(statement)
    system("md data")
}