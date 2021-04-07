package com.elouyi

import com.elouyi.data.DataProducer

expect object YwFactory {
    fun createDataProducer(): DataProducer
}