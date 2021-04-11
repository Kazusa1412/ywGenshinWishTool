package com.elouyi.utils

import java.io.File


actual fun save2File(filePath: String,content: String) {
    val file = File(filePath)
    val out = file.outputStream()
    out.write(content.toByteArray())
    out.close()
}

