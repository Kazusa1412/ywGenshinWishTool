package com.elouyi

import com.elouyi.utils.readLocalFile
import kotlinx.cinterop.*

fun main() = memScoped<Unit> {
    println("native")

    val url = readLocalFile()
    println(url)

    readLine()
}