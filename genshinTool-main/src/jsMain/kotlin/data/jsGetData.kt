package com.elouyi.data

actual fun getUrlDataFromMap(map: Map<String,Any>): UrlData = UrlData().apply {
    authkey_ver = map["authkey_ver"].toString().toInt()
    sign_type = map["sign_type"].toString().toInt()
    auth_appid = map["auth_appid"].toString()
    init_type = map["init_type"].toString().toInt()
    gacha_id = map["gacha_id"].toString()
    lang = map.getProp(::lang)
    device_type = map.getProp(::device_type)
    ext = map.getProp(::ext)
    game_version = map.getProp(::game_version)
    region = map.getProp(::region)
    authkey = map.getProp(::authkey)
    game_biz = map.getProp(::game_biz)
}