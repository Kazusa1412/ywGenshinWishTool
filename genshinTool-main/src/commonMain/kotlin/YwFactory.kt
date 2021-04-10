package com.elouyi

import com.elouyi.data.DataProducer
import com.elouyi.data.IUrlDataFromMap

expect object YwFactory {
    fun createDataProducer(): DataProducer

    fun urlDataFromMap(): IUrlDataFromMap
}