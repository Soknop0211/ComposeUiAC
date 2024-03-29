package com.example.aceledacomposeui.ui.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.aceledacomposeui.R
import com.example.aceledacomposeui.model.HomeExtraModel
import com.example.aceledacomposeui.model.HomeMainList
import com.example.aceledacomposeui.ui.screen.AppScreen
import com.example.aceledacomposeui.ui.screen_activity.AccountActivity
import com.example.aceledacomposeui.ui.screen_activity.KeyBoardInputActivity
import com.example.aceledacomposeui.ui.screen_activity.MobileTopUpActivity
import com.example.aceledacomposeui.ui.screen_activity.ScanQrCodeActivity
import com.example.aceledacomposeui.ui.theme.AceledaBankLogo
import com.example.aceledacomposeui.ui.theme.AceledaComposeUITheme
import com.example.aceledacomposeui.ui.theme.AcledaAppLogo
import com.example.aceledacomposeui.ui.theme.Black
import com.example.aceledacomposeui.ui.theme.BlueLightTxt
import com.example.aceledacomposeui.ui.theme.BlueTxt
import com.example.aceledacomposeui.ui.theme.Gray
import com.example.aceledacomposeui.ui.theme.KhQrLogo
import com.example.aceledacomposeui.ui.theme.LightGray
import com.example.aceledacomposeui.ui.theme.Primary
import com.example.aceledacomposeui.ui.theme.Red
import com.example.aceledacomposeui.ui.theme.SecondPrimary
import com.example.aceledacomposeui.ui.theme.SecondYellow
import com.example.aceledacomposeui.ui.theme.ThirdPrimary
import com.example.aceledacomposeui.ui.theme.TransparentLight
import com.example.aceledacomposeui.ui.theme.White
import com.example.aceledacomposeui.ui.theme.Yellow
import com.example.aceledacomposeui.utils.Utils
import com.example.aceledacomposeui.utils.Utils.getListExtraHomeMenu
import com.example.aceledacomposeui.utils.Utils.getListHomeMenu
import com.example.aceledacomposeui.utils.Utils.getListRecentTransfer
import com.example.aceledacomposeui.utils.logDebug
import com.example.aceledacomposeui.view_model.HomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.draggedItem
import org.burnoutcrew.reorderable.move
import org.burnoutcrew.reorderable.rememberReorderState
import org.burnoutcrew.reorderable.reorderable
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNewScreen(avController: NavController,
                  mActivity: Activity,
                  mNavBackStackEntry : NavBackStackEntry,
                  onNavigateToSecondScreen: () -> Unit = {},
                  mViewModel : HomeViewModel = hiltViewModel()) {

    val verticalGradientBrush = Brush.verticalGradient(
        colors = listOf(
            SecondPrimary,
            Primary,
        )
    )

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
                DrawerContent(avController, mNavBackStackEntry, mActivity = mActivity, mProfileClick = {
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
                }
            }
        },
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Primary),
        ) {
            TopAppBar(
                modifier = Modifier.background(verticalGradientBrush),
                colors = TopAppBarDefaults.topAppBarColors(
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
                    CustomIconTop(
                        onClick = {
                            avController.navigate(AppScreen.NotificationScreen.route)
                        },
                        Icons.Filled.Notifications
                    )

                    Box(
                        modifier = Modifier
                        .padding(end = 10.dp)
                            .clickable {
                                avController.navigate(AppScreen.KhQrCodeScreen.route)
                            }
                    ) {
                        Image(
                            painter = painterResource(id = KhQrLogo),
                            contentDescription = "Localized description",
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(5.dp))
                                .background(Red)
                                .padding(3.dp)
                                .size(18.dp)
                        )
                    }
                }
            )

            /*HomeBody(
                mActivity,
                mViewModel.mHomeList.observeAsState().value ?: Utils.mainCategory(),
                mViewModel
            )*/

            BodyContent(
                mActivity,
                onNavigateToSecondScreen
            )
        }
    }

}

