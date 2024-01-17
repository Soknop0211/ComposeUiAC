package com.example.aceledacomposeui.ui.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aceledacomposeui.R
import com.example.aceledacomposeui.model.NotificationModel
import com.example.aceledacomposeui.ui.theme.AceledaComposeUITheme
import com.example.aceledacomposeui.ui.theme.AceledaSplashLogo
import com.example.aceledacomposeui.ui.theme.AcledaAppLogo
import com.example.aceledacomposeui.ui.theme.Black
import com.example.aceledacomposeui.ui.theme.LightGray
import com.example.aceledacomposeui.ui.theme.Primary
import com.example.aceledacomposeui.ui.theme.White
import com.example.aceledacomposeui.ui.theme.topBarBrush
import com.example.aceledacomposeui.utils.logDebug
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import org.burnoutcrew.reorderable.move
import org.burnoutcrew.reorderable.rememberReorderState
import org.burnoutcrew.reorderable.reorderable
import kotlin.math.absoluteValue

@Composable
fun ItemDetailList() {
    val state = rememberReorderState()

    val mList = ArrayList<NotificationModel>()
    for (i in 1..5) {
        mList.add(
            NotificationModel(
            i.toString(),
            "KHQR Payment ACLDA Mobile",
            "05, Jan, 2024 11:30 AM",
            "You have made payment 100$ from Mr. Dara Bank by KHQR, on 05, Jan, 2024 11:30 AM. Thanks you for using our service ! You have made payment 100$ from Mr. Dara Bank by KHQR, on 05, Jan, 2024 11:30 AM. Thanks you for using our service !"
        )
        )
    }

    val mutableStateList = mList.toMutableStateList()

    LazyColumn (
        state = state.listState,
        modifier = Modifier
            .reorderable(state, { from, to -> mutableStateList.move(from.index, to.index) })
    ){
        item {
            Column(
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_interest),
                    contentDescription = "Slider",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }

        item {
            Column (
                modifier = Modifier
                    .padding(10.dp)
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
                        fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                        overflow = TextOverflow.Ellipsis,
                        color = Black,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailKt(
    navController : NavController,
    mId : String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.background(topBarBrush),
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                title = {
                    Text(
                        text = "Item Detail",
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
        mId.logDebug("mItemIdDebug")

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = LightGray)
        ){
            CollapsingToolbar()
        }
    }
}

@Composable
fun CardItemDetailItem(
    modifier: Modifier = Modifier,
    mItem : NotificationModel = NotificationModel()
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 5.dp,
                bottom = 5.dp,
                end = 10.dp,
                start = 10.dp
            )
            .clickable {},

        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = White)
                .wrapContentHeight()
        ) {
            // Init click on more
            var mShowLess by remember { mutableIntStateOf(2) }

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 10.dp)
            ){
                Row (
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                ){
                    Image(
                        modifier = Modifier
                            .size(25.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(id = AcledaAppLogo),
                        contentDescription = "Localized description",
                    )
                    Column (
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    ){
                        Text(
                            text = mItem.name ?: "",
                            maxLines = 1,
                            color = Primary,
                            fontSize = 16.sp
                        )

                        Text(
                            text = mItem.date ?: "",
                            maxLines = 1,
                            color = Primary,
                            fontSize = 11.sp
                        )
                    }

                }

                Text(
                    text = mItem.description ?: "",
                    maxLines = mShowLess,
                    overflow = TextOverflow.Ellipsis,
                    color = Primary,
                    fontSize = 14.sp
                )

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .align(AbsoluteAlignment.Right)
                ) {
                    Text(
                        text = if (mShowLess > 2) "Less" else "More",
                        color = Primary,
                        fontSize = 11.sp,
                        textDecoration = TextDecoration.Underline,
                        modifier = modifier
                            .align(Alignment.CenterEnd)
                            .clickable {
                                mShowLess = if (mShowLess > 2) {
                                    2
                                } else {
                                    10
                                }
                            }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Top app bar")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app bar",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text =
                """ This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.

                    It also contains some basic inner content, such as this text.

                    You have pressed the floating action button $presses times.
                """.trimIndent(),
            )
        }
    }
}


@Composable
fun CollapsingToolbar() {
    val scroll: ScrollState = rememberScrollState(0)
    Box(modifier = Modifier.fillMaxSize()) {
        Header()

        Body(scroll)
    }
}

private val headerHeight = 275.dp
@Composable
private fun Header() {
    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(headerHeight)) {
        Image(
            painter = painterResource(id = R.drawable.promotion_img),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )

        Box(
            Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xAA000000)),
                        startY = 3 * headerHeightPx / 4 // to wrap the title only
                    )
                )
        )
    }
}

@Composable
private fun Body(scroll: ScrollState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(scroll)
    ) {
        Spacer(Modifier.height(headerHeight))

        Box(
            modifier = Modifier
                .background(color = Color.White)
        ) {
            Column (
                modifier = Modifier
                    .padding(10.dp)
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
                        fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                        overflow = TextOverflow.Ellipsis,
                        color = Black,
                        fontSize = 14.sp
                    )
                }

                repeat(5) {
                    Text(
                        text = "ACLEDA Bank Plc is a public limited company, formed under the Banking and Financial Institutions Law of the Kingdom of Cambodia. Originally, it was founded in January 1993, as a national NGO for micro and small enterprises' development and credit.",
                        textAlign = TextAlign.Justify,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                        color = Black,
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemDetailPreview() {
    AceledaComposeUITheme {
        ItemDetailKt(navController = rememberNavController(), mId = "")
    }
}