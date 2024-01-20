package com.example.aceledacomposeui.ui.ui

import android.text.TextUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aceledacomposeui.R
import com.example.aceledacomposeui.ui.theme.AceledaComposeUITheme
import com.example.aceledacomposeui.ui.theme.Black
import com.example.aceledacomposeui.ui.theme.Gray
import com.example.aceledacomposeui.ui.theme.GrayBg
import com.example.aceledacomposeui.ui.theme.Primary
import com.example.aceledacomposeui.ui.theme.ThirdPrimary
import com.example.aceledacomposeui.ui.theme.White

@Composable
fun KeyBoardNoShadowKt(onClick: (String) -> Unit, mDensity: Density = LocalDensity.current) {
    val mList = arrayOf(
        "1", "2", "3", "4", "5",
        "6", "7", "8", "9", ".",
        "0", "X"
    )
    var columnHeightDp by remember {
        mutableStateOf(0.dp)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = GrayBg)
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .height((columnHeightDp))
                .fillMaxWidth(),
            columns = GridCells.Fixed(3),
            content = {
                itemsIndexed(items = mList) { index, _ ->
                    val mIsNotNumber = mList[index] == "." || mList[index] == "X"

                    val cardShape = RoundedCornerShape(10.dp)

                    Card(
                        modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 5.dp)
                            .onGloballyPositioned { coordinates ->
                                val itemHeight =
                                    with(mDensity) { (coordinates.size.height.toDp() + (6 * 3).dp) }

                                columnHeightDp = itemHeight * (mList.size / 3)
                            }
                            .shadow(
                                shape = cardShape,
                                ambientColor = Primary,
                                spotColor = Primary,
                                elevation = if (mIsNotNumber) 0.dp else 10.dp
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = if (mIsNotNumber) null else rememberRipple(color = Primary),
                            ) {
                                onClick.invoke(mList[index])
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = if(mIsNotNumber) Color.Transparent else White,
                        ),
                        shape = cardShape,
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterVertically)
                                    .weight(weight = 1f, fill = false)
                            ){
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .padding(8.dp)
                                ) {
                                    if (mList[index].equals("x", true)) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.icon_clear),
                                            contentDescription = "contentDescription",
                                            tint = Primary,
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .size(30.dp)
                                                .align(Alignment.CenterHorizontally)
                                                .padding(horizontal = 5.dp))

                                    } else {
                                        Text(
                                            text = mList[index],
                                            color = Black,
                                            maxLines = 1,
                                            fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                                            fontWeight = FontWeight.Bold,
                                            style = TextStyle(fontSize = 16.sp),
                                            modifier = Modifier
                                                .padding(top = 10.dp, bottom = 5.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        )
    }

}

