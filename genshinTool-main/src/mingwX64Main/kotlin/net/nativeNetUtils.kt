package com.elouyi.net

import kotlinx.cinterop.*
import platform.posix.*

@Deprecated("zz")
fun getIpByHostname(hostname: String): String? {

    memScoped {
        val hprt = gethostbyname(hostname) ?: return null
        return hprt.rawValue.toString()
    }
}

fun openSocket(url: String){

    memScoped {
        var sockaddr_in: SOCKET_ADDRESS
        val socketId = socket(AF_INET, SOCK_STREAM,0)
        println("socid is $socketId")
        val p = alloc<sockaddr>()
        p.sa_family = 10u
        val state = connect(socketId,p.ptr, 1000)
        println("state is $state")
    }
}


fun request(url: String): String {
    var ret: Int
    var i: Int
    var h: Int;

    val sockfg = socket(AF_INET, SOCK_STREAM,0)
    if (sockfg <= 0u) {
        println("创建网络连接失败")
        return ""
    }

    return ""
}