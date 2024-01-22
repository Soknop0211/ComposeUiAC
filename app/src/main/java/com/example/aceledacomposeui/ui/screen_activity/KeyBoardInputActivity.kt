package com.example.aceledacomposeui.ui.screen_activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import com.example.aceledacomposeui.R
import com.example.aceledacomposeui.lifecycle.ComposableLifecycle
import com.example.aceledacomposeui.ui.theme.AceledaComposeUITheme
import com.example.aceledacomposeui.ui.theme.Black
import com.example.aceledacomposeui.ui.theme.Gray
import com.example.aceledacomposeui.ui.theme.LightGray
import com.example.aceledacomposeui.ui.theme.Primary
import com.example.aceledacomposeui.ui.theme.SecondYellow
import com.example.aceledacomposeui.ui.theme.fontMedium
import com.example.aceledacomposeui.ui.ui.KeyBoardLineBgKt
import com.example.aceledacomposeui.ui.widget.DottedShape
import com.example.aceledacomposeui.ui.widget.ToolAppBar
import com.example.aceledacomposeui.utils.Constants.Dollar
import com.example.aceledacomposeui.utils.Constants.Khmer

class KeyBoardInputActivity : ComponentActivity() {

    companion object {
        fun start(mContext: Context) {
            mContext.startActivity(Intent(mContext, KeyBoardInputActivity::class.java))
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
                    KeyBoardMainScreen(this@KeyBoardInputActivity)
                }
            }
        }
    }
}

