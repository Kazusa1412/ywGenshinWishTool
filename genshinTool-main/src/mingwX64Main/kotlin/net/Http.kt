package com.elouyi.net

object Http {

    fun get(url: String, headers: Map<String, String>): String? {
        return WinInetHelper.request(
            url = url,
            method = "GET",
            headers = headers,
            postData = null
        ) { responseCode, responseData ->
            if (responseCode != 200) {
                null
            } else {
                responseData.decodeToString()
            }
        }
    }

}