@Composable
fun HomeBody(mActivity: Context,
             mList : List<HomeMainList> = ArrayList(),
             mViewModel : HomeViewModel) {
    val horizontalDp = 12.dp
    val localDensity = LocalDensity.current

    val state = rememberReorderState()

    // First List
    val data = mList.toMutableStateList()

    LazyColumn(
        state = state.listState,
        modifier = Modifier
            .reorderable(state, { from, to ->
                data.move(from.index, to.index)

                // Update Local
                mViewModel.saveHomeScreenList(Utils.encodeToString(data))

                data.forEach {
                    it.id.logDebug("jeeeeeeeeeeeeeeeeeeeeeeeee")
                }
            })
    ) {
        items(data, { it }) { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .draggedItem(state.offsetByKey(item))
                    .detectReorderAfterLongPress(state),
            ) {
                Column (
                    modifier = Modifier
                        .background(LightGray)
                ){
                    when (item.id) {
                        "top_main" -> {
                            Column(
                                modifier = Modifier
                                    .background(Primary)
                            ) {
                                CategoriesMenu(localDensity, mActivity)

                                Categories2Item(horizontalDp)
                            }
                        }
                        "recent_transaction" -> {
                            RecentTransaction(horizontalDp)
                        }
                        else -> {
                            Column (
                                modifier = Modifier
                                    .background(color = LightGray)
                            ){
                                when (item.id) {
                                    "slider_recommended" -> {
                                        SliderRecommended()
                                    }
                                    "special_service" -> {
                                        SpecialServiceList()
                                    }
                                    "special_offer" -> {
                                        SpecialOfferList(horizontalDp, localDensity)
                                    }
                                    "call_center" -> {
                                        CallCenterList(horizontalDp, localDensity)
                                    }
                                    "advertise" -> {
                                        SliderRecommended(mIsAdvertise = true)

                                        Spacer(modifier = Modifier.padding(10.dp))
                                    }
                                    else ->{
                                        Spacer(modifier = Modifier.padding(0.dp))
                                    }
                                }
                            }
                        }
                    }
                }


            }
        }
    }
}

@androidx.annotation.OptIn(ExperimentalGetImage::class)
@Composable
fun BodyContent(
    mActivity: Activity? = null,
    onNavigateToSecondScreen: () -> Unit = {}
) {
    val rememberScrollState = rememberScrollState()
    val horizontalDp = 12.dp
    val localDensity = LocalDensity.current

    val endReached by remember {
        derivedStateOf {
            rememberScrollState.value == rememberScrollState.maxValue
        }
    }

    Box (
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState),
        ) {
            CategoriesMenu(localDensity, mActivity)

            Categories2Item(horizontalDp)

            RecentTransaction(horizontalDp)

            Column (
                modifier = Modifier
                    .background(color = LightGray)
            ){
                SliderRecommended()

                SpecialServiceList(onNavigateToSecondScreen)

                SpecialOfferList(horizontalDp, localDensity)

                CallCenterList(horizontalDp, localDensity)

                SliderRecommended(mIsAdvertise = true)

                /*CustomServiceFavorite(horizontalDp)*/

                Spacer(modifier = Modifier.padding(10.dp))

            }

        }

        Column(
            modifier = Modifier
                .padding()
                .align(Alignment.BottomEnd)
        ) {
            FloatingAction(endReached, rememberScrollState)
        }
    }

}

@Composable
private fun CategoriesMenu(mDensity: Density = LocalDensity.current, mActivity: Context ?= null) {
    var columnHeightDp by remember {
        mutableStateOf(350.dp)
    }

    var lineHeightDp by remember {
        mutableStateOf(50.dp)
    }

    val mList = getListHomeMenu().toMutableStateList()

    // Color
    val mStartList = listOf(
        ThirdPrimary,
        Gray,
        Gray
    )

    val mEndList = listOf(
        Gray,
        Gray,
        ThirdPrimary
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

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .height((columnHeightDp)),
        columns = GridCells.Fixed(3),
        userScrollEnabled = false,

        content = {
            itemsIndexed(items = mList) { index, _ ->
                Card(
                    modifier = Modifier
                        .onGloballyPositioned { coordinates ->
                            // Set column height using the LayoutCoordinates
                            lineHeightDp =
                                with(mDensity) { (coordinates.size.height.toDp()) }

                            columnHeightDp = lineHeightDp * 3
                        }
                        .clickable {
                            mActivity!!.menuNext(mList[index].id)
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = ThirdPrimary,
                    ),
                    shape = RoundedCornerShape(0.dp),
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
                                Icon(
                                    painter = painterResource(id = mList[index].logo),
                                    contentDescription = "android image",
                                    tint = White,
                                    modifier = Modifier
                                        .size(50.dp)
                                        .padding(top = 5.dp)
                                )
                                Text(
                                    text = mList[index].name,
                                    color = White,
                                    maxLines = 1,
                                    style = TextStyle(fontSize = 13.sp),
                                    modifier = Modifier
                                        .padding(top = 10.dp, bottom = 5.dp)
                                )
                            }

                            if (index <= 5) {
                                val mBrush =
                                    when (index) {
                                        0, 3 -> startXBrush
                                        1, 4 -> centerXBrush
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

                        if (index == 0 || index == 1 || index == 3 || index == 4 || index == 6 || index == 7) {
                            val mBrush =
                                when (index) {
                                    0, 1 -> startYBrush
                                    3, 4 -> centerYBrush
                                    else -> endYBrush
                                }

                            Divider(
                                modifier = Modifier
                                    .width(1.dp)
                                    .height(lineHeightDp)
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

@Composable
private fun Categories2Item(horizontalDp : Dp = 10.dp) {
    val  listCate = (1..2).map { it.toString() }

    Box(modifier = Modifier
        .background(color = ThirdPrimary)
        .padding(horizontal = horizontalDp, vertical = 5.dp)
    ) {
        LazyRow(
            userScrollEnabled = false,
            modifier = Modifier
                .wrapContentSize()
        ) {
            item {
                listCate.forEachIndexed { index, _ ->
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

    var isExpanded by remember { mutableStateOf(false) }
    val iconOnOff : Painter = painterResource(id = if (isExpanded) R.drawable.baseline_open_eyes else R.drawable.ic_close_eyes)

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
                .heightIn(100.dp)
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
                            text = "Total Balance",
                            fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                            style = TextStyle(color = White),
                            textAlign = TextAlign.Start,
                            maxLines = 2
                        )

                        Box(
                            modifier = Modifier
                                .weight(weight = 1f, fill = false)
                                .padding(start = 5.dp)
                        ){
                            Icon(
                                painter = iconOnOff,
                                contentDescription = "android image",
                                tint = White,
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(vertical = 5.dp)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null
                                    ) {
                                        isExpanded = !isExpanded
                                    }
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
                            Box(
                                modifier = Modifier
                                    .background(color = SecondYellow, shape = CircleShape)
                                    .align(Alignment.CenterVertically)
                                    .size(8.dp)
                                    .padding(end = 10.dp)
                            )

                            Text(
                                text = "USD",
                                style = TextStyle(color = White),
                                textAlign = TextAlign.Start,
                                fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                                maxLines = 2,
                                modifier = Modifier
                                    .padding(start = 5.dp)
                            )
                        }
                        Text(
                            text = if (isExpanded)  "100.00" else "***",
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
                            Box(
                                modifier = Modifier
                                    .background(color = BlueLightTxt, shape = CircleShape)
                                    .align(Alignment.CenterVertically)
                                    .size(8.dp)
                                    .padding(end = 10.dp)
                            )

                            Text(
                                text = "KHR",
                                style = TextStyle(color = White),
                                textAlign = TextAlign.Start,
                                fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                                maxLines = 2,
                                modifier = Modifier
                                    .padding(start = 5.dp)
                            )
                        }
                        Text(
                            text = if (isExpanded)  "400,088" else "***",
                            style = TextStyle(color = White),
                            textAlign = TextAlign.Start,
                            maxLines = 2,
                            fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                            modifier = Modifier
                                .weight(weight = 1f, fill = false)
                        )
                    }

                    // Line
                    val colorGradient = Brush.horizontalGradient(
                        colors = listOf(
                            SecondYellow,
                            SecondYellow,
                            BlueLightTxt,
                        )
                    )

                    Divider(
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .height(2.dp)
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
                        painter = painterResource(id = R.drawable.ic_exchange_currency),
                        contentDescription = "Localized description",
                    )
                    Column (
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    ){
                        Text(
                            text = "Exchange Rate",
                            style = TextStyle(color = White),
                            textAlign = TextAlign.Start,
                            maxLines = 1,
                            fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(bottom = 2.dp, top = 5.dp)
                        )

                        Text(
                            text = "1$ = 4088R",
                            style = TextStyle(color = LightGray),
                            textAlign = TextAlign.Start,
                            fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                            fontSize = 13.sp,
                            maxLines = 1,
                            modifier = Modifier
                                .padding(top = 2.dp, bottom = 5.dp)
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
            .background(color = Primary)
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
        backgroundColor = White,
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
                    .background(Color.Transparent, shape = CircleShape)
            ){
                Image(
                    modifier = Modifier
                        .size(45.dp),
                    painter = painterResource(id = it.logo),
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
                    color = Black,
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
private fun SliderRecommended(mIsAdvertise : Boolean = false) {
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
                text = if (mIsAdvertise) "Advertise" else "Recommended",
                fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                color = Black,
                style = TextStyle(fontSize = 18.sp)
            )

            Icon(
                painter = painterResource(id = R.drawable.baseline_more_horiz_24),
                contentDescription = "contentDescription",
                tint = Black
            )
        }
    }

    InitSlider(mIsAdvertise)

}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun InitSlider(mIsAdvertise : Boolean = false) {
    val pagerState = rememberPagerState(initialPage = 0)
    val imageSlider =
        if(!mIsAdvertise) {
            listOf(
                painterResource(id = R.drawable.special_offer),
                painterResource(id = R.drawable.promotion_img),
                painterResource(id = R.drawable.img_loan),
                painterResource(id = R.drawable.img_qr_code),
                painterResource(id = R.drawable.img_interest),
                )
        } else {
            listOf(
                painterResource(id = R.drawable.special_offer)
                )
        }

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
                .height(if (mIsAdvertise) 100.dp else 150.dp)
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

        if (imageSlider.size > 1) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Primary,
                inactiveColor = Yellow,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
            )
        }
    }
}

@Composable
private fun SpecialServiceList(
    onNavigateToSecondScreen: () -> Unit = {}
) {
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
                color = Black,
                style = TextStyle(fontSize = 18.sp)
            )

            Icon(
                painter = painterResource(id = R.drawable.baseline_more_horiz_24),
                contentDescription = "contentDescription",
                tint = Black
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
                CardSpecialItem(index = index, if (index == mList.size - 1) 10.dp else 3.dp, onNavigateToSecondScreen)
            }
        }
    }
}

@Composable
fun CardSpecialItem(index: Int = 0, end: Dp = 0.dp, onNavigateToSecondScreen: () -> Unit = {}) {
    val wd = LocalConfiguration.current.screenWidthDp
    val midWd = ((wd / 2) + (wd / 4) ).dp

    Card(
        modifier = Modifier
            .width(midWd)
            .wrapContentSize()
            .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = end)
            .clickable {
                // avController.navigate(AppScreen.UpdateScreen.route)
                //avController.navigate(AppScreen.UpdateScreen.getItemId(mId = "mItemIdTesting"))
                onNavigateToSecondScreen.invoke()
            },

        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        val drawableImg = if (index % 2 == 0) R.drawable.img_interest else R.drawable.img_qr_code

        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(color = White)
        ) {
            Image(
                contentScale = ContentScale.FillBounds,
                painter = painterResource(id = drawableImg),
                contentDescription = "android image",
                modifier = Modifier
                    .fillMaxSize()
                    .height(130.dp)
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
                        painter = painterResource(id = R.drawable.ic_promotion),
                        contentDescription = "Localized description",
                    )

                    Text(
                        modifier = Modifier
                            .padding(horizontal = 5.dp),
                        text = "Special Recommended Promotion",
                        fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                        maxLines = 1,
                        color = Black,
                        fontSize = 16.sp
                    )

                }

                Box (
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                ){
                    androidx.compose.material.Text(
                        text = "ACLEDA Bank Plc is a public limited company, formed under the Banking and Financial Institutions Law of the Kingdom of Cambodia. Originally, it was founded in January 1993, as a national NGO for micro and small enterprises' development and credit.",
                        maxLines = 3,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                        minLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        color = Black,
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
                color = Black,
                style = TextStyle(fontSize = 18.sp)
            )

            Icon(
                painter = painterResource(id = R.drawable.baseline_more_horiz_24),
                contentDescription = "contentDescription",
                tint = Black
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
            val mLogo =
                if (it % 2 == 0) {
                    R.drawable.promotion_img
                } else if (it == 3) {
                    R.drawable.img_interest
                } else {
                    R.drawable.special_offer
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

@Composable
private fun CallCenterList(horizontalDp : Dp = 10.dp, mDensity : Density = LocalDensity.current) {
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
                text = "Call Center Service",
                fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                color = Black,
                style = TextStyle(fontSize = 18.sp)
            )

            Icon(
                painter = painterResource(id = R.drawable.baseline_more_horiz_24),
                contentDescription = "contentDescription",
                tint = Black
            )
        }
    }

    // Create element height in dp state
    var columnHeightListDp by remember {
        mutableStateOf(200.dp)
    }
    val listItem = (1..3).map { it.toString() }

    /*1 = 1
    4 = 2
    7 = 3
    10 = 4
    13 = 5
    16 = 6
    19 = 7

    1 = 1
    2, 3, 4 = 2
    5, 6, 7 = 3
    8, 9, 10 = 4
    11, 12, 13 = 5
    14, 15, 16 = 6
    17, 18, 19 = 7

    if
        3 / 3 = 1 + 1
    6 / 3 = 2 + 1
    9 / 3 = 3 + 1

    else
    if < 3 = 1 + 1
    4 / 3 = 1 + 1
    5 / 3 = 1.5 + 1*/

    val midSize =
        if (listItem.size % 3 == 0) {
            (listItem.size / 3) + 1
        } else if (listItem.size < 3) {
            2
        } else {
            val dividend = listItem.size
            val divisor = 3
            val number = (dividend.toDouble() / divisor.toDouble()).roundToInt() + 1

            ("$number").logDebug("jeeeeeeeeeeeeeeeeeeeeeeeeee")

            number
        }
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .height(columnHeightListDp),
        userScrollEnabled = false,
        columns = GridCells.Fixed(3),

        contentPadding = PaddingValues(
            horizontal = horizontalDp,
            vertical = 10.dp
        )
    ) {
        items(
            listItem.size,
            span = { index ->
                val spanCount = if (index == 0) 3 else 1   // GridCells.Fixed(2) => 2 / 2 = 1 col, 2 / 1 = 2 col
                GridItemSpan(spanCount)
            }
        ) {it ->
            val mLogo =
                if (it % 2 == 0) {
                    R.drawable.special_offer
                } else {
                    R.drawable.promotion_img
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
    }
}

@Composable
fun FloatingAction(isVisibleButton : Boolean, scrollState: ScrollState) {
    val coroutineScope = rememberCoroutineScope()

    AnimatedVisibility(
        visible = isVisibleButton,
        enter = fadeIn(animationSpec = tween(1000)),
        exit = fadeOut(animationSpec = tween(1000)),
        /*enter = slideInVertically(),
        exit = slideOutVertically(),*/
        modifier = Modifier
            .padding(5.dp)
    ) {
        FloatingActionButton(
            onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollTo(0, animationSpec = tween(2000))
                }
            },
            containerColor = BlueLightTxt,
            shape = CircleShape,
            elevation = FloatingActionButtonDefaults.elevation(2.dp),
            modifier = Modifier
                .size(60.dp)
                .padding(5.dp)

        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowUp,
                contentDescription = "Reddit icon",
                tint = White,
            )
        }
    }
}

private fun Context.menuNext(id : String) {
    when (id) {
        "top_up" -> {
            MobileTopUpActivity.start(this)
        }
        "scan_qr" -> {
            ScanQrCodeActivity.start(this)
        }
        "pay_me" -> {
            KeyBoardInputActivity.start(this)
        }
        "account" -> {
            AccountActivity.start(this)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeNewPreview() {
    AceledaComposeUITheme {
        BodyContent()
    }
}