@Composable
fun KeyBoardMainScreen(mActivity: Activity) {
    Scaffold(
        topBar = {
            ToolAppBar(
                mTitle = "Pay Me",
                mOnClickNavigation = { mActivity.finish() }
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = LightGray)
        ){
            KeyBoardContentScreen()
        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun KeyBoardContentScreen() {
    var amountInput by remember { mutableStateOf("") }
    var remarkInput by remember { mutableStateOf("") }
    var purposeInput by remember { mutableStateOf("") }

    var currency by remember { mutableStateOf(Dollar) }

    var isShowKeyBoard by remember { mutableStateOf(false) }
    val keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current

    val focusManager = LocalFocusManager.current

    Column (
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween,
    ){

        LazyColumn(
            modifier = Modifier
                .weight(1f),
            state = rememberLazyListState()
        ) {
            item {
                Column (
                    modifier = Modifier
                        .padding(10.dp)
                ){
                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .clickable {
                                currency = if (currency.equals(Dollar, true)) Khmer else Dollar
                            }
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Top)
                                .wrapContentSize(),
                            text = "$currency 096 107 0770",
                            fontFamily = fontMedium,
                            color = Black
                        )

                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "contentDescription",
                            tint = Black,
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }

                    EditTextEnterKeyBoardNumber(
                        label = "Amount",
                        value = amountInput,
                        amountInput = {
                            amountInput = it
                        },
                        isShowKeyBoard = {
                            isShowKeyBoard = it
                        },
                        keyboardController = keyboardController!!
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
                                text = "Enter Remark",
                                fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                                color = Black,
                            )
                        }
                    }

                    EditTextEnterText(
                        label = "Remark",
                        value = remarkInput,
                        amountInput = {
                            remarkInput = it
                        }
                    )

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
                                text = "Enter Purpose",
                                fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                                color = Black,
                            )
                        }
                    }

                    EditTextEnterText(
                        label = "Purpose",
                        value = purposeInput,
                        amountInput = {
                            purposeInput = it
                        }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
            }
        }

        Column {
            Box (
                modifier = Modifier
                    .background(color = Primary)
                    .clickable { }
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

            AnimatedVisibility(
                visible = isShowKeyBoard,
                /*enter = expandVertically(
                    animationSpec = tween(1000),
                ),
                exit = shrinkVertically(
                    shrinkTowards = Alignment.CenterVertically,
                    animationSpec = tween(100),
                ),*/
            ) {
                Box(
                    modifier = Modifier
                        .animateContentSize(animationSpec = tween(1000))
                ) {
                    KeyBoardLineBgKt(onClick = {
                        var display: String = amountInput
                        if (it.equals("x", ignoreCase = true)) {
                            if (!android.text.TextUtils.isEmpty(display)) {
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

           /* AnimatedVisibility(
                visible = isShowKeyBoard,
               *//* enter = slideInVertically(
                    animationSpec = tween(4000),
                ),
                exit = shrinkVertically(
                    shrinkTowards = Alignment.CenterVertically
                ),*//*
                enter = slideInVertically(initialOffsetY = { -it }),
                exit = slideOutVertically(targetOffsetY = { -it }),
            ) {
                KeyBoardLineBgKt(onClick = {
                    var display: String = amountInput
                    if (it.equals("x", ignoreCase = true)) {
                        if (!android.text.TextUtils.isEmpty(display)) {
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
            }*/

        }

        // Life Cycle
        ComposableLifecycle { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                focusManager.clearFocus()
            }
            if (event == Lifecycle.Event.ON_PAUSE) {
                focusManager.clearFocus()
            }
        }

    }
}

enum class CursorSelectionBehaviour {
    START, END, SELECT_ALL
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun EditTextEnterKeyBoardNumber(
    label: String,
    value: String,
    amountInput: (String) -> Unit,
    isShowKeyBoard: (Boolean) -> Unit,
    keyboardController: SoftwareKeyboardController,
) {

    /*
    behaviour: CursorSelectionBehaviour = CursorSelectionBehaviour.END
    val direction = LocalLayoutDirection.current
    var tfv by remember {
        val selection = when (behaviour) {
            CursorSelectionBehaviour.START -> {
                if (direction == LayoutDirection.Ltr) TextRange.Zero else TextRange(value.length)
            }
            CursorSelectionBehaviour.END -> {
                if (direction == LayoutDirection.Ltr) TextRange(value.length) else TextRange.Zero
            }
            CursorSelectionBehaviour.SELECT_ALL -> TextRange(0, value.length)
        }
        val textFieldValue = TextFieldValue(text = value, selection = selection)
        mutableStateOf(textFieldValue)
    }*/

    var isShowLIneKeyBoard by remember { mutableStateOf(false) }


    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
        .also { interactionSource ->
            LaunchedEffect(interactionSource) {
                interactionSource.interactions.collect {
                    if (it is PressInteraction.Release) {
                        keyboardController.hide()
                    }
                }
            }
        }

    AnimatedVisibility(
        visible = isShowLIneKeyBoard
    ) {
        keyboardController.hide()
        isShowLIneKeyBoard = false
    }

    val textFieldValue = TextFieldValue(text = value, selection = TextRange(value.length)) //place cursor at the end of the text

    OutlinedTextField(
        singleLine = true,
        interactionSource = interactionSource,
        value = textFieldValue,
        label = { Text(label) },
        onValueChange = {
          amountInput.invoke(it.text)
        },
        modifier = Modifier
            .focusRequester(focusRequester)
            .onFocusEvent { focusState ->
                keyboardController.hide()
                if (focusState.isFocused) {
                    isShowKeyBoard.invoke(true)
                } else {
                    isShowKeyBoard.invoke(false)
                }
            }
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .padding(horizontal = 5.dp, vertical = 5.dp),
        keyboardOptions = KeyboardOptions(
            imeAction =  ImeAction.Done,
            keyboardType = KeyboardType.Number,
        ),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            unfocusedBorderColor = Gray
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextEnterText(
    label: String,
    value: String,
    amountInput: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        singleLine = true,
        value = value,
        label = { Text(label) },
        onValueChange = amountInput,
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .padding(horizontal = 5.dp, vertical = 5.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            unfocusedBorderColor = Gray
        )
    )
}

@Preview(showBackground = true)
@Composable
fun KeyBoardInputPreview() {
    AceledaComposeUITheme {
        KeyBoardContentScreen()
    }
}