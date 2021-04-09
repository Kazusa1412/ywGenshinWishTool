package com.elouyi.net

import kotlinx.cinterop.*
import platform.posix.*
import platform.windows.htons
import platform.windows.inet_pton

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
        val socketId = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP)
        println("socid is $socketId")
        val serv_addr_in: sockaddr_in = alloc()
        memset(serv_addr_in.ptr,0,sizeOf<sockaddr_in>().toULong())

        serv_addr_in.sin_family = AF_INET.toShort()
        serv_addr_in.sin_addr.S_un.S_addr = inet_addr("14.215.177.39")
        serv_addr_in.sin_port = htons(80)

        val ipt = inet_pton(AF_INET,"14.215.177.39",serv_addr_in.sin_addr.ptr)
        println("ipt is $ipt")

        val s = connect(socketId,(serv_addr_in as sockaddr).ptr, sizeOf<sockaddr_in>().toInt())

        println("s is $s")


        /*
        val bu: ByteVar = alloc()
        recv(socketId,bu.ptr,100000,0)
        println("data is ${bu.ptr.toKStringFromUtf8()}")
        */
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

fun Int.dword() = toUInt()

fun Long.dword() = toUInt()