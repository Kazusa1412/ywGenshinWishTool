package com.elouyi

import com.elouyi.data.*
import com.elouyi.utils.readLocalFile
import com.elouyi.utils.save2File
import platform.posix.exit
import platform.posix.read
import platform.posix.system

fun main() {
    init()
    val url = readLocalFile()
    if (url.isEmpty()) {
        println("没有找到链接，请进入游戏打开抽卡界面的历史记录")
        readLine()
        readLine()
        exit(0)
    }
    println("您的链接是\n$url")

    val data = getUrlDataFromUrl(url)
    // println("data is $data")

    var s: String? = ""
    do {
        println("是否要获取抽卡信息? Y/N ")
        s = readLine()
        println("$s")
        if (s == "N" || s=="n") {
            println("程序结束")
            readLine()
            readLine()
            exit(0)
        }

    }while (s != "Y" && s != "y")
    println("正在获取数据...")
    try {
        getWishData(data)
        println("数据已保存至 ./data/ 文件夹")
    }catch (e: Exception) {
        println("发生错误")
        e.printStackTrace()
    }

    readLine()
    readLine()
}

fun init() {
    println(statement)
    system("md data")
}