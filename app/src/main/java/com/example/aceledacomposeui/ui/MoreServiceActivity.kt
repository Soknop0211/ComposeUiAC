package com.example.aceledacomposeui.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aceledacomposeui.R
import com.example.aceledacomposeui.data.PreferenceManager
import com.example.aceledacomposeui.ui.theme.AceledaComposeUITheme
import com.example.aceledacomposeui.ui.theme.Primary
import com.example.aceledacomposeui.ui.theme.SecondYellow
import com.example.aceledacomposeui.ui.theme.White
import com.example.aceledacomposeui.ui.widget.CustomToolbar
import com.example.aceledacomposeui.utils.Constants
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aceledacomposeui.ui.theme.AceledaSplashLogo
import com.example.aceledacomposeui.ui.theme.AcledaAppLogo
import com.example.aceledacomposeui.ui.theme.Black
import com.example.aceledacomposeui.ui.theme.Gray
import com.example.aceledacomposeui.ui.theme.LightGray
import com.example.aceledacomposeui.ui.theme.SecondPrimary
import com.example.aceledacomposeui.ui.ui.IndeterminateCircularIndicator
import com.example.aceledacomposeui.ui.widget.ToolAppBar
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class MoreServiceActivity : ComponentActivity() {

    companion object {
        fun start(mContext: Context) {
            mContext.startActivity(Intent(mContext, MoreServiceActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AceledaComposeUITheme (
                darkTheme = PreferenceManager.getBoolean(Constants.ThemeMode, this)
            ){
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // InitScreen(this)
                }
            }
        }
    }
}

@Composable
fun InitScreen(
    navController : NavController,
    mActivity : Activity?= null
) {
    val listHeader = arrayListOf(
        "Top Promotions",
        "Center Promotions",
        "Last Promotions",
        "Near Promotions",
        "Foot Promotions"
    )

    val list = (1..5).map { it.toString() }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightGray),
    ){
        if (mActivity == null) {
            ToolAppBar(
                mTitle = "Recommendation",
                mOnClickNavigation = { navController.navigateUp() }
            )
        } else {
            CustomToolbar(
                mActivity = mActivity,
                mTitle = "Recommended",
            )
        }

        var mIsLoading by remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            delay(3.seconds)  // the delay of 3 seconds
            mIsLoading = false
        }

        val listState = rememberLazyListState()

        Box {
            LazyColumn (
                modifier = Modifier
                    .fillMaxSize(),
                state = listState
            ){
                listHeader.forEachIndexed { index, it ->
                    item {
                        Row(
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxWidth(),
                        ) {
                            Text(
                                text = it,
                                style = TextStyle(fontSize = 16.sp),
                                textAlign = TextAlign.Start,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 5.dp, top = 5.dp, bottom = 10.dp)
                            )
                        }
                    }

                    item {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth(),
                        ) {
                            item {
                                list.forEach { _ ->
                                    RowItem(index, listHeader)
                                }
                            }
                        }
                    }
                }
            }

            if(mIsLoading) {
                IndeterminateCircularIndicator()
            }
        }

    }

}

@Composable
fun RowItem(index: Int = 0, itemList : ArrayList<String> = arrayListOf()) {
    val wd = LocalConfiguration.current.screenWidthDp
    val midWd = if (index == 0 || index == itemList.size - 1)  wd.dp else  ((wd / 2) + (wd / 4) ).dp

    Card(
        modifier = Modifier
            .width(midWd)
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
        val drawableImg =
            when (index) {
                0 -> R.drawable.img_interest
                itemList.size - 1 -> R.drawable.img_loan
                else -> R.drawable.img_qr_code
            }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = White)
                .wrapContentHeight()
        ) {
            Image(
                contentScale = ContentScale.FillBounds,
                painter = painterResource(id = drawableImg),
                contentDescription = "android image",
                modifier = Modifier
                    .fillMaxSize()
                    .height(150.dp)
            )
            Row(
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 10.dp)
            ) {
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
                        text = "New Promotion",
                        maxLines = 1,
                        color = Black,
                        fontSize = 16.sp
                    )

                    Text(
                        text = "Good Promotion",
                        maxLines = 1,
                        color = Gray,
                        fontSize = 13.sp
                    )
                }

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AceledaComposeUITheme {
        RowItem()
    }
}