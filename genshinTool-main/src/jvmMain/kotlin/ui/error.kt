package com.elouyi.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun showError() {
    Column(modifier = Modifier,horizontalAlignment = Alignment.CenterHorizontally) {
        Text("error")
    }
}