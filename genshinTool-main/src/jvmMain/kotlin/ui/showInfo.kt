package com.elouyi.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
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
    Row {
        Divider(modifier = Modifier.weight(1f),color = Color(0,0,0,0))
        Column {
            Row {
                Text("5⭐ 角色!",color = YwColor.ch5)
                Rect(offset = Offset.Zero,size = Size(10f,10f))
                Spacer(modifier = Modifier.width(10.dp))
                Text("5⭐ 武器!",color = YwColor.weapon5)
            }
            Row {
                Text("4⭐ 角色!",color = YwColor.ch4)
                Spacer(modifier = Modifier.width(10.dp))
                Text("4⭐ 武器!",color = YwColor.weapon4)
            }
            Text("3⭐ 武器!",color = YwColor.weapon3)
        }
        Divider(modifier = Modifier.weight(1f),color = Color(0,0,0,0))
    }
    Row(modifier = Modifier.padding(10.dp).height(700.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {
        Column{
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

    Canvas(modifier = Modifier.padding(50.dp).height(200.dp).width(200.dp)) {
        drawInfoCircle(showData)
    }
    Row {
        Spacer(modifier = Modifier.width(50.dp))
        Column {
            val textSize = 13.sp
            val lineH = 10.sp
            Text("${showData.startTime} ~ ${showData.endTime}",fontSize = textSize,lineHeight = lineH)
            Text("一共 $total 抽，已累计 ${showData.count5} 抽未出5星",fontSize = textSize,lineHeight = lineH)
            Text("5⭐: $c5   ${String.format("%.2f",(c5 * 100f)/total)}%",fontSize = textSize,lineHeight = lineH)
            Text("4⭐: $c4   ${String.format("%.2f",(c4 * 100f)/total)}%",fontSize = textSize,lineHeight = lineH)
            Text("3⭐: $c3   ${String.format("%.2f",(c3 * 100f)/total)}%",fontSize = textSize,lineHeight = lineH)
            Text("5⭐ 记录: ",fontSize = textSize,lineHeight = lineH)
            val str5 = buildString {
                showData.s_5.forEach{ (key, value) ->
                    append("$key[$value] ")
                }
            }
            Text(
                str5,
                //fontWeight = FontWeight.Light,
                fontSize = 13.sp,
                maxLines = 10,
                modifier = Modifier.width(300.dp),
                lineHeight = lineH
            )
            Text("5⭐ 平均出货次数: ${(total - showData.count5) / c5}",fontSize = textSize,lineHeight = lineH)
        }
    }


}

fun DrawScope.drawInfoCircle(showData: WishShowData) {
    val total = showData.total
    val ch5 = (showData.ch_5 * 360f)/total
    val weapon5 =  (showData.weapon_5 * 360f)/total
    val ch4 = (showData.ch_4 * 360f)/total
    val weapon4 = (showData.weapon_4 * 360f)/total
    val weapon3 = (showData.weapon_3 * 360f)/total
    val offset = 75

    drawArc(
        color = YwColor.ch5,
        startAngle = 0f - offset,
        sweepAngle = ch5,
        true
    )
    drawArc(
        color = YwColor.weapon5,
        startAngle = ch5 - offset,
        sweepAngle = weapon5,
        true
    )
    drawArc(
        color = YwColor.ch4,
        startAngle = ch5 + weapon5 - offset,
        sweepAngle = ch4,
        true
    )
    drawArc(
        color = YwColor.weapon4,
        startAngle = ch5 + weapon5 + ch4 - offset,
        sweepAngle = weapon4,
        true
    )
    drawArc(
        color = YwColor.weapon3,
        startAngle = ch5 + weapon5 + ch4 + weapon4 - offset,
        sweepAngle = weapon3,
        true
    )
}