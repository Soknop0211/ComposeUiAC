package com.example.aceledacomposeui.ui.ui

import android.annotation.SuppressLint
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aceledacomposeui.R
import com.example.aceledacomposeui.model.HomeExtraModel
import com.example.aceledacomposeui.ui.RowItem
import com.example.aceledacomposeui.ui.screen.AppScreen
import com.example.aceledacomposeui.ui.theme.AceledaBankLogo
import com.example.aceledacomposeui.ui.theme.AceledaComposeUITheme
import com.example.aceledacomposeui.ui.theme.AcledaAppLogo
import com.example.aceledacomposeui.ui.theme.Black
import com.example.aceledacomposeui.ui.theme.BlueLightTxt
import com.example.aceledacomposeui.ui.theme.BlueTxt
import com.example.aceledacomposeui.ui.theme.Gray
import com.example.aceledacomposeui.ui.theme.KhQrLogo
import com.example.aceledacomposeui.ui.theme.LightGrayText
import com.example.aceledacomposeui.ui.theme.Primary
import com.example.aceledacomposeui.ui.theme.Red
import com.example.aceledacomposeui.ui.theme.SecondPrimary
import com.example.aceledacomposeui.ui.theme.SecondYellow
import com.example.aceledacomposeui.ui.theme.TransparentLight
import com.example.aceledacomposeui.ui.theme.White
import com.example.aceledacomposeui.ui.theme.Yellow
import com.example.aceledacomposeui.utils.Utils.getListExtraHomeMenu
import com.example.aceledacomposeui.utils.Utils.getListHomeMenu
import com.example.aceledacomposeui.utils.Utils.getListRecentTransfer
import com.example.aceledacomposeui.utils.fontFamily
import com.example.aceledacomposeui.utils.logDebug
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNewScreen(avController: NavController = rememberNavController(), ) {

    val verticalGradientBrush = Brush.verticalGradient(
        colors = listOf(
            SecondPrimary,
            Primary,
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Primary),
    ) {
        TopAppBar(
            modifier = Modifier.background(verticalGradientBrush),
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color.Transparent
            ),
            title = {
                Text(
                    "",
                    color = colorResource(id = R.color.white),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                Row {
                    Image(
                        painter = painterResource(id = AceledaBankLogo),
                        modifier = Modifier
                            .width(100.dp)
                            .wrapContentHeight()
                            .align(Alignment.CenterVertically),
                        contentDescription = "Localized description"
                    )
                }
            },
            actions = {
                val isExpanded by remember { mutableStateOf(false) }
                val iconFavorite: ImageVector =
                    if (isExpanded) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder

                CustomIconTop(
                    onClick = {
                        avController.navigate(AppScreen.NotificationScreen.route)
                    },
                    Icons.Filled.Notifications
                )

                /*CustomIconTop(
                    onClick = {
                        isExpanded = !isExpanded
                    },
                    icon = iconFavorite
                )*/

                Box(modifier = Modifier.padding(end = 10.dp)) {
                    Image(
                        painter = painterResource(id = KhQrLogo),
                        contentDescription = "Localized description",
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(5.dp))
                            .background(Red)
                            .padding(3.dp)
                            .size(18.dp)
                            .clickable { }
                    )
                }
            }
        )

        BodyContent(avController)
    }

}

@androidx.annotation.OptIn(ExperimentalGetImage::class)
@Composable
fun BodyContent(avController: NavController) {
    val rememberScrollState = rememberScrollState()
    val horizontalDp = 12.dp
    val localDensity = LocalDensity.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState),
        ) {
            CategoriesMenu(horizontalDp, localDensity)

            Categories2Item(horizontalDp)

            RecentTransaction(horizontalDp)

            SliderRecommended()

            SpecialServiceList()

            SpecialOfferList(horizontalDp, localDensity)

            CustomServiceFavorite(horizontalDp)

        }
    }
}

@Composable
private fun CategoriesMenu(horizontalDp : Dp, mDensity : Density) {
    var columnHeightDp by remember {
        mutableStateOf(350.dp)
    }
    val mList = getListHomeMenu()

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .height((columnHeightDp)),
        columns = GridCells.Fixed(3),
        userScrollEnabled = false,
        contentPadding = PaddingValues(
            horizontal = horizontalDp,
            vertical = 5.dp,
        ),

        content = {
            items(mList.size) { index ->
                Card(
                    modifier = Modifier
                        .onGloballyPositioned { coordinates ->
                            // Set column height using the LayoutCoordinates
                            columnHeightDp =
                                with(mDensity) { (coordinates.size.height.toDp() + 8.dp) * 3 }
                        }
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = SecondPrimary,
                    ),
                    shape = RoundedCornerShape(20.dp),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = mList[index].logo),
                            contentDescription = "android image",
                            tint = White,
                            modifier = Modifier
                                .size(35.dp)
                                .padding(top = 5.dp)
                        )
                        Text(
                            text = mList[index].name,
                            color = White,
                            maxLines = 1,
                            style = TextStyle(fontSize = 12.sp),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 5.dp)
                        )
                    }
                }
            }
        }
    )
}

