package com.example.aceledacomposeui.ui.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.aceledacomposeui.R
import com.example.aceledacomposeui.model.NotificationModel
import com.example.aceledacomposeui.ui.theme.AceledaComposeUITheme
import com.example.aceledacomposeui.ui.theme.AcledaAppLogo
import com.example.aceledacomposeui.ui.theme.LightGray
import com.example.aceledacomposeui.ui.theme.Primary
import com.example.aceledacomposeui.ui.theme.White
import com.example.aceledacomposeui.ui.widget.ToolAppBar
import com.example.aceledacomposeui.view_model.NotificationViewModel
import kotlinx.coroutines.delay
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.draggedItem
import org.burnoutcrew.reorderable.move
import org.burnoutcrew.reorderable.rememberReorderState
import org.burnoutcrew.reorderable.reorderable
import kotlin.time.Duration.Companion.seconds

@Composable
fun ReOrderableList(){
    val state = rememberReorderState()
    val data = List(100) { "item $it" }.toMutableStateList()
    LazyColumn(
        state = state.listState,
        modifier = Modifier.reorderable(state, { from, to -> data.move(from.index, to.index) })
    ) {
        items(data, { it }) { item ->
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .draggedItem(state.offsetByKey(item))
                    .detectReorderAfterLongPress(state),
                shape = RoundedCornerShape(10.dp),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {
                Text(
                    text = item,
                    modifier = Modifier
                        .padding(20.dp)
                )
            }
        }
    }
}

@Composable
fun NotificationList() {
    val state = rememberReorderState()

    val mList = ArrayList<NotificationModel>()
    for (i in 1..20) {
        mList.add(NotificationModel(
            i.toString(),
            "KHQR Payment ACLDA Mobile",
            "05, Jan, 2024 11:30 AM",
            "You have made payment 100$ from Mr. Dara Bank by KHQR, on 05, Jan, 2024 11:30 AM. Thanks you for using our service ! You have made payment 100$ from Mr. Dara Bank by KHQR, on 05, Jan, 2024 11:30 AM. Thanks you for using our service !"
        ))
    }

    val mutableStateList = mList.toMutableStateList()

    LazyColumn (
        state = state.listState,
        modifier = Modifier
            .reorderable(state, { from, to -> mutableStateList.move(from.index, to.index) })
    ){
        items(mutableStateList, { it }) { item ->
            // CardNotificationItem(mItem = it)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 5.dp,
                        bottom = 5.dp,
                        end = 10.dp,
                        start = 10.dp
                    )
                    .draggedItem(state.offsetByKey(item))
                    .detectReorderAfterLongPress(state),
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
                                    text = item.name ?: "",
                                    maxLines = 1,
                                    color = Primary,
                                    fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                                    fontSize = 16.sp
                                )

                                Text(
                                    text = item.date ?: "",
                                    maxLines = 1,
                                    fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                                    color = Primary,
                                    fontSize = 11.sp
                                )
                            }

                        }

                        Text(
                            text = item.description ?: "",
                            maxLines = mShowLess,
                            overflow = TextOverflow.Ellipsis,
                            color = Primary,
                            fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                            fontSize = 14.sp,
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
                                fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .clickable {
                                        mShowLess = if (mShowLess > 2) {
                                            2
                                        } else {
                                            Int.MAX_VALUE
                                        }
                                    }
                            )
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun NotificationKt(
    mNotificationViewModel : NotificationViewModel = hiltViewModel(),
    navController : NavController
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightGray)
    ){
        ToolAppBar(
            mTitle = "Notification",
            mOnClickNavigation = { navController.navigateUp() }
        )

        var mIsLoading by remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            delay(3.seconds)  // the delay of 3 seconds
            mIsLoading = false

            // Request Api
            // viewModel.requestList()
        }

        Box {
            NotificationList()

            if(mIsLoading) {
                IndeterminateCircularIndicator()
            }
        }
    }

   /* Column (
       ,
    ){
        ToolAppBar(
            mTitle = "Notification",
            mOnClickNavigation = { navController.navigateUp() }
        )

        var mIsLoading by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            delay(3.seconds)  // the delay of 3 seconds
            mIsLoading = false

            // Request Api
            // viewModel.requestList()
        }

        val state = rememberReorderState()

        // State
        // val mList = viewModel.mNotificationList.observeAsState(listOf())

        val mList = ArrayList<NotificationModel>()
        for (i in 1..20) {
            mList.add(NotificationModel(
                i.toString(),
                "KHQR Payment ACLDA Mobile",
                "05, Jan, 2024 11:30 AM",
                "You have made payment 100$ from Mr. Dara Bank by KHQR, on 05, Jan, 2024 11:30 AM. Thanks you for using our service ! You have made payment 100$ from Mr. Dara Bank by KHQR, on 05, Jan, 2024 11:30 AM. Thanks you for using our service !"
            ))
        }

        val mutableStateList = mList.toMutableStateList()
        val data = List(100) { "item $it" }.toMutableStateList()

        Box {
            LazyColumn (
                state = state.listState,
                modifier = Modifier.reorderable(state, { from, to -> data.move(from.index, to.index) })
            ){
                items(data, { it }) { item ->
                    // CardNotificationItem(mItem = it)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 5.dp,
                                bottom = 5.dp,
                                end = 10.dp,
                                start = 10.dp
                            )
                            .draggedItem(state.offsetByKey(item))
                            .detectReorderAfterLongPress(state),
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
                                            text = "item.name" ?: "",
                                            maxLines = 1,
                                            color = Primary,
                                            fontSize = 16.sp
                                        )

                                        Text(
                                            text = "item.date" ?: "",
                                            maxLines = 1,
                                            color = Primary,
                                            fontSize = 11.sp
                                        )
                                    }

                                }

                                Text(
                                    text = "item.description" ?: "",
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
                                        modifier = Modifier
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
            }

            if(mIsLoading) {
                IndeterminateCircularIndicator()
            }
        }

    }*/
}

@Composable
fun CardNotificationItem(
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

@Preview(showBackground = true)
@Preview(name = "Light Mode")
/*@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_UNDEFINED, showBackground = true)*/
/*@Preview(name = "Full Preview", showSystemUi = true)*/
@Composable
fun NotificationPreview() {
    AceledaComposeUITheme {
        CardNotificationItem()
        // ReOrderableList()
    }
}