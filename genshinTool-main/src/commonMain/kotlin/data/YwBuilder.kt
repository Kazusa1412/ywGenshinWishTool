package com.elouyi.data

interface YwBuilder<out T> {
    fun build(): T
}