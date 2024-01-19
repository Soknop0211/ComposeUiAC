package com.example.aceledacomposeui.ui.ui

import android.text.TextUtils
import android.util.Log
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aceledacomposeui.R
import com.example.aceledacomposeui.ui.theme.AceledaComposeUITheme
import com.example.aceledacomposeui.ui.theme.Black
import com.example.aceledacomposeui.ui.theme.Gray
import com.example.aceledacomposeui.ui.theme.LightGray
import com.example.aceledacomposeui.ui.theme.Primary
import com.example.aceledacomposeui.ui.theme.White

@Composable
fun KeyBoardKt(onClick: (String) -> Unit, mDensity: Density = LocalDensity.current) {
    val mList = arrayOf(
        "1", "2", "3", "4", "5",
        "6", "7", "8", "9", ".",
        "0", "X"
    )
    var columnHeightDp by remember {
        mutableStateOf(350.dp)
    }

    var lineHeightDp by remember {
        mutableStateOf(50.dp)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = LightGray)
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .height((columnHeightDp))
                .fillMaxWidth(),
            columns = GridCells.Fixed(3),
            content = {
                itemsIndexed(items = mList) { index, _ ->
                    val mIsNotNumber = mList[index] == "·" || mList[index] == "X"
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .onGloballyPositioned { coordinates ->
                                lineHeightDp =
                                    with(mDensity) { (coordinates.size.height.toDp() + 20.dp) }

                                columnHeightDp = lineHeightDp * (mList.size / 3)
                            }
                            .clickable {
                                onClick.invoke(mList[index])
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = if(mIsNotNumber) Color.Transparent else White,
                        ),
                        elevation = CardDefaults.cardElevation(if(mIsNotNumber) 0.dp else 5.dp),
                        shape = RoundedCornerShape(10.dp),
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
                                    Text(
                                        text = mList[index],
                                        color = Black,
                                        maxLines = 1,
                                        fontWeight = FontWeight.Bold,
                                        style = TextStyle(fontSize = 13.sp),
                                        modifier = Modifier
                                            .padding(top = 10.dp, bottom = 5.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun KeyBoardBottomSheet(onDismiss: () -> Unit = {}) {
    val keyboard = LocalSoftwareKeyboardController.current
    keyboard?.show()

    val modalBottomSheetState = rememberModalBottomSheetState()
    var amountInput by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = { },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Column{
            EditTextEnterKeyBoardNumber2(
                label = "0.0",
                mLogo = R.drawable.ic_account_payment,
                value = amountInput,
                amountInput = {
                    amountInput = it
                }
            )

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            KeyBoardKt(onClick =  {
                var display: String = amountInput
                if (it.equals("x", ignoreCase = true)) {
                    if (!TextUtils.isEmpty(display)) {
                        display = display.substring(0, display.length - 1)
                        amountInput = display
                    }
                } else {
                    if (it == "." && display.contains(".")) {

                    } else {
                        display += it
                    }
                    amountInput = display
                }
            })

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun EditTextEnterKeyBoardNumber2(
    label : String,
    mLogo: Int,
    value: String,
    amountInput: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        // placeholder = { Text("Enter mobile number") },
        label = { Text(label) },
        onValueChange = amountInput,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .padding(horizontal = 5.dp, vertical = 5.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
            unfocusedBorderColor = Gray
        )
    )
}

@Preview(showBackground = true)
@Composable
fun KeyBoardKtPreview() {
    AceledaComposeUITheme {
        KeyBoardBottomSheet()
    }
}