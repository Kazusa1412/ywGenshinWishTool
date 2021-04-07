package com.elouyi

import com.elouyi.data.getUrlDataFromUrl
import com.elouyi.utils.request
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onInputFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.button
import react.dom.input
import react.dom.p

@JsExport
class App : RComponent<RProps,RState>() {

    override fun RBuilder.render() {
        p {
            + "zzz"
        }
        if (url.isEmpty()) {
            input {
                attrs {
                    placeholder = "请输入 url (运行genshinTool-main.exe即可获得)"
                    id = "urlInput"
                }
            }
            button {
                + "提交"
                attrs {
                    onClickFunction = {
                        val input: dynamic = document.getElementById("urlInput")
                        val inputUrl = input.value as String?
                        console.log(inputUrl)
                        if (inputUrl.isNullOrEmpty()) {
                            window.alert("请输入url")
                        }else {
                            url = inputUrl
                            val u2 = getUrlDataFromUrl(url)
                            console.log(u2)
                        }
                    }
                }
            }
        }
    }
}