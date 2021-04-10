package com.elouyi.data

import com.elouyi.YwFactory
import kotlinx.serialization.Serializable
import kotlin.reflect.KProperty

@Serializable
data class SingleData(
    val uid: String,
    val gacha_type: String,
    val item_id: String,
    val count: String,
    val time: String,
    val name: String,
    val lang: String,
    val item_type: String,
    val rank_type: String,
    val id: String,
)

@Serializable
data class WishData(
    val page: String,
    val size: String,
    val total: String,
    val list: List<SingleData>,
    val region: String,
)

/**
 * get 请求响应实体类
 */
@Serializable
data class WishResponse(
    val retcode: Int,
    val message: String,
    val data: WishData,
)

/**
 * 读取的 url 数据类
 */
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

/**
 * 生成 get 请求 url 的方法
 */
inline fun buildWishUrl(
    block: WishUrlBuilder.() -> Unit
): String = WishUrlBuilder().run {
    block()
    build()
}

/**
 * get 请求 url builder
 */
data class WishUrlBuilder(
    var gacha_type: Int = 301,
    var page: Int = 1,
    var size: Int = 6,
    var end_id: String = "0",
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

/**
 * 从读取到的 str 提取请求参数信息
 */
fun getUrlDataFromUrl(url: String): UrlData {
    val i = url.indexOf("?")
    val map = mutableMapOf<String,Any>()
    url.slice(i + 1 until url.length)
        .run {
            // endsWith 在 native 不是预期效果
            if (contains("#")) {
                slice(0 until lastIndexOf("#"))
            } else this
        }
        .split("&")
        .forEach {
            it.split("=").apply {
                map[get(0)] = get(1)
            }
        }
    println(map)
    return YwFactory.urlDataFromMap().getUrlDataFromMap(map)
}

/**
 * 从 map 到 [UrlData] 的接口，由 [YwFactory] 构建
 */
interface IUrlDataFromMap {
    fun getUrlDataFromMap(map: Map<String, Any>): UrlData
}

/**
 * emm
 */
val commonMap2UrlData: IUrlDataFromMap = object : IUrlDataFromMap {
    override fun getUrlDataFromMap(map: Map<String, Any>): UrlData {
        return CommonUrlDataFromMap.getUrlDataFromMap(map)
    }
}

/**
 * 公共部分的 map 转 [UrlData], jvm 平台可能会有其他实现
 */
object CommonUrlDataFromMap : IUrlDataFromMap {
    override fun getUrlDataFromMap(map: Map<String, Any>): UrlData = UrlData().apply {
        authkey_ver = map.getProp(::authkey_ver)
        sign_type = map.getProp(::sign_type)
        auth_appid = map.getProp(::auth_appid)
        init_type = map.getProp(::init_type)
        gacha_id = map.getProp(::gacha_id)
        lang = map.getProp(::lang)
        device_type = map.getProp(::device_type)
        ext = map.getProp(::ext)
        game_version = map.getProp(::game_version)
        region = map.getProp(::region)
        authkey = map.getProp(::authkey)
        game_biz = map.getProp(::game_biz)
    }
}


fun <R> Map<String,Any>.getProp(kProperty: KProperty<Any>): R{
    return get(kProperty.name) as R
}