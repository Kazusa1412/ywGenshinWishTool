package com.elouyi

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
import com.elouyi.data.getWishData
import com.elouyi.data.statement
import com.elouyi.ui.*
import com.elouyi.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun main() = Window(title = "ywGenshinTool V0.1",size = IntSize(1280,720),icon = null) {

    val data = remember { mutableStateOf(WishJsonFile()) }
    val state = remember { mutableStateOf(DataState.LOADING) }

    GlobalScope.launch(Dispatchers.Main) {
        val jsonData = getWishData()
        if (jsonData == null) {
            state.value = DataState.NONE
            return@launch
        }
        data.value = jsonData
        state.value = DataState.DONE
    }

    MaterialTheme(shapes = YwShape.test) {
        Column(modifier = Modifier.fillMaxSize().background(Color(255,234,239,255))) {
            Column(modifier = Modifier.weight(100f)) {
                Row (modifier = Modifier.height(100.dp).fillMaxWidth().background(Color(250,140,160,150))) {
                    Button(
                        modifier = Modifier.padding(10.dp),
                        onClick = {

                        }
                    ) {
                        Text("更新数据")
                    }
                }

                when(state.value) {
                    DataState.LOADING -> loading()
                    DataState.DONE -> showInfo(data.value)
                    DataState.NONE -> none()
                    DataState.ERROR -> showError()
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