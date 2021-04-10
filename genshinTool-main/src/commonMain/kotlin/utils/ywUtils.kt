package com.elouyi.utils

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> deserialize(jsonStr: String): T {
    return Json.run {
        decodeFromString(jsonStr)
    }
}

inline fun <reified T> serialize(obj: T): String {
    return Json.encodeToString(obj)
}

expect fun save2File(filePath: String,content: String)

enum class DataState {
    LOADING,
    DONE,
    NONE,
    ERROR,
}