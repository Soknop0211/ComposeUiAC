package com.example.aceledacomposeui.ui.screen_activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aceledacomposeui.ui.theme.AceledaComposeUITheme
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRowDefaults
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aceledacomposeui.R
import com.example.aceledacomposeui.model.BankAccountModel
import com.example.aceledacomposeui.ui.theme.Black
import com.example.aceledacomposeui.ui.theme.Gray
import com.example.aceledacomposeui.ui.theme.Primary
import com.example.aceledacomposeui.ui.theme.Red
import com.example.aceledacomposeui.ui.theme.SecondPrimary
import com.example.aceledacomposeui.ui.theme.SecondYellow
import com.example.aceledacomposeui.ui.theme.White
import com.example.aceledacomposeui.ui.theme.sp12
import com.example.aceledacomposeui.ui.widget.BottomShadow
import com.example.aceledacomposeui.ui.widget.ToolAppBar
import kotlinx.coroutines.launch

class AccountActivity : ComponentActivity() {

    companion object {
        fun start(mContext: Context) {
            mContext.startActivity(Intent(mContext, AccountActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AceledaComposeUITheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AccountMain(this)
                }
            }
        }
    }
}

@Composable
private fun AccountMain(mActivity : Activity) {
    Scaffold(
        topBar = {
            ToolAppBar(
                mTitle = "My Accounts",
                mOnClickNavigation = { mActivity.finish() }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = White),
            contentAlignment = Alignment.Center
        ){
            AccountBody()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun AccountBody() {
    val tabItems = listOf(
        TabItem(
            title = "Accounts",
            unselectedIcon = painterResource(id = R.drawable.ic_account_payment),
            selectedIcon = painterResource(id = R.drawable.ic_account_payment)
        ),
        TabItem(
            title = "Trading",
            unselectedIcon = painterResource(id = R.drawable.ic_account_payment),
            selectedIcon = painterResource(id = R.drawable.ic_account_payment)
        ),
        TabItem(
            title = "Cards",
            unselectedIcon = painterResource(id = R.drawable.ic_account_payment),
            selectedIcon = painterResource(id = R.drawable.ic_account_payment)
        ),
        TabItem(
            title = "Link",
            unselectedIcon = painterResource(id = R.drawable.ic_account_payment),
            selectedIcon = painterResource(id = R.drawable.ic_account_payment)
        ),
    )

    var selectedTabIndex by remember {
        mutableIntStateOf(0) // or use mutableStateOf(0)
    }

    val pagerState = rememberPagerState {
        tabItems.size
    }

    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            divider = { },
            containerColor = Primary,
            selectedTabIndex = selectedTabIndex
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    modifier = Modifier
                        .background(if (selectedTabIndex == index) Primary else SecondPrimary),
                    selected = (index == selectedTabIndex),
                    onClick = {
                        selectedTabIndex = index

                        // change the page when the tab is changed
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(selectedTabIndex)
                        }
                    },
                    text = {
                        Text(text = item.title.uppercase(), fontSize = sp12, maxLines = 1)
                    },
                    /*icon = {
                        Icon(
                            painter = if (index == selectedTabIndex) item.selectedIcon else item.unselectedIcon,
                            contentDescription = null
                        )
                    },*/
                    selectedContentColor = White, unselectedContentColor = Gray
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { index ->
            AccountContent(index)
        }
    }

    // change the tab item when current page is changed
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }
}

@Composable
private fun AccountContent(index : Int = 0) {
    val mList =
        if (index == 0) {
            arrayListOf(
                BankAccountModel("1", "Wallet Account", "097 1206 897", "KHR", "100,000"),
                BankAccountModel("2", "Wallet Account", "097 1206 897", "USD", "100.00"),
                BankAccountModel("3", "Payroll Account", "0001-09987878-11", "USD", "100,000"),
                BankAccountModel("4", "Saving Account", "0001-09987878-12", "USD", "100.00"),
                BankAccountModel("5", "Saving Account", "0001-09987878-13", "KHR", "100.00")
            )
        } else {
            arrayListOf(
                BankAccountModel("1", "Wallet Account", "097 1206 897", "KHR", "100,000"),
                BankAccountModel("2", "Wallet Account", "097 1206 897", "USD", "100.00")
            )
        }

    LazyColumn {
        items(mList) {
            Column {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            shape = RoundedCornerShape(0.dp),
                            ambientColor = Primary,
                            spotColor = Primary,
                            elevation = 10.dp
                        ),
                    shape = RoundedCornerShape(0.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = White
                    )
                ) {
                    Row (
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxSize()
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_currency_exchange),
                            contentDescription = "android image",
                            tint = Primary,
                            modifier = Modifier
                                .size(30.dp)
                                .align(Alignment.CenterVertically)
                                .padding(start = 5.dp)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(weight = 1f, fill = false)
                                .padding(horizontal = 10.dp, vertical = 5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column (
                                modifier = Modifier
                                    .weight(weight = 1f, fill = false)
                                    .padding(horizontal = 5.dp)
                            ){
                                Text(
                                    modifier = Modifier
                                        .wrapContentSize(),
                                    text = it.numberPhone,
                                    fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                                    color = Black,
                                    maxLines = 1,
                                )

                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = "Available balance",
                                    maxLines = 1,
                                    fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                                    color = Gray,
                                    style = TextStyle(fontSize = 14.sp)
                                )
                            }

                            Column (
                                modifier = Modifier
                                    .weight(weight = 1f, fill = false)
                                    .padding(horizontal = 5.dp)
                            ){
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = it.accountName,
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
                                    text = "${it.price} ${it.currency}",
                                    maxLines = 1,
                                    fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                                    color = SecondYellow,
                                    style = TextStyle(fontSize = 14.sp)
                                )
                            }
                        }

                        Icon(
                            painter = painterResource(id = R.drawable.baseline_more_horiz_24),
                            contentDescription = "android image",
                            tint = Gray,
                            modifier = Modifier
                                .size(30.dp)
                                .align(Alignment.Top)
                                .padding(start = 5.dp)
                        )
                    }
                }

                BottomShadow(height = 5.dp)
            }
        }

        item {
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}

data class TabItem(
    val title: String,
    val unselectedIcon: Painter,
    val selectedIcon: Painter
)

@Composable
fun SimpleTabLayout() {
    val titles = listOf("Tab 1", "Tab 2", "Tab 3")
    var tabIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TabRow(selectedTabIndex = tabIndex) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Selected page: ${titles[tabIndex]}")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    AceledaComposeUITheme {
        AccountBody()
    }
}