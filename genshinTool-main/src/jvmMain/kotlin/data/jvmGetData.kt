package com.elouyi.data

import com.elouyi.utils.deserialize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

suspend fun getWishDataFromJsonFile(): WishJsonFile? = withContext(Dispatchers.IO) {
    val dir = File("data").apply {
        try {
            if (listFiles().isEmpty()) return@withContext null
        } catch (e: Exception) {
            return@withContext null
        }
    }
    val inputStream = dir.listFiles()[0].inputStream()
    val bytes = inputStream.readBytes()
    val str = String(bytes)
    inputStream.close()
    deserialize<WishJsonFile>(str)
}

suspend fun readLocalFile(): String = withContext(Dispatchers.IO) {
    val userName = System.getProperty("user.name")
    val filePath = "C:/Users/${userName}/AppData/LocalLow/miHoYo/原神/output_log.txt"
    val file = File(filePath).apply { if (!exists()) return@withContext "" }
    val inputString = file.inputStream()
    val str = String(inputString.readBytes())
    inputString.close()
    val prefix = "OnGetWebViewPageFinish:https://"
    var urlParam = ""
    str.split("\n").forEach {
        if (it.startsWith(prefix)) {
            urlParam = it.slice(prefix.length - 8 until it.length)
            return@forEach
        }
    }
    urlParam
}

suspend fun requestData() {
    withContext(Dispatchers.IO) {
        try {
            val data = getUrlDataFromUrl(readLocalFile())
            getWishData(data)
        }catch (e: Exception) {
            e.printStackTrace()
            val file = File("error.log").apply { if (!exists()) createNewFile() }
            val out = file.outputStream()
            out.write(e.stackTraceToString().toByteArray())
            out.close()
        }
    }

}