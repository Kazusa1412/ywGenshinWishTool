package com.elouyi

import androidx.compose.desktop.Window
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.elouyi.data.statement
import com.elouyi.ui.YwShape
import com.elouyi.ui.loading

fun main() = Window(title = "ywTitle",size = IntSize(1280,720),icon = null) {

    val data = remember { mutableStateOf(0) }


    MaterialTheme {
        Column {
            Row (modifier = Modifier.height(100.dp).fillMaxWidth().background(Color.Yellow)){

            }
            loading()
            Row(modifier = Modifier.padding(10.dp).height(300.dp).fillMaxWidth().background(Color.Green)) {
                Canvas(modifier = Modifier.padding(40.dp).height(200.dp).width(200.dp).background(Color.Yellow)) {
                    drawArc(
                        color = Color.Green,
                        0f,
                        90f,
                        true
                    )
                    drawArc(
                        color = Color.Red,
                        90f,
                        180f,
                        true
                    )
                    drawArc(
                        color = Color.Blue,
                        270f,
                        90f,
                        true
                    )
                }
                Canvas(modifier = Modifier.padding(40.dp).height(200.dp).width(200.dp).background(Color.Yellow)) {
                    drawArc(
                        color = Color.Green,
                        0f,
                        60f,
                        true
                    )
                }
                Canvas(modifier = Modifier.padding(40.dp).height(200.dp).width(200.dp).background(Color.Yellow)) {
                    drawArc(
                        color = Color.Green,
                        0f,
                        120f,
                        true
                    )
                }
            }


            Text(statement,modifier = Modifier)
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