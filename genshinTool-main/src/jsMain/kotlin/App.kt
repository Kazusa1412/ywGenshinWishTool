package com.elouyi

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.p

@JsExport
class App : RComponent<RProps,RState>() {

    override fun RBuilder.render() {
        p {
            + "zzz"
        }
    }
}