package com.elouyi

import androidx.compose.animation.Crossfade
import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elouyi.data.WishJsonFile
import com.elouyi.data.getWishDataFromJsonFile
import com.elouyi.data.requestData
import com.elouyi.data.statement
import com.elouyi.ui.*
import com.elouyi.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import javax.imageio.ImageIO

fun main() {
    try {
        Window(
            title = "ywGenshinTool V0.1",
            size = IntSize(1280,720),
            icon = ImageIO.read(YwFactory::class.java.classLoader.getResource("ct14.jpg"))
        ) {
            init()
            val data = remember { mutableStateOf(WishJsonFile()) }
            val state = remember { mutableStateOf(DataState.LOADING) }

            GlobalScope.launch(Dispatchers.Main) {
                //state.value = DataState.DOWNLOADING
                //return@launch
                val jsonData = getWishDataFromJsonFile()
                if (jsonData == null) {
                    state.value = DataState.NONE
                    return@launch
                }
                data.value = jsonData
                state.value = DataState.DONE
            }

            MaterialTheme(shapes = YwShape.test) {
                Column(modifier = Modifier.fillMaxSize().background(YwColor.background)) {
                    Column(modifier = Modifier.weight(100f)) {
                        Row (modifier = Modifier.height(80.dp).fillMaxWidth().background(Color(250,140,160,150))) {
                            Button(
                                modifier = Modifier.padding(20.dp),
                                onClick = {
                                    if (state.value == DataState.LOADING || state.value == DataState.DOWNLOADING) return@Button
                                    GlobalScope.launch(Dispatchers.Main) {
                                        state.value = DataState.DOWNLOADING
                                        requestData()
                                        val jsonData = getWishDataFromJsonFile()
                                        if (jsonData == null) {
                                            state.value = DataState.ERROR
                                            return@launch
                                        }
                                        data.value = jsonData
                                        state.value = DataState.DONE
                                    }

                                }
                            ) {
                                Text("更新数据")
                            }
                        }

                        Crossfade(targetState = state) {
                            when(it.value) {
                                DataState.LOADING -> loading()
                                DataState.DONE -> showInfo(data.value)
                                DataState.NONE -> none()
                                DataState.ERROR -> showError()
                                DataState.DOWNLOADING -> showDownloading()
                            }
                        }
                    }

                    Text(statement,modifier = Modifier.weight(5f),fontSize = 15.sp)
                }
            }
            /*
            val count = remember { mutableStateOf(0) }
            MaterialTheme(shapes = YwShape.test) {
                Column(Modifier.fillMaxSize(),Arrangement.spacedBy(5.dp)) {
                    Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = {
                            count.value++
                        }
                    ) {
                        Text(if (count.value == 0) "Hello World" else "Clicked ${count.value}")
                    }
                    Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = {
                            count.value = 0
                        }) {
                        Text("Reset")
                    }
                }
                Canvas(modifier = Modifier.fillMaxSize()){
                    val canvasWidth = size.width
                    val canvasHeight = size.height
                    drawCircle(
                        color = Color.Blue,
                        center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                        radius = size.minDimension / 4
                    )
                }
            }

             */
        }
    }catch (e: Exception) {
        val file = File("error.log").apply { if (!exists()) createNewFile() }
        val out = file.outputStream()
        out.write(e.stackTraceToString().toByteArray())
        out.close()
    }
}

fun init() {
    File("data").apply { if (!exists()) mkdir() }
}