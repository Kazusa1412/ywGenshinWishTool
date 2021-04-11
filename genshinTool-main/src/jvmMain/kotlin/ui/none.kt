package com.elouyi.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun none() {

    Row {
        Divider(modifier = Modifier.weight(1f),color = Color(0,0,0,0))
        Column(modifier = Modifier,horizontalAlignment = Alignment.CenterHorizontally) {
            Text("没有数据")
            Text("请进入游戏打开抽卡历史记录，然后点击更新数据获取数据")
        }
        Divider(modifier = Modifier.weight(1f),color = Color(0,0,0,0))
    }
}