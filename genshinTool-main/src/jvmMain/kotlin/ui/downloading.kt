package com.elouyi.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun showDownloading() {
    val rit = rememberInfiniteTransition()
    val test by rit.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200,easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )


    Row(modifier = Modifier.padding(10.dp)) {
        Divider(modifier = Modifier.weight(1f),color = Color(0,0,0,0))
        Column {
            Text("获取数据中")
            Canvas(modifier = Modifier.padding(50.dp).height(200.dp).width(200.dp)) {
                drawArc(
                    color = YwColor.ch5,
                    startAngle = test,
                    sweepAngle = 30f,
                    true
                )
                drawArc(
                    color = YwColor.ch4,
                    startAngle = test + 60,
                    sweepAngle = 30f,
                    true
                )
                drawArc(
                    color = YwColor.weapon5,
                    startAngle = test + 120,
                    sweepAngle = 30f,
                    true
                )
                drawArc(
                    color = YwColor.weapon4,
                    startAngle = test + 180,
                    sweepAngle = 30f,
                    true
                )
                drawArc(
                    color = YwColor.gold,
                    startAngle = test + 240,
                    sweepAngle = 30f,
                    true
                )
                drawArc(
                    color = YwColor.weapon3,
                    startAngle = test + 300,
                    sweepAngle = 30f,
                    true
                )
            }
        }
        Divider(modifier = Modifier.weight(1f),color = Color.Black)
    }

}