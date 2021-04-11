package com.elouyi

import kotlinx.browser.document
import kotlinx.css.border
import kotlinx.css.width
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import org.w3c.files.File
import org.w3c.files.FileReader
import react.*
import react.dom.button
import react.dom.input
import react.dom.p
import styled.css
import styled.styledDiv
import kotlin.reflect.typeOf

external interface AppProps : RProps {
    var jsonStr: String
}

external interface AppState: RState {
    var test: String?
}

@JsExport
class App : RComponent<AppProps,AppState>() {

    override fun RBuilder.render() {
        p {
            + "zzz"
        }
        if (state.test.isNullOrEmpty()) {
            input {
                attrs {
                    type = InputType.file
                    id = "urlInput"
                }
            }
            button {
                + "提交"
                attrs {
                    onClickFunction = {
                        val input: dynamic = document.getElementById("urlInput")
                        val inputFile = input.files[0] as File
                        val reader = FileReader()
                        reader.onload = {
                            console.log(reader.result)
                        }
                        reader.readAsText(inputFile)
                        setState {
                            test = "13456"
                        }
                        console.log(inputFile)
                        console.log(inputFile::class)

                    }
                }
            }
        }else {
            p {
                + "hahaha"
            }
        }

        styledDiv {
            css {

            }
        }
    }
}