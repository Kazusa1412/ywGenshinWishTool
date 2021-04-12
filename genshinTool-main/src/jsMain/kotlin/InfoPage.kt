package com.elouyi

import com.elouyi.data.WishJsonFile
import com.elouyi.module.echarts
import kotlinx.browser.document
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState

external interface InfoProps : RProps {
    var data: WishJsonFile
}

class InfoPage : RComponent<InfoProps,RState>() {

    val myChart = echarts.init(document.getElementById("main"))

    override fun RBuilder.render() {
        TODO()
    }
}