@Composable
fun KeyBoardKt(onClick: (String) -> Unit, mDensity: Density = LocalDensity.current) {
    val mList = arrayOf(
        "1", "2", "3", "4", "5",
        "6", "7", "8", "9", ".",
        "0", "X"
    )
    var columnHeightDp by remember {
        mutableStateOf(0.dp)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = GrayBg)
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .height((columnHeightDp))
                .fillMaxWidth(),
            columns = GridCells.Fixed(3),
            content = {
                itemsIndexed(items = mList) { index, _ ->
                    val mIsNotNumber = mList[index] == "." || mList[index] == "X"

                    val cardShape = RoundedCornerShape(10.dp)

                    Card (
                        modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 5.dp)
                            .onGloballyPositioned { coordinates ->
                                val itemHeight =
                                    with(mDensity) { (coordinates.size.height.toDp() + (6 * 3).dp) }

                                columnHeightDp = itemHeight * (mList.size / 3)
                            }
                            .shadow(
                                shape = cardShape,
                                ambientColor = Primary,
                                spotColor = Primary,
                                elevation = if (mIsNotNumber) 0.dp else 10.dp
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = if (mIsNotNumber) null else rememberRipple(color = Primary),
                            ) {
                                onClick.invoke(mList[index])
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = if(mIsNotNumber) Color.Transparent else Gray,
                        ),
                        shape = cardShape,
                    ){
                        Card(
                            modifier = Modifier
                                .padding(bottom = 2.dp)
                                .onGloballyPositioned { coordinates ->
                                    val itemHeight =
                                        with(mDensity) { (coordinates.size.height.toDp() + (6 * 3).dp) }

                                    columnHeightDp = itemHeight * (mList.size / 3)
                                }
                                .shadow(
                                    shape = cardShape,
                                    ambientColor = Primary,
                                    spotColor = Primary,
                                    elevation = if (mIsNotNumber) 0.dp else 10.dp
                                ),
                            colors = CardDefaults.cardColors(
                                containerColor = if(mIsNotNumber) Color.Transparent else White,
                            ),
                            shape = cardShape,
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column (
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.CenterVertically)
                                        .weight(weight = 1f, fill = false)
                                ){
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .padding(8.dp)
                                    ) {
                                        if (mList[index].equals("x", true)) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.icon_clear),
                                                contentDescription = "contentDescription",
                                                tint = Primary,
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .size(30.dp)
                                                    .align(Alignment.CenterHorizontally)
                                                    .padding(horizontal = 5.dp))

                                        } else {
                                            Text(
                                                text = mList[index],
                                                color = Black,
                                                maxLines = 1,
                                                fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                                                fontWeight = FontWeight.Bold,
                                                style = TextStyle(fontSize = 16.sp),
                                                modifier = Modifier
                                                    .padding(top = 10.dp, bottom = 5.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun KeyBoardLineBgKt(onClick: (String) -> Unit, mDensity: Density = LocalDensity.current) {
    val mList = arrayOf(
        "1", "2", "3", "4", "5",
        "6", "7", "8", "9", ".",
        "0", "X"
    )
    var columnHeightDp by remember {
        mutableStateOf(0.dp)
    }

    var itemHeight by remember {
        mutableStateOf(0.dp)
    }

    // Color
    val mStartList = listOf(
        White,
        Gray,
        Gray
    )

    val mEndList = listOf(
        Gray,
        Gray,
        White
    )

    val mCenterList = listOf(
        Gray,
        Gray,
        Gray
    )

    val startXBrush = Brush.horizontalGradient(
        colors = mStartList
    )

    val centerXBrush = Brush.horizontalGradient(
        colors = mCenterList
    )

    val endXBrush = Brush.horizontalGradient(
        colors = mEndList
    )

    val startYBrush = Brush.verticalGradient(
        colors = mStartList
    )

    val centerYBrush = Brush.verticalGradient(
        colors = mCenterList
    )

    val endYBrush = Brush.verticalGradient(
        colors = mEndList
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = White)
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .height((columnHeightDp))
                .fillMaxWidth(),
            columns = GridCells.Fixed(3),
            content = {
                itemsIndexed(items = mList) { index, _ ->
                    val mIsNotNumber = mList[index] == "." || mList[index] == "X"

                    Card(
                        modifier = Modifier
                            .onGloballyPositioned { coordinates ->
                                if (itemHeight == 0.dp) {
                                    itemHeight = with(mDensity) { (coordinates.size.height.toDp()) }

                                    columnHeightDp = itemHeight * (mList.size / 3)
                                }
                            }
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = if (mIsNotNumber) null else rememberRipple(color = Primary),
                            ) {
                                onClick.invoke(mList[index])
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = White,
                        ),
                        shape = RoundedCornerShape(0.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterVertically)
                                    .weight(weight = 1f, fill = false)
                            ){
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .padding(8.dp)
                                        .fillMaxHeight()
                                ) {
                                    if (mList[index].equals("x", true)) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.icon_clear),
                                            contentDescription = "contentDescription",
                                            tint = Primary,
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .size(25.dp)
                                                .align(Alignment.CenterHorizontally)
                                                .padding(horizontal = 5.dp))

                                    } else {
                                        Text(
                                            text = mList[index],
                                            color = Black,
                                            maxLines = 1,
                                            fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                                            fontWeight = FontWeight.Bold,
                                            style = TextStyle(fontSize = 16.sp),
                                            modifier = Modifier
                                                .padding(top = 10.dp, bottom = 5.dp)
                                        )
                                    }
                                }

                                if (index <= 8) {
                                    val mBrush =
                                        when (index) {
                                            0, 3, 6 -> startXBrush
                                            1, 4, 7 -> centerXBrush
                                            else -> endXBrush
                                        }

                                    Divider(
                                        modifier = Modifier
                                            .height(1.dp)
                                            .background(brush = mBrush),
                                        thickness = (1).dp,
                                        color = Color.Transparent
                                    )
                                }
                            }

                            if (index == 0 || index == 1 || index == 3 || index == 4 || index == 6 || index == 7 || index == 9 || index == 10) {
                                val mBrush =
                                    when (index) {
                                        0, 1 -> startYBrush
                                        3, 4, 6, 7 -> centerYBrush
                                        else -> endYBrush
                                    }

                                Divider(
                                    modifier = Modifier
                                        .width(1.dp)
                                        .height(itemHeight)
                                        .background(brush = mBrush),
                                    thickness = (1).dp,
                                    color = Color.Transparent
                                )
                            }
                        }
                    }
                }
            }
        )
    }

}


@Preview(showBackground = true)
@Composable
fun KeyBoardKtPreview() {
    AceledaComposeUITheme {
        KeyBoardLineBgKt(onClick = {})
    }
}