package com.example.aceledacomposeui.ui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aceledacomposeui.ui.theme.AceledaComposeUITheme
import com.example.aceledacomposeui.ui.theme.Primary
import com.example.aceledacomposeui.ui.theme.SecondYellow
import com.example.aceledacomposeui.ui.theme.Yellow

@Composable
fun CustomBottomButtonKt() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Primary)
    ){
        Text(
            text = "OK",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .padding(15.dp),
            color = SecondYellow
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomBottomButtonPreview() {
    AceledaComposeUITheme {
        CustomBottomButtonKt()
    }
}