@Composable
private fun Categories2Item(horizontalDp : Dp = 10.dp) {
    val  listCate = (1..2).map { it.toString() }

    Box(modifier = Modifier
        .padding(horizontal = horizontalDp, vertical = 5.dp)
    ) {
        LazyRow(
            userScrollEnabled = false,
            modifier = Modifier
                .wrapContentSize()
        ) {
            item {
                listCate.forEachIndexed { index, it ->
                    CardCategoriesItem2(index)
                }
            }
        }
    }
}

@Composable
private fun CardCategoriesItem2(index : Int) {
    val wd = LocalConfiguration.current.screenWidthDp
    val midWd = ((wd / 2).dp - 8.dp)

    Card(
        modifier = Modifier
            .width(midWd)
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clickable {

            },
        colors = CardDefaults.cardColors(
            containerColor = if (index % 2 == 0) BlueTxt else BlueLightTxt,
        ),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation =  6.dp
        ),
    ) {
        Box (
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .heightIn(80.dp)
        ){
            if (index % 2 == 0) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 5.dp, horizontal = 10.dp),
                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "Exchange Rate",
                            style = TextStyle(color = White),
                            textAlign = TextAlign.Start,
                            maxLines = 2
                        )

                        Box(
                            modifier = Modifier
                                .weight(weight = 1f, fill = false)
                                .padding(horizontal = 5.dp)
                                .background(Primary, shape = CircleShape)
                        ){
                            Icon(
                                painter = painterResource(id = R.drawable.ic_currency_exchange),
                                contentDescription = "android image",
                                tint = White,
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(horizontal = 5.dp)
                            )
                        }
                    }

                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Row {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "android image",
                                tint = SecondYellow,
                                modifier = Modifier
                                    .size(15.dp)
                                    .align(Alignment.CenterVertically)
                                    .padding(end = 5.dp)
                            )

                            Text(
                                text = "Dolla",
                                style = TextStyle(color = White),
                                textAlign = TextAlign.Start,
                                maxLines = 2
                            )
                        }
                        Text(
                            text = "1$",
                            style = TextStyle(color = White),
                            textAlign = TextAlign.Start,
                            maxLines = 2,
                            modifier = Modifier
                                .weight(weight = 1f, fill = false)
                        )
                    }

                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Row {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "android image",
                                tint = SecondYellow,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .size(15.dp)
                                    .padding(end = 5.dp)
                            )

                            Text(
                                text = "Real",
                                style = TextStyle(color = White),
                                textAlign = TextAlign.Start,
                                maxLines = 2
                            )
                        }
                        Text(
                            text = "4088R",
                            style = TextStyle(color = White),
                            textAlign = TextAlign.Start,
                            maxLines = 2,
                            modifier = Modifier
                                .weight(weight = 1f, fill = false)
                        )
                    }

                    // Line
                    val colorGradient = Brush.horizontalGradient(
                        colors = listOf(
                            Yellow,
                            BlueLightTxt,
                        )
                    )

                    Divider(
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .background(colorGradient),
                        thickness = 1.dp,
                    )
                }
            } else {
                Row (
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 5.dp, horizontal = 10.dp),
                ){
                    Image(
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(id = AcledaAppLogo),
                        contentDescription = "Localized description",
                    )
                    Column (
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    ){
                        Text(
                            text = "Other Service",
                            style = TextStyle(color = White),
                            textAlign = TextAlign.Start,
                            fontSize = 18.sp,
                            maxLines = 1
                        )

                        Text(
                            text = "Acleda Services",
                            style = TextStyle(color = LightGrayText),
                            textAlign = TextAlign.Start,
                            fontSize = 13.sp,
                            maxLines = 1
                        )
                    }

                }
            }
        }
    }
}

@Composable
private fun RecentTransaction(horizontalDp : Dp = 10.dp) {
    Column (
        modifier = Modifier
            .wrapContentSize()
            .clip(shape = RoundedCornerShape(0.dp))
            .background(color = TransparentLight)
    ){
        Box(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 5.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.wrapContentSize(),
                    text = "Recent Transaction",
                    fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                    color = White,
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }

        val mServiceList = getListRecentTransfer()

        Box(
            modifier = Modifier
                .padding(horizontal = horizontalDp, vertical = 5.dp)
        ) {
            LazyRow {
                item {
                    mServiceList.forEachIndexed { index, it ->
                        CardRecentTransactionItem(if (index == mServiceList.size - 1) 8.dp else 3.dp, it)
                    }
                }
            }
        }
    }

}

@Composable
private fun CardRecentTransactionItem(end : Dp = 0.dp, it : HomeExtraModel) {
    val wd = LocalConfiguration.current.screenWidthDp
    val width = (wd / 4).dp

    androidx.compose.material.Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = end)
            .width(width)
            .wrapContentHeight(),
        backgroundColor = SecondPrimary,
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 6.dp,
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(5.dp),
        ) {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(Primary, shape = CircleShape)
            ){
                Image(
                    modifier = Modifier
                        .size(45.dp),
                    painter = painterResource(id = AcledaAppLogo),
                    contentDescription = "Localized description",
                )
            }

            Column (
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 10.dp, horizontal = 5.dp)
            ){
                androidx.compose.material.Text(
                    text = it.name,
                    color = White,
                    style = TextStyle(fontSize = 12.sp, color = White, fontWeight = FontWeight.Normal),
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    minLines = 2
                )
            }
        }
    }
}

@Composable
private fun SliderRecommended() {
    Box(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(weight = 1f, fill = false)
                    .wrapContentSize(),
                text = "Recommended",
                fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                color = White,
                style = TextStyle(fontSize = 18.sp)
            )

            Icon(
                painter = painterResource(id = R.drawable.baseline_more_horiz_24),
                contentDescription = "contentDescription",
                tint = White
            )
        }
    }

    InitSlider()

}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun InitSlider() {
    val pagerState = rememberPagerState(initialPage = 0)
    val imageSlider = listOf(
        painterResource(id = R.drawable.img_interest),
        painterResource(id = R.drawable.img_loan),
        painterResource(id = R.drawable.img_qr_code)
    )

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2600)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount)
            )

            "${pagerState.currentPage + 1} ${pagerState.pageCount}".logDebug()
        }
    }

    Column {
        com.google.accompanist.pager.HorizontalPager(
            count = imageSlider.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
        ) { page ->
            androidx.compose.material.Card(
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(0.dp, Color.Transparent),
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                Image(
                    painter = imageSlider[page],
                    contentDescription = "Slider",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = White,
            inactiveColor = Yellow,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        )
    }
}

@Composable
private fun SpecialServiceList() {
    Box(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .weight(weight = 1f, fill = false),
                text = "Special Recommended",
                fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                color = White,
                style = TextStyle(fontSize = 18.sp)
            )

            Icon(
                painter = painterResource(id = R.drawable.baseline_more_horiz_24),
                contentDescription = "contentDescription",
                tint = White
            )
        }
    }

    val mList = (1..4).map { it.toString() }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        item {
            mList.forEachIndexed { index, _ ->
                CardSpecialItem(index = index, if (index == mList.size - 1) 10.dp else 3.dp)
            }
        }
    }
}

@Composable
fun CardSpecialItem(index: Int = 0, end: Dp = 0.dp) {
    val wd = LocalConfiguration.current.screenWidthDp
    val midWd = ((wd / 2) + (wd / 4) ).dp

    Card(
        modifier = Modifier
            .width(midWd)
            .wrapContentSize()
            .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = end)
            .clickable { },

        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        val drawableImg = if (index % 2 == 0) R.drawable.img_interest else R.drawable.img_qr_code

        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(color = SecondPrimary)
        ) {
            Image(
                contentScale = ContentScale.FillBounds,
                painter = painterResource(id = drawableImg),
                contentDescription = "android image",
                modifier = Modifier
                    .fillMaxSize()
                    .height(150.dp)
            )
            
            Column (
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 10.dp)
            ){
                Row {
                    Image(
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(id = AcledaAppLogo),
                        contentDescription = "Localized description",
                    )

                    Text(
                        modifier = Modifier
                            .padding(horizontal = 5.dp),
                        text = "Special Recommended Promotion",
                        maxLines = 1,
                        color = White,
                        fontSize = 16.sp
                    )

                }

                Box (
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                ){
                    androidx.compose.material.Text(
                        text = "ACLEDA Bank Plc. is a public limited company, formed under the Banking and Financial Institutions Law of the Kingdom of Cambodia. Originally, it was founded in January 1993, as a national NGO for micro and small enterprises' development and credit.",
                        maxLines = 4,
                        minLines = 4,
                        overflow = TextOverflow.Ellipsis,
                        color = White,
                        fontSize = 14.sp
                    )
                }
            }

        }

    }
}

@Composable
private fun CardCustomServiceItem (item : HomeExtraModel) {
    val wd = LocalConfiguration.current.screenWidthDp
    val midWd = ((wd / 3.8).dp)

    Card(
        modifier = Modifier
            .width(midWd)
            .height(midWd)
            .padding(10.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = White,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .wrapContentHeight()
        ) {
            Image(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .size(30.dp),
                painter = painterResource(id = AcledaAppLogo),
                contentDescription = "Localized description",
            )

            Text(
                text = item.name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Primary,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.Start),
                style = TextStyle(
                    textAlign = TextAlign.Start
                )
            )
        }
    }
}

@Composable
private fun SpecialOfferList(horizontalDp : Dp = 10.dp, mDensity : Density = LocalDensity.current) {
    // Recommended Service
    Box(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(weight = 1f, fill = false)
                    .wrapContentSize(),
                text = "Special Offer",
                fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                color = White,
                style = TextStyle(fontSize = 18.sp)
            )

            Icon(
                painter = painterResource(id = R.drawable.baseline_more_horiz_24),
                contentDescription = "contentDescription",
                tint = White
            )
        }
    }

    // Create element height in dp state
    var columnHeightListDp by remember {
        mutableStateOf(350.dp)
    }
    val listItem = (1..5).map { it.toString() }

    val midSize = if (listItem.size % 2 == 0) listItem.size / 2 else (listItem.size / 2) + 1

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .height(columnHeightListDp),
        userScrollEnabled = false,
        columns = GridCells.Fixed(2),

        contentPadding = PaddingValues(
            horizontal = horizontalDp,
            vertical = 10.dp
        )
    ) {
        items(
            listItem.size,
            span = { index ->
                val spanCount = if (index == 0) 2 else 1   // GridCells.Fixed(2) => 2 / 2 = 1 col, 2 / 1 = 2 col
                GridItemSpan(spanCount)
            }
        ) {it ->
            var mLogo =
                if (it % 2 == 0) {
                    R.drawable.img_qr_code
                } else {
                    R.drawable.img_interest
                }

            Card(
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        columnHeightListDp =
                            with(mDensity) { (coordinates.size.height.toDp() + 5.dp) * midSize }
                    }
                    .padding(5.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(0.dp, Primary)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center

                ) {
                    Image(
                        contentScale = ContentScale.FillBounds,
                        painter = painterResource(id = mLogo),
                        contentDescription = "android image",
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

            }
        }

        /*itemsIndexed(items = listItem) { index, _ ->
            val mLogo = if (index % 2 == 0) {
                R.drawable.img_qr_code
            } else if (index % 3 == 0) {
                R.drawable.img_loan
            } else {
                R.drawable.img_interest
            }

            Card(
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        columnHeightListDp =
                            with(mDensity) { (coordinates.size.height.toDp() + 5.dp) * midSize }
                    }
                    .padding(5.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(0.dp, Primary)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center

                ) {
                    Image(
                        contentScale = ContentScale.FillBounds,
                        painter = painterResource(id = mLogo),
                        contentDescription = "android image",
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

            }
        }*/
    }
}

@Composable
private fun CustomServiceFavorite(horizontalDp : Dp = 10.dp) {
    Column (
        modifier = Modifier
            .wrapContentSize()
            .clip(shape = RoundedCornerShape(0.dp))
            .background(color = TransparentLight)
    ){
        // Recommended Service
        Box(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 5.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.wrapContentSize(),
                    text = "Custom Service",
                    fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                    color = White,
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }

        // ** Service Main **
        val mCustomListList = getListExtraHomeMenu()

        Box(
            modifier = Modifier
                .padding(horizontal = horizontalDp, vertical = 5.dp)
        ) {
            LazyRow(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(shape = RoundedCornerShape(0.dp))
            ) {
                item {
                    mCustomListList.forEachIndexed { _, it ->
                        CardCustomServiceItem(it)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeNewPreview() {
    AceledaComposeUITheme {
        SpecialOfferList()
    }
}