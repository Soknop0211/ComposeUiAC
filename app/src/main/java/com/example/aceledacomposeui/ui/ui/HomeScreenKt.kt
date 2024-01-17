package com.example.aceledacomposeui.ui.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Bitmap
import android.util.Log
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.aceledacomposeui.R
import com.example.aceledacomposeui.model.HomeExtraModel
import com.example.aceledacomposeui.ui.screen.AppScreen
import com.example.aceledacomposeui.ui.theme.AceledaBankLogo
import com.example.aceledacomposeui.ui.theme.AceledaComposeUITheme
import com.example.aceledacomposeui.ui.theme.AcledaAppLogo
import com.example.aceledacomposeui.ui.theme.Gray
import com.example.aceledacomposeui.ui.theme.KhQrLogo
import com.example.aceledacomposeui.ui.theme.Primary
import com.example.aceledacomposeui.ui.theme.Red
import com.example.aceledacomposeui.ui.theme.SecondPrimary
import com.example.aceledacomposeui.ui.theme.SecondYellow
import com.example.aceledacomposeui.ui.theme.TransparentLight
import com.example.aceledacomposeui.ui.theme.White
import com.example.aceledacomposeui.ui.theme.Yellow
import com.example.aceledacomposeui.utils.Screen
import com.example.aceledacomposeui.utils.Utils.getListExtraHomeMenu
import com.example.aceledacomposeui.utils.Utils.getListHomeMenu
import com.example.aceledacomposeui.utils.fontFamily
import com.example.aceledacomposeui.view_model.HomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreenKt(
    avController: NavController,
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RectangleShape,
                drawerContainerColor = Primary,
                modifier = Modifier
                    .wrapContentSize()
            ) {
                /*DrawerContent(avController, mProfileClick = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }) {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }*/
            }
        },
    ) {

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
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            },
                            modifier = Modifier.align(Alignment.CenterVertically)
                        ) {
                            Icon(
                                Icons.Default.Menu,
                                tint = White,
                                contentDescription = "Menu"
                            )
                        }

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

            ContentList(avController)
        }
    }

}

@Composable
fun DrawerHeader(
    avController: NavController,
    mProfileClick: () -> Unit,
    modifier: Modifier = Modifier,
    mNavBackStackEntry: NavBackStackEntry
) {
    val wd = LocalConfiguration.current.screenWidthDp
    val midWd = ((wd / 2) + (wd / 4)).dp
    var bitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }

    Box(
        modifier = Modifier
            .clickable {
                avController.navigate(AppScreen.ProfileScreen.route)
                mProfileClick.invoke()
            }
    ) {
        Row(
            modifier = modifier
                .background(SecondPrimary)
                .padding(15.dp)
                .width(midWd)
        ) {

            // Get Result Call Back From Navigation
            mNavBackStackEntry.savedStateHandle.get<Bitmap>("bitmap")?.let {
                bitmap = it
            }

            // Save Data
            if (bitmap == null) {
                Image(
                    painter = painterResource(id = R.drawable.ic_my_profile),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
            } else {
                Image(
                    bitmap = bitmap!!.asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
            }

            Column(
                modifier = modifier
                    .wrapContentSize()
                    .padding(10.dp)
            ) {
                Text(
                    text = "Sok Nop",
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                )

                Text(
                    text = "012 345 678",
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }

        }
    }
}

@Composable
fun DrawerContent(avController: NavController,
                  mNavBackStackEntry : NavBackStackEntry,
                  mActivity : Activity,
                  mProfileClick: () -> Unit, mOnClick: () -> Unit) {
    Column(
        modifier = Modifier
            .background(Primary),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        DrawerHeader(avController, mProfileClick, mNavBackStackEntry = mNavBackStackEntry)

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp, end = 10.dp)
        ) {
            items(
                items = screens,
                itemContent = {
                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                mOnClick.invoke()
                            }
                            .padding(start = 5.dp, end = 5.dp, top = 10.dp, bottom = 10.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = it.logo),
                            tint = SecondYellow,
                            contentDescription = "Description"
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = it.title,
                            color = White,
                            fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                            modifier = Modifier
                                .wrapContentSize()
                        )
                    }
                })
        }

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        )
        {
            Text(
                text = "Version 1.0",
                color = White,
                fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                modifier = Modifier
                    .wrapContentSize()
            )
        }

    }
}

@androidx.annotation.OptIn(ExperimentalGetImage::class)
@Composable
fun ContentList(
    avController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val rememberScrollState = rememberScrollState()
    val horizontalDp = 12.dp
    val localDensity = LocalDensity.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState),
        ) {
            // Slider
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp)
            ) {
                InitSlider()
            }

            // ** Categories **

            // Create element height in dp state
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
                                        with(localDensity) { (coordinates.size.height.toDp() + 8.dp) * 3 }
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

            /*val  listCate = (1..2).map { it.toString() }
            val wd = LocalConfiguration.current.screenWidthDp
            val midWd = ((wd / 2).dp - 8.dp)

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
                            Card(
                                modifier = Modifier
                                    .width(midWd)
                                    .padding(horizontal = 8.dp, vertical = 8.dp)
                                    .clickable {

                                    },
                                colors = CardDefaults.cardColors(
                                    containerColor = if (index % 2 == 0) SecondYellow else Red,
                                ),
                                shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation =  6.dp
                                ),
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(5.dp),
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_currency_exchange),
                                        contentDescription = "android image",
                                        tint = White,
                                        modifier = Modifier
                                            .size(30.dp)
                                            .padding(horizontal = 5.dp)
                                            .align(Alignment.Top)
                                    )

                                    Column (
                                        modifier = Modifier
                                            .padding(horizontal = 5.dp)
                                    ){

                                        Text(
                                            text = "Exchange Rate",
                                            style = TextStyle(color = White),
                                            textAlign = TextAlign.Start,
                                            maxLines = 2
                                        )

                                        Text(
                                            text = "You can check with exchange rate",
                                            color = White,
                                            fontSize = 15.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }*/

            // ** Service Main **
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
                        text = "Special Categories",
                        fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                        color = White,
                        style = TextStyle(fontSize = 18.sp)
                    )
                }
            }

            val mServiceList = getListExtraHomeMenu()

            Box(
                modifier = Modifier
                    .padding(horizontal = horizontalDp, vertical = 5.dp)
            ) {
                LazyRow(
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = TransparentLight)
                ) {
                    item {
                        mServiceList.forEachIndexed { _, it ->
                            CardSpecialItem(it)
                        }
                    }
                }
            }

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
                        text = "Recommended",
                        fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                        color = White,
                        style = TextStyle(fontSize = 18.sp)
                    )

                    // Right arrow icon
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .weight(weight = 1f, fill = false)
                            .clickable {
                                /*mActivity?.let {
                                    MoreServiceActivity.start(it)
                                }*/
                                avController.navigate(AppScreen.SeeMoreScreen.route)
                            },
                    ) {

                        Text(
                            text = "View All",
                            fontFamily = fontFamily,
                            color = White,
                            style = TextStyle(fontSize = 14.sp)
                        )

                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowRight,
                            contentDescription = "contentDescription",
                            tint = White
                        )
                    }
                }
            }

            // Create element height in dp state
            var columnHeightListDp by remember {
                mutableStateOf(350.dp)
            }
            val listItem = (1..4).map { it.toString() }

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
                itemsIndexed(items = listItem) { index, item ->
                    // items(list.size) { photo ->
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
                                // Set column height using the LayoutCoordinates
                                columnHeightListDp =
                                    with(localDensity) { (coordinates.size.height.toDp() + 8.dp) * midSize }
                            }
                            .padding(8.dp),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(0.dp, Primary)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentAlignment = Alignment.Center

                        ) {
                            Image(
                                contentScale = ContentScale.FillBounds,
                                painter = painterResource(id = mLogo),
                                contentDescription = "android image",
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(vertical = 10.dp, horizontal = 10.dp)
                            ) {
                                Text(
                                    text = "",
                                    maxLines = 1,
                                    color = White,
                                )
                            }
                        }

                    }
                }
            }

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
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = TransparentLight)
                ) {
                    item {
                        mCustomListList.forEachIndexed { _, it ->
                            CardCustomServiceItem(it)
                        }
                    }
                }
            }

            // Exit Button
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(
                        color = SecondYellow,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable {
                        avController.navigate(AppScreen.HomeNewScreen.route)
                    },
            ) {
                Text(
                    text = "New Home",
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 3.dp, bottom = 5.dp),
                    fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                    color = White,
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }
    }
}

@Composable
private fun CardSpecialItem(it : HomeExtraModel) {
    val wd = LocalConfiguration.current.screenWidthDp
    val width = (wd / 2.8).dp
    val height = (wd / 2.4).dp

    androidx.compose.material.Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .width(width)
            .height(height),
        backgroundColor = SecondPrimary,
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 6.dp,
    ) {
        /*Row(
            modifier = Modifier
                .padding(5.dp),
        ) {
            Icon(
                painter = painterResource(id = it.logo),
                contentDescription = "android image",
                tint = White,
                modifier = Modifier
                    .size(30.dp)
                    .padding(horizontal = 5.dp)
                    .align(Alignment.Top)
            )

            Text(
                text = it.name,
                style = TextStyle(color = White),
                textAlign = TextAlign.Start,
                maxLines = 2,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(vertical = 5.dp, horizontal = 5.dp)
            )
        }*/

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
        ) {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .background(SecondYellow, shape = CircleShape)
            ){
                Icon(
                    painter = painterResource(id = it.logo),
                    contentDescription = "android image",
                    tint = White,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(horizontal = 5.dp)
                )
            }

            Column (
                modifier = Modifier
                    .padding(vertical = 5.dp, horizontal = 5.dp)
            ){
                Text(
                    text = it.name,
                    color = White,
                    style = TextStyle(color = White),
                    textAlign = TextAlign.Start,
                    maxLines = 2
                )

                Text(
                    text = it.description,
                    color = Gray,
                    style = TextStyle(color = White),
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 5,
                    fontSize = 13.sp
                )
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
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.Start),
                style = TextStyle(
                    textAlign = TextAlign.Start
                )
            )
        }
    }
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
            Log.d("jeeeeeeeeeeeeeeeee", "${pagerState.currentPage + 1} ${pagerState.pageCount}")
        }
    }

    Box {
        com.google.accompanist.pager.HorizontalPager(
            count = imageSlider.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
        ) { page ->
            androidx.compose.material.Card(
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(2.dp, Primary),
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
                .align(Alignment.BottomCenter)
                .padding(15.dp)
        )
    }
}

@Composable
fun CustomIconTop(onClick: () -> Unit, icon: ImageVector) {
    IconButton(onClick = { onClick.invoke() }) {
        Icon(
            imageVector = icon,
            tint = White,
            contentDescription = "Localized description",
            modifier = Modifier
                .size(25.dp)
        )
    }
}


val screens = listOf(
    Screen.CountryLanguage, Screen.Setting, Screen.Location,
    Screen.TermsCondition, Screen.SecurityTip, Screen.FAQs,
    Screen.InviteFriends, Screen.ACLEDABankStock, Screen.CustomerLoyalty,
    Screen.CustomerUs, Screen.Help
)


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    AceledaComposeUITheme {
        // ContentLayout()

        // LazyVerticalGridList()

        /*CardCustomServiceItem(
            HomeExtraModel("", "Exchange Rating", "", true, 0)
        )*/

        /*CardSpecialItem(
            HomeExtraModel(
                "favorites",
                "Favorites",
                "Save recipient information for quick transaction.",
                false,
                R.drawable.ic_favorite,
            )
        )*/

        /*InitSlider()*/
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .padding(16.dp)
                .background(
                    color = Color.Cyan,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Text(
                text = "New Home",
                modifier = Modifier.padding(10.dp),
                fontSize = 16.sp
            )
        }
    }
}