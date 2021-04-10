package com.elouyi.data

import com.elouyi.utils.deserialize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

suspend fun getWishData(): WishJsonFile? = withContext(Dispatchers.IO) {
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