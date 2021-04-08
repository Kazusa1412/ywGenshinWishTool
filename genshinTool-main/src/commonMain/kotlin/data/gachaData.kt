package com.elouyi.data

import kotlin.reflect.KProperty

data class SingleData(
    val uid: String,
    val gachaType: String,
    val itemId: String,
    val count: String,
    val time: String,
    val name: String,
    val lang: String,
    val itemType: String,
    val rankType: String,
    val id: String,
)

data class WishData(
    val page: String,
    val size: String,
    val total: String,
    val list: List<SingleData>
)

data class WishResponse(
    val retcode: Int,
    val message: String,
    val data: WishData
)

open class UrlData(
    var authkey_ver: Int = 1,
    var sign_type: Int = 2,
    var auth_appid: String = "webview_gacha",
    var init_type: Int = 301,
    var gacha_id: String = "",
    var lang: String = "zh-cn",
    var device_type: String = "pz",
    var ext: String = "",
    var game_version: String = "",
    var region: String = "",
    var authkey: String = "",
    var game_biz: String = "",
)


inline fun buildWishUrl(
    block: WishUrlBuilder.() -> Unit
): String = WishUrlBuilder().run {
    block()
    build()
}

data class WishUrlBuilder(
    var gacha_type: Int = 301,
    var page: Int = 1,
    var size: Int = 6,
    var end_id: String = "",
) : UrlData(), YwBuilder<String> {

    fun withUrlData(urlData: UrlData) {
        //todo 用数据类的解构
        this.authkey_ver = urlData.authkey_ver
        this.sign_type = urlData.sign_type
        this.auth_appid = urlData.auth_appid
        this.init_type = urlData.init_type
        this.gacha_id = urlData.gacha_id
        this.lang = urlData.lang
        this.device_type = urlData.device_type
        this.ext = urlData.ext
        this.game_version = urlData.game_version
        this.region = urlData.region
        this.authkey = urlData.authkey
        this.game_biz = urlData.game_biz
    }

    override fun build(): String = buildString {
        append("https://hk4e-api.mihoyo.com/event/gacha_info/api/getGachaLog?")
        //val c = this@WishUrlBuilder::class // 反射是 jvm 平台的
        append("authkey_ver=$authkey_ver")
        append("&sign_type=$sign_type")
        append("&auth_appid=$auth_appid")
        append("&init_type=$init_type")
        append("&gacha_id=$gacha_id")
        append("&lang=$lang")
        append("&device_type=$device_type")
        append("&ext=$ext")
        append("&game_version=$game_version")
        append("&region=$region")
        append("&authkey=$authkey")
        append("&game_biz=$game_biz")
        append("&gacha_type=$gacha_type")
        append("&page=$page")
        append("&size=$size")
        append("&end_id=$end_id")
    }
}

fun getUrlDataFromUrl(url: String): UrlData {
    val i = url.indexOf("?")
    val map = mutableMapOf<String,Any>()
    url.slice(i + 1 until url.length)
        .split("&")
        .forEach {
            it.split("=").apply {
                map[get(0)] = get(1).run {
                    if (endsWith("#/log")){
                        slice(0 until lastIndexOf("#"))
                    }else {
                        this
                    }
                }
            }
        }
    println(map)
    return getUrlDataFromMap(map)
}

expect fun getUrlDataFromMap(map: Map<String,Any>): UrlData


fun <R> Map<String,Any>.getProp(kProperty: KProperty<Any>): R{
    return get(kProperty.name) as R
}