package com.example.aceledacomposeui.ui.screen_activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Switch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aceledacomposeui.R
import com.example.aceledacomposeui.model.MobilePhonesData
import com.example.aceledacomposeui.ui.theme.AceledaComposeUITheme
import com.example.aceledacomposeui.ui.theme.Black
import com.example.aceledacomposeui.ui.theme.Gray
import com.example.aceledacomposeui.ui.theme.Primary
import com.example.aceledacomposeui.ui.theme.SecondYellow
import com.example.aceledacomposeui.ui.theme.White
import com.example.aceledacomposeui.ui.theme.Yellow
import com.example.aceledacomposeui.ui.widget.DottedShape
import com.example.aceledacomposeui.ui.widget.ToolAppBar
import com.example.aceledacomposeui.ui.widget.dialog.InputDialogView
import com.example.aceledacomposeui.ui.widget.dialog.LoadingView
import com.example.aceledacomposeui.utils.Utils
import com.example.aceledacomposeui.utils.fileNameToDrawer
import com.example.aceledacomposeui.utils.hideKeyboard
class MobileTopUpActivity : ComponentActivity() {

    companion object {
        fun start(mContext: Context) {
            mContext.startActivity(Intent(mContext, MobileTopUpActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AceledaComposeUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopUpMainScreen(this)
                }
            }
        }
    }
}

@Composable
fun TopUpMainScreen(mActivity: Activity) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White),
    ) {
        ToolAppBar(
            mTitle = "Mobile Top Up",
            mOnClickNavigation = { mActivity.finish() }
        )

        TopUpContentScreen(mActivity)
    }

}

private fun checkLogo(mList: List<MobilePhonesData>, amountInput: String) : MobilePhonesData ? {
    mList.forEach {
        if (amountInput.isNotEmpty()) {
            if (amountInput.length == 3 && amountInput == it.prefix) {
                return it
            } else if (amountInput.length > 3 && amountInput.take(3) == it.prefix) {
                return it
            }
        }
    }
    return null
}

@Composable
fun TopUpContentScreen(mActivity: Activity ?= null) {
    var amountInput by remember { mutableStateOf("") }
    var favoriteInputInput by remember { mutableStateOf("") }

    var digit by remember { mutableIntStateOf(10) }
    var checked by remember { mutableStateOf(false) }

    val height = (LocalConfiguration.current.screenHeightDp / 4).dp
    val prefix = 3

    var mLogo by remember { mutableIntStateOf(R.drawable.baseline_wifi_24) }
    val mList : List<MobilePhonesData>  = Utils.getMobileList(mActivity!!)

    val mData = checkLogo(mList, amountInput)
    if (mData != null) {
        mLogo = mActivity.fileNameToDrawer(mData.logo)
        digit = mData.digit
    } else {
        mLogo = R.drawable.baseline_wifi_24
    }

    var showLoadingDialog by remember {
        mutableStateOf(false)
    }

    var showAccountDialog by remember {
        mutableStateOf(false)
    }

    var mNumberPhone by remember { mutableStateOf("097 1206 897") }

    var mCurrency by remember { mutableStateOf("100,000 KHR") }


    Column (
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween,
    ){

        LazyColumn(
            modifier = Modifier.weight(1f)
        ){
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height),
                    backgroundColor = White,
                    elevation = 0.dp,
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
                                    .size(60.dp),
                                painter = painterResource(id = R.drawable.ic_mobile_topup),
                                contentDescription = "Localized description",
                            )
                        }

                        Column (
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 10.dp, horizontal = 5.dp)
                        ){
                            androidx.compose.material.Text(
                                text = "PINLESS",
                                color = Black,
                                style = TextStyle(fontSize = 18.sp, color = White, fontWeight = FontWeight.Normal),
                                textAlign = TextAlign.Center,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }

            item {
                Column (
                    modifier = Modifier
                        .padding(10.dp)
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .border(1.dp, color = Gray, shape = RoundedCornerShape(10.dp))
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .clickable {
                                showAccountDialog = !showAccountDialog  // First false we assing not false is true to show dialog
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
                                    text = mCurrency,
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

                    EditTextEnterNumber(
                        isNumber = true,
                        label = "Mobile number",
                        mLogo = mLogo,
                        value = amountInput,
                        amountInput = {
                            if (it.startsWith("0", true) && it.length <= digit) {
                                amountInput = it

                                if(it.length == digit) {
                                    mActivity.hideKeyboard()
                                }
                            } else if (it.length <= prefix && !it.startsWith("0", true)){
                                amountInput = it
                            }
                        }
                    )

                    Column (
                        Modifier.padding(horizontal = 10.dp, vertical = 20.dp)
                    ) {
                        Box(
                            Modifier
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(Color.Gray, shape = DottedShape(step = 10.dp))
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row (
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(weight = 1f, fill = false)
                        ){
                            Icon(
                                painter = painterResource(id = R.drawable.ic_favorite),
                                contentDescription = "contentDescription",
                                tint = Black,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(horizontal = 5.dp)
                                    .align(Alignment.CenterVertically))

                            Text(
                                modifier = Modifier
                                    .align(Alignment.Top)
                                    .wrapContentSize(),
                                text = "Add to Favorite",
                                fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                                color = Black,
                            )
                        }

                        Switch(
                            checked = checked,
                            onCheckedChange = {
                                checked = it
                            }
                        )
                    }

                    if (checked){
                        EditTextEnterNumber(
                            label = "Favorite Name",
                            mLogo = R.drawable.ic_favorite,
                            value = favoriteInputInput,
                            amountInput = {
                                favoriteInputInput = it
                            }
                        )
                    }
                }
            }

        }

        Box (
            modifier = Modifier
                .background(color = Primary)
                .clickable {
                    showLoadingDialog =
                        !showLoadingDialog  // First false we assing not false is true to show dialog
                }
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Primary)
            ){
                Text(
                    text = "OK",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize()
                        .padding(15.dp),
                    color = SecondYellow
                )
            }
        }
    }

    // Show Alert Dialog
    if (showLoadingDialog) {
        LoadingView {
            showLoadingDialog = !showLoadingDialog
        }
    }

    if (showAccountDialog) {
        InputDialogView {
            showAccountDialog = !showAccountDialog

            if(it != null) {
                mNumberPhone = it.numberPhone
                mCurrency = "${it.price} ${it.currency}"
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextEnterNumber(
    isNumber : Boolean = false,
    label : String,
    mLogo: Int,
    value: String,
    amountInput: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        // placeholder = { Text("Enter mobile number") },
        label = { Text(label) },
        onValueChange = amountInput,
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .padding(horizontal = 5.dp, vertical = 5.dp),
        keyboardOptions = KeyboardOptions(keyboardType = if (isNumber) KeyboardType.Number else  KeyboardType.Text),
        shape = RoundedCornerShape(8.dp),
        leadingIcon = {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .background(Color.Transparent, shape = CircleShape)
            ){
                Image(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(30.dp),
                    painter = painterResource(id = mLogo),
                    contentDescription = "Localized description",
                )
            }
        },
        trailingIcon = {
            Image(
                modifier = Modifier
                    .size(30.dp),
                painter = painterResource(id = R.drawable.ic_account_card),
                contentDescription = "Localized description",
                colorFilter = ColorFilter.tint(color = SecondYellow)
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            unfocusedBorderColor = Gray)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextEnterNumber(
    label : String,
    mLogo: Int,
    value: String,
    amountInput: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        // placeholder = { Text("Enter mobile number") },
        label = { Text(label) },
        onValueChange = amountInput,
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .padding(horizontal = 5.dp, vertical = 5.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        shape = RoundedCornerShape(8.dp),
        leadingIcon = {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .background(Color.Transparent, shape = CircleShape)
            ){
                Image(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(30.dp),
                    painter = painterResource(id = mLogo),
                    contentDescription = "Localized description",
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            unfocusedBorderColor = Gray)
    )
}

@Preview(showBackground = true)
@Composable
fun MobileTopUpPreview() {
    AceledaComposeUITheme {
        TopUpContentScreen()
    }
}