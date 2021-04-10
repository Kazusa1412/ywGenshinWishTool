package com.elouyi.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elouyi.data.WishJsonFile
import com.elouyi.data.WishResponse
import com.elouyi.data.WishShowData
import com.elouyi.data.getWishShowData


@Composable
fun showInfo(data: WishJsonFile) {

    var ch = listOf<WishResponse>()
    var weapon = listOf<WishResponse>()
    var std = listOf<WishResponse>()
    for (d in data.data) {
        if (d.isEmpty()) continue
        when(d[0].data.list[0].gacha_type) {
            "301" -> ch = d
            "302" -> weapon = d
            "200" -> std = d
        }
    }
    Row(modifier = Modifier.padding(20.dp).height(700.dp).fillMaxWidth().background(Color.Green)) {
        Column {
            // 角色活动祈愿
            val showData = getWishShowData(ch)
            zz(showData)

        }
        Column {
            // 武器祈愿
            val showData = getWishShowData(weapon)
            zz(showData)
        }
        Column {
            // 标准池
            val showData = getWishShowData(std)
            zz(showData)
        }
    }
}

@Composable
fun zz(showData: WishShowData) {

    val c5 = showData.ch_5 + showData.weapon_5
    val c4 = showData.ch_4 + showData.weapon_4
    val c3 = showData.weapon_3
    val total = showData.total

    Canvas(modifier = Modifier.padding(70.dp).height(200.dp).width(200.dp).background(Color.Gray)) {
        drawInfoCircle(showData)
    }

    Text("${showData.startTime} ~ ${showData.endTime}")
    Text("一共 $total 抽，已累计 ${showData.count5} 抽未出5星")
    Text("5⭐: $c5   ${String.format("%.2f",(c5 * 100f)/total)}%")
    Text("4⭐: $c4   ${String.format("%.2f",(c4 * 100f)/total)}%")
    Text("3⭐: $c3   ${String.format("%.2f",(c3 * 100f)/total)}%")
    Text("5⭐ 记录: ")
    val str5 = buildString {
        showData.s_5.forEach{ (key, value) ->
            append("$key[$value] ")
        }
    }
    Text(str5,fontWeight = FontWeight.Light,fontSize = 10.sp)
}

fun DrawScope.drawInfoCircle(showData: WishShowData) {
    val total = showData.total
    val ch5 = (showData.ch_5 * 360f)/total
    val weapon5 =  (showData.weapon_5 * 360f)/total
    val ch4 = (showData.ch_4 * 360f)/total
    val weapon4 = (showData.weapon_4 * 360f)/total
    val weapon3 = (showData.weapon_3 * 360f)/total
    println(ch5)
    println("we5 $weapon5")
    drawArc(
        color = YwColor.gold,
        startAngle = 0f,
        sweepAngle = ch5,
        true
    )
    drawArc(
        color = YwColor.z,
        startAngle = ch5,
        sweepAngle = weapon5,
        true
    )
    drawArc(
        color = YwColor.purple,
        startAngle = ch5 + weapon5,
        sweepAngle = ch4,
        true
    )
    drawArc(
        color = YwColor.blue,
        startAngle = ch5 + weapon5 + ch4,
        sweepAngle = weapon4,
        true
    )
    drawArc(
        color = YwColor.pink,
        startAngle = ch5 + weapon5 + ch4 + weapon4,
        sweepAngle = weapon3,
        true
    )
}