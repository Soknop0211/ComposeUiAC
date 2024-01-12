package com.example.aceledacomposeui.ui.widget.dialog

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.aceledacomposeui.R
import com.example.aceledacomposeui.model.BankAccountModel
import com.example.aceledacomposeui.ui.screen_activity.TopUpContentScreen
import com.example.aceledacomposeui.ui.theme.AceledaComposeUITheme
import com.example.aceledacomposeui.ui.theme.AcledaAppLogo
import com.example.aceledacomposeui.ui.theme.Black
import com.example.aceledacomposeui.ui.theme.Gray
import com.example.aceledacomposeui.ui.theme.Primary
import com.example.aceledacomposeui.ui.theme.SecondYellow
import com.example.aceledacomposeui.ui.theme.White

@Preview(showBackground = true)
@Composable
fun InputDialogPreview() {
    AceledaComposeUITheme {
        // InputDialogView { }
    }
}
@Composable
fun InputDialogView(onDismiss:(BankAccountModel?) -> Unit) {
    val mList = arrayListOf(
        BankAccountModel("1", "Wallet Account", "097 1206 897", "KHR", "100,000"),
        BankAccountModel("2", "Wallet Account", "097 1206 897", "USD", "100.00")
    )

    Dialog(onDismissRequest = { onDismiss(null) }) {
        Card(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.padding(8.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation =  8.dp
            ),
        ) {
            Column(
                Modifier
                    .background(Color.White)
            ) {

                Text(
                    text = "Please select bank account:",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn {
                    items(mList) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = 5.dp,
                                    bottom = 5.dp,
                                    end = 10.dp,
                                    start = 10.dp
                                )
                                .clickable {
                                    onDismiss(it)
                                },
                            shape = RoundedCornerShape(10.dp),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 2.dp
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
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
                                        text = it.accountName,
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
                                        text = it.price,
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
                                        text = it.currency,
                                        maxLines = 1,
                                        fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                                        color = SecondYellow,
                                        style = TextStyle(fontSize = 14.sp)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun LoadingView(onDismiss:() -> Unit) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier,
            elevation = CardDefaults.cardElevation(
                defaultElevation =  8.dp
            ),
        ) {
            Column(
                Modifier
                    .background(White)
                    .padding(12.dp)
            ) {
                Text(
                    text = "Loading.. Please wait..",
                    Modifier
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )

                CircularProgressIndicator(
                    strokeWidth = 4.dp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp)
                )
            }
        }
    }

    val context = LocalContext.current
    val handler = Looper.myLooper()?.let { Handler(it) }
    handler?.postDelayed({ onDismiss() }, 3000)
}
