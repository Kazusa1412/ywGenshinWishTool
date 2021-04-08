package com.elouyi

import com.elouyi.net.getIpByHostname
import com.elouyi.net.openSocket
import com.elouyi.utils.readLocalFile
import kotlinx.cinterop.*
import platform.posix.exit
import platform.posix.pthread_self
import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.Worker

fun main() {
    val url = readLocalFile()
    if (url.isEmpty()) {
        println("没有找到链接，请进入游戏打开抽卡界面")
        readLine()
        exit(0)
    }

    println("您的链接是\n$url")
/*
    val worker = Worker.start(true,"zz")

    worker.execute(TransferMode.UNSAFE,{
        println(2)
        1
    }) {
        println(3)

    }.consume {
        println(4)
    }


 */
    openSocket("https://www.baidu.com")
    /*
    while (true) {
        Worker.start(true).execute(TransferMode.UNSAFE,{}) {
            println("13 ${pthread_self()!!} sdsa")
        }
    }

     */


    readLine()
}