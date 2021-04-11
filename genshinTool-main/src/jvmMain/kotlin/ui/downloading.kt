package com.elouyi.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun showDownloading() {
    Row(modifier = Modifier.padding(10.dp)) {
        Divider(modifier = Modifier.weight(1f),color = Color(0,0,0,0))
        Column {
            Text("获取数据中")
        }
        Divider(modifier = Modifier.weight(1f),color = Color.Black)
    }

}