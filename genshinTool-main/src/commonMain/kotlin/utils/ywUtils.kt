package com.elouyi.utils

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> deserialize(jsonStr: String): T {
    return Json.run {
        decodeFromString(jsonStr)
    }
}

fun serialize(obj: Any): String {
    return Json.encodeToString(obj)
}