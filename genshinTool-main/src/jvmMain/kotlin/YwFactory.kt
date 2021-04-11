package com.elouyi

import com.elouyi.data.*

actual object YwFactory {

    actual fun createDataProducer(): DataProducer {
        return object : DataProducer {
            override suspend fun fetchData(url: String): WishResponse {
                TODO("Not yet implemented")
            }

            override suspend fun fetchAllData(url: String): List<WishResponse> {
                TODO("Not yet implemented")
            }
        }
    }

    actual fun urlDataFromMap(): IUrlDataFromMap {
        return object : IUrlDataFromMap {
            override fun getUrlDataFromMap(map: Map<String, Any>): UrlData {
                // TODO: 2021/4/11 用反射
                return UrlData().apply {
                    authkey_ver = map["authkey_ver"].toString().toInt()
                    sign_type = map["sign_type"].toString().toInt()
                    auth_appid = map["auth_appid"].toString()
                    init_type = map["init_type"].toString().toInt()
                    gacha_id = map["gacha_id"].toString()
                    lang = map["lang"].toString()
                    device_type = map["device_type"].toString()
                    ext = map["ext"].toString()
                    game_version = map["game_version"].toString()
                    region = map["region"].toString()
                    authkey = map["authkey"].toString()
                    game_biz = map["game_biz"].toString()
                }
            }
        }
    }
}