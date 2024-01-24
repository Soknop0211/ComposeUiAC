package com.example.aceledacomposeui.ui.ui

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aceledacomposeui.R
import com.example.aceledacomposeui.ui.theme.AceledaComposeUITheme
import com.example.aceledacomposeui.ui.theme.AceledaSplashLogo
import com.example.aceledacomposeui.ui.theme.Black
import com.example.aceledacomposeui.ui.theme.Gray
import com.example.aceledacomposeui.ui.theme.LightGray
import com.example.aceledacomposeui.ui.theme.Primary
import com.example.aceledacomposeui.ui.theme.Red
import com.example.aceledacomposeui.ui.theme.SecondYellow
import com.example.aceledacomposeui.ui.theme.White
import com.example.aceledacomposeui.ui.theme.Yellow
import com.example.aceledacomposeui.ui.theme.fontMedium
import com.example.aceledacomposeui.ui.theme.topBarBrush
import com.example.aceledacomposeui.ui.widget.DottedShape
import com.example.aceledacomposeui.ui.widget.dialog.InputDialogView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KhQrCodeKt(navController : NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.background(topBarBrush),
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                title = {
                    Text(
                        text = "My QKQR",
                        color = colorResource(id = R.color.white),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ){
                        Icon(
                            modifier = Modifier
                                .size(35.dp),
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            tint = colorResource(id = R.color.white),
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    Image(
                        modifier = Modifier
                            .clickable {
                                navController.navigateUp()
                            }
                            .size(30.dp),
                        painter = painterResource(id = AceledaSplashLogo),
                        contentDescription = "Localized description",
                    )
                },
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = LightGray),
            contentAlignment = Alignment.Center
        ){
            KhQrCodeBody()
        }
    }
}

@Composable
private fun KhQrCodeBody(modifier : Modifier = Modifier) {
    var showAccountDialog by remember {
        mutableStateOf(false)
    }

    var mNumberPhone by remember { mutableStateOf("097 1206 897") }

    var mAmount by remember { mutableStateOf("100,000 KHR") }

    var mCurrency by remember { mutableStateOf("KHR") }

    Column (
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween,
    ){
        Card(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 10.dp, horizontal = 20.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = White
            )
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth(),
            ) {
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Red)
                        .clip(shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)),
                ){
                    Image(
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.Center)
                            .size(width = 100.dp, height = 50.dp),
                        painter = painterResource(id = R.drawable.kh_qrcode_logo),
                        contentDescription = "Localized description",
                    )
                }

                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.End),
                    contentAlignment = Alignment.CenterEnd
                ){
                    Image(
                        modifier = Modifier
                            .rotate(-90f)
                            .size(45.dp),
                        painter = painterResource(id = R.drawable.ic_triangle_base_24),
                        contentDescription = "Localized description",
                    )
                }

                Column (
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                ){
                    Text(
                        text = "SOK NOP",
                        maxLines = 1,
                        color = Black,
                        fontFamily = fontMedium,
                        fontSize = 16.sp
                    )

                    Text(
                        modifier = modifier
                            .padding(vertical = 5.dp),
                        text = "0 $mCurrency",
                        maxLines = 1,
                        fontFamily = fontMedium,
                        color = Black,
                        fontSize = 16.sp
                    )

                    Box(
                        Modifier
                            .padding(vertical = 10.dp)
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Color.Gray, shape = DottedShape(step = 10.dp))
                    )

                    Box (
                        modifier = Modifier
                            .padding(10.dp)
                            .wrapContentSize(),
                    ){
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height((LocalConfiguration.current.screenHeightDp / 3).dp),
                            painter = painterResource(id = R.drawable.kh_qrcode),
                            contentDescription = "Localized description",
                        )
                    }

                    Spacer(modifier = Modifier.padding(20.dp))
                }

            }

        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .border(1.dp, color = Gray, shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .clickable (
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ){
                    showAccountDialog = !showAccountDialog  // First false we assign not false is true to show dialog
                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .weight(weight = 1f, fill = false)
                    .align(Alignment.Top)
                    .wrapContentSize(),
                text = "From account",
                fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                color = Black,
            )

            Row (
                modifier = Modifier
                    .weight(weight = 1f, fill = false)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column (
                    modifier = Modifier
                        .weight(weight = 1f, fill = false)
                        .padding(horizontal = 5.dp)
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = mNumberPhone,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                        color = Black,
                        maxLines = 1,
                        textAlign = TextAlign.End,
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.End,
                        text = mAmount,
                        maxLines = 1,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                        color = SecondYellow,
                        style = TextStyle(fontSize = 18.sp)
                    )
                }

                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "contentDescription",
                    tint = Black,
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .border(1.dp, color = Gray, shape = CircleShape)
                        .align(Alignment.CenterVertically)
                        .shadow(
                            shape = CircleShape,
                            elevation = 8.dp,
                            ambientColor = Black,
                            spotColor = Yellow
                        )
                )
            }
        }

    }


    // Alert Dialog
    if (showAccountDialog) {
        InputDialogView {
            showAccountDialog = !showAccountDialog

            if(it != null) {
                mNumberPhone = it.numberPhone
                mAmount = "${it.price} ${it.currency}"
                mCurrency = it.currency
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KhQrCodePreview() {
    AceledaComposeUITheme {
        KhQrCodeKt(navController = rememberNavController())
    }
}