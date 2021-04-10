package com.elouyi.utils

import kotlinx.cinterop.*
import platform.posix.*
import platform.windows.CreateDirectoryW
import platform.windows.DWORD
import platform.windows.GetUserNameW

fun getWindowsUserName(): String = memScoped {
    val a1 = "".wcstr
    val a = a1.ptr
    val p = alloc<UIntVarOf<DWORD>>()
    p.value = 1000000000u
    GetUserNameW(a,p.ptr)
    a.toKStringFromUtf16()
}

fun readLocalFile(): String = buildString{
    val fileName = "C:/Users/${getWindowsUserName()}/AppData/LocalLow/miHoYo/原神/output_log.txt"
    val fp = _wfopen(fileName.wcstr,"r".wcstr)
    if (fp == null) {
        println("没有找到文件\n $fileName")
        readLine()
        exit(1)
    }
    memScoped {
        val a:  CValuesRef<ByteVar> = allocArray(5000)
        val prefix = "OnGetWebViewPageFinish:https://"
        while (true) {
            if (fgets(a,5000,fp) != null) {
                //printf("%s\n",a)
                val a = a.getPointer(this).toKStringFromUtf8()
                if (a.startsWith(prefix)){
                    append(a.slice(prefix.length - 8 until a.length))
                    break
                }
            }else {
                break
            }
        }
    }
    fclose(fp)
}

actual fun save2File(filePath: String,content: String) {
    val fp = _wfopen(filePath.wcstr,"r+".wcstr) ?: _wfopen(filePath.wcstr,"w+".wcstr)
    println(fp == null)
    fputs(content,fp)
}