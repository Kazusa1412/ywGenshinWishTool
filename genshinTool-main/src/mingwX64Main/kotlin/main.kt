package com.elouyi

import com.elouyi.utils.readLocalFile
import kotlinx.cinterop.*
import platform.posix.exit

fun main() = memScoped<Unit> {
    val url = readLocalFile()
    if (url.isEmpty()) {
        println("没有找到链接，请进入游戏打开抽卡界面")
        readLine()
        exit(0)
    }

    println("您的链接是\n$url")
    readLine()
}

fun test() {

}
