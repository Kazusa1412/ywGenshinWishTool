package com.elouyi

import com.elouyi.data.WishJsonFile
import com.elouyi.module.echarts
import com.elouyi.utils.deserialize
import com.elouyi.utils.generateOption
import kotlinx.browser.document
import kotlinx.css.*
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
import styled.styledP

external interface AppProps : RProps {

}

external interface AppState: RState {
    var test: String?
}


@JsExport
class App : RComponent<AppProps,AppState>() {


    override fun RBuilder.render() {
        var mdata: WishJsonFile = WishJsonFile()
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
                            val jsonStr = reader.result.toString()
                            //console.log(jsonStr)
                            val z = deserialize<WishJsonFile>(jsonStr)
                            showInfo(z)
                            /*
                            setState {
                                test = "13456"
                            }*/
                            //props.jsdata = deserialize(jsonStr)
                            Unit
                        }
                        reader.readAsText(inputFile)


                    }
                }
            }
        }else {
            /*
           showData {
               console.log("233")
               console.log(mdata.data[0][0].message)
               data = mdata
           }*/
        }

        styledDiv {
            css {
                width = 1600.px
                height = 400.px
            }
            attrs {
                id = "main"
            }
        }
        styledDiv {
            css {
                width = 1600.px
                marginLeft = 100.px
                display = Display.flex
            }
            styledDiv {
                css {
                    width = 400.px
                    height = 400.px
                    borderStyle = BorderStyle.solid
                    borderWidth = 1.px
                    borderColor = Color.black
                }
                styledP {
                    attrs {
                        id = "chp"
                    }
                }
            }
            styledDiv {
                css {
                    paddingLeft = 50.px
                    width = 400.px
                    height = 400.px
                    borderStyle = BorderStyle.solid
                    borderWidth = 1.px
                    borderColor = Color.black
                }
            }
            styledDiv {
                css {
                    paddingLeft = 50.px
                    width = 400.px
                    height = 400.px
                    borderStyle = BorderStyle.solid
                    borderWidth = 1.px
                    borderColor = Color.black
                }
            }

        }

    }
}

fun RBuilder.showInfo(data: WishJsonFile) {
    val myChart = echarts.init(document.getElementById("main"))
    val option = generateOption(data)
    console.log(option)
    myChart.setOption(option)
    showText(data)
}

fun RBuilder.showText(data: WishJsonFile) {

}

fun RBuilder.showData(handler: InfoProps.() -> Unit): ReactElement {
    return child(InfoPage::class) {
        this.attrs(handler)
    }
}