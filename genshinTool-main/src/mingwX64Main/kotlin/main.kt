package com.elouyi

import com.elouyi.net.Http
import com.elouyi.utils.readLocalFile
import platform.posix.exit

fun main() {
    val url = readLocalFile()
    if (url.isEmpty()) {
        println("没有找到链接，请进入游戏打开抽卡界面")
        readLine()
        exit(0)
    }

    println("您的链接是\n$url")

    val get = Http.get("https://www.baidu.com", emptyMap())
    println(get)


    readLine()
}