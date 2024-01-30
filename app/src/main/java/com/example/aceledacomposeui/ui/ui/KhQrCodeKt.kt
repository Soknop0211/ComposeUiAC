package com.example.aceledacomposeui.ui.ui

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.applyCanvas
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aceledacomposeui.R
import com.example.aceledacomposeui.ui.theme.AceledaComposeUITheme
import com.example.aceledacomposeui.ui.theme.AceledaSplashLogo
import com.example.aceledacomposeui.ui.theme.Black
import com.example.aceledacomposeui.ui.theme.Gray
import com.example.aceledacomposeui.ui.theme.GrayBg
import com.example.aceledacomposeui.ui.theme.LightGray
import com.example.aceledacomposeui.ui.theme.Primary
import com.example.aceledacomposeui.ui.theme.Red
import com.example.aceledacomposeui.ui.theme.SecondYellow
import com.example.aceledacomposeui.ui.theme.White
import com.example.aceledacomposeui.ui.theme.Yellow
import com.example.aceledacomposeui.ui.theme.dp10
import com.example.aceledacomposeui.ui.theme.fontMedium
import com.example.aceledacomposeui.ui.theme.sp18
import com.example.aceledacomposeui.ui.theme.topBarBrush
import com.example.aceledacomposeui.ui.widget.DottedShape
import com.example.aceledacomposeui.ui.widget.dialog.InputDialogView
import com.example.aceledacomposeui.utils.Utils
import com.example.aceledacomposeui.utils.convertUriToBitmap
import com.example.aceledacomposeui.utils.insertText
import com.example.aceledacomposeui.utils.removeText
import com.example.aceledacomposeui.utils.shareLink
import com.example.aceledacomposeui.utils.toast
import kotlinx.coroutines.delay
import kotlin.math.roundToInt
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KhQrCodeKt(navController : NavController) {

    var mIsLoading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(1.5.seconds)
        mIsLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.background(topBarBrush),
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                title = {
                    Text(
                        text = "My QKQR",
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = LightGray),
            contentAlignment = Alignment.Center
        ){
            KhQrCodeBody {
                mIsLoading = true
            }

        }
    }
}

@Composable
private fun KhQrCodeBody(modifier : Modifier = Modifier,
                         context: Context = LocalContext.current,
                         mUnitCallBack : () -> Unit
) {
    var showAccountDialog by remember {
        mutableStateOf(false)
    }

    var mNumberPhone by remember { mutableStateOf("097 1206 897") }

    var mAmount by remember { mutableStateOf("100,000 KHR") }

    var mAmountPay by remember { mutableStateOf("0") }

    var mCurrency by remember { mutableStateOf("KHR") }

    var isInitBottomSheet by remember { mutableStateOf(false) }

    // Save Image
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                Utils.saveImageToGallery(context, it.convertUriToBitmap(context))
            }
        }
    )

    // Take ScreenShot
    var capturingViewBounds by remember { mutableStateOf<Rect?>(null) }

    val view = LocalView.current

    // Permissions
    val readWriteFilePermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {isGranted ->
            if (
                isGranted[Manifest.permission.READ_EXTERNAL_STORAGE] == true
                && isGranted[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true
            )  {
                // Take Screen Shot
                val mBitmap = mBitmap(capturingViewBounds, view)

                if (mBitmap != null) {
                    // Utils.saveImageToGallery(context, mBitmap)

                    mUnitCallBack()

                    Utils.saveBitmapImage(context, mBitmap)
                }
            } else {
                context.toast("PERMISSION NOT GRANTED")
            }
        }
    )
    val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
    )

    Column (
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween,
    ){
        Card(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .wrapContentHeight()
                .onGloballyPositioned {
                    capturingViewBounds = it.boundsInRoot()
                }
                .padding(vertical = 10.dp, horizontal = 15.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = White
            )
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth(),
            ) {
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Red)
                        .clip(shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)),
                ){
                    Image(
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.Center)
                            .size(width = 100.dp, height = 45.dp),
                        painter = painterResource(id = R.drawable.kh_qrcode_logo),
                        contentDescription = "Localized description",
                    )
                }

                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.End),
                    contentAlignment = Alignment.CenterEnd
                ){
                    Image(
                        modifier = Modifier
                            .rotate(-90f)
                            .size(40.dp),
                        painter = painterResource(id = R.drawable.ic_triangle_base_24),
                        contentDescription = "Localized description",
                    )
                }

                Column (
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                ){
                    Text(
                        text = "SOK NOP",
                        maxLines = 1,
                        color = Black,
                        fontFamily = fontMedium,
                        fontSize = 16.sp
                    )

                    Text(
                        modifier = modifier
                            .padding(vertical = 5.dp),
                        text = "$mAmountPay $mCurrency",
                        maxLines = 1,
                        fontFamily = fontMedium,
                        color = Black,
                        fontSize = 16.sp
                    )

                    Box(
                        Modifier
                            .padding(vertical = 10.dp)
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Color.Gray, shape = DottedShape(step = 10.dp))
                    )

                    Box (
                        modifier = Modifier
                            .padding(10.dp)
                            .wrapContentSize(),
                    ){
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height((LocalConfiguration.current.screenHeightDp / 3.5).dp),
                            painter = painterResource(id = R.drawable.kh_qrcode),
                            contentDescription = "Localized description",
                        )
                    }

                    Spacer(modifier = Modifier.padding(20.dp))
                }

            }

        }

        Column {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .border(1.dp, color = Primary, shape = RoundedCornerShape(10.dp))
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        showAccountDialog =
                            !showAccountDialog  // First false we assign not false is true to show dialog
                    },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .padding(end = 10.dp)
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
                            text = mAmount,
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

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column (
                    modifier = Modifier
                        .wrapContentSize()
                        .weight(1f, false)
                        .padding(top = 5.dp, bottom = 20.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            // launcher.launch("image/*")

                            readWriteFilePermissionResultLauncher.launch(
                                permissions
                            )
                        }
                ){
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "contentDescription",
                        tint = White,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(horizontal = 5.dp)
                            .background(color = Primary, shape = CircleShape)
                            .shadow(
                                shape = CircleShape,
                                elevation = 8.dp,
                                ambientColor = Black,
                                spotColor = Yellow
                            )
                    )

                    Column (
                        modifier = Modifier
                            .padding(5.dp)
                    ){
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = "Save",
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                            color = Black,
                            maxLines = 1,
                            textAlign = TextAlign.Center,
                        )
                    }
                }

                Column (
                    modifier = Modifier
                        .wrapContentSize()
                        .weight(1f, false)
                        .padding(top = 5.dp, bottom = 20.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            isInitBottomSheet = !isInitBottomSheet
                        }
                ){
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "contentDescription",
                        tint = White,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(horizontal = 5.dp)
                            .background(color = Primary, shape = CircleShape)
                            .shadow(
                                shape = CircleShape,
                                elevation = 8.dp,
                                ambientColor = Black,
                                spotColor = Yellow
                            )
                    )

                    Column (
                        modifier = Modifier
                            .padding(5.dp)
                    ){
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = "Set Amount",
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                            color = Black,
                            maxLines = 1,
                            textAlign = TextAlign.Center,
                        )
                    }
                }

                Column (
                    modifier = Modifier
                        .wrapContentSize()
                        .weight(1f, false)
                        .padding(top = 5.dp, bottom = 20.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            context.shareLink()
                        }
                ){
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "contentDescription",
                        tint = White,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(horizontal = 5.dp)
                            .background(color = Primary, shape = CircleShape)
                            .shadow(
                                shape = CircleShape,
                                elevation = 8.dp,
                                ambientColor = Black,
                                spotColor = Yellow
                            )
                    )

                    Column (
                        modifier = Modifier
                            .padding(5.dp)
                    ){
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = "Share",
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                            color = Black,
                            maxLines = 1,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }

        }

    }


    // Alert Dialog
    if (showAccountDialog) {
        InputDialogView {
            showAccountDialog = !showAccountDialog

            if(it != null) {
                mNumberPhone = it.numberPhone
                mAmount = "${it.price} ${it.currency}"
                mCurrency = it.currency
            }
        }
    }

    // Init BottomSheet
    AnimatedVisibility(
        visible = isInitBottomSheet,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        InitBottomSheet (
            mCurrency
        ){
            isInitBottomSheet = false
            if (!TextUtils.isEmpty(it)){
                mAmountPay = it
            }
        }
    }

}

private fun mBitmap(bounds : Rect?, view : View) : Bitmap? {
    if (bounds != null) {
        val bitmap = Bitmap.createBitmap(
            bounds.width.roundToInt(), bounds.height.roundToInt(),
            Bitmap.Config.ARGB_8888
        ).applyCanvas {
            translate(-bounds.left, -bounds.top)
            view.draw(this)
        }
        return bitmap
    }

    return null
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InitBottomSheet(mCurrency : String, onDismiss: (String) -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        onDismissRequest = { onDismiss("") },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        content = {
            MainItemBottomSheet (
                mCurrency
            ){
                onDismiss(it)
            }
        }
    )
}

@Composable
private fun MainItemBottomSheet(mCurrency : String, onDismiss: (String) -> Unit) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    val cardShape = RoundedCornerShape(dp10)

    var isEnableBtn by remember { mutableStateOf(false) }

    LaunchedEffect(textFieldValue) {
        isEnableBtn = textFieldValue.text.isNotEmpty()
    }

    Column (
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ){
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Please enter amount for receive",
            overflow = TextOverflow.Ellipsis,
            fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
            color = Primary,
            maxLines = 1,
            fontSize = sp18,
            textAlign = TextAlign.Center,
        )

        Row (
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 20.dp)
                .align(Alignment.CenterHorizontally)
        ){
            Text(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .align(Alignment.CenterVertically)
                    .wrapContentSize(),
                text = mCurrency,
                overflow = TextOverflow.Ellipsis,
                fontFamily = FontFamily(Font(R.font.montserrat_medium_body)),
                color = Gray,
                maxLines = 1,
                textAlign = TextAlign.Center,
            )

            EditTextEnterKeyBoardNumber(
                label = "Amount",
                value = textFieldValue,
                amountInput = {
                    textFieldValue = it
                },
                modifier = Modifier.wrapContentSize()
            )
        }

        val mColor = animateColorAsState(
            targetValue = if (isEnableBtn)   Primary else Gray, label = "",
            animationSpec = tween(300, 0, LinearEasing)
        )

        Column {
            Column (
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .background(color = mColor.value, shape = cardShape)
                    .shadow(
                        shape = cardShape,
                        ambientColor = mColor.value,
                        spotColor = mColor.value,
                        elevation = 10.dp
                    )
                    .clickable(
                        enabled = isEnableBtn,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(color = Primary),
                    ) {
                        onDismiss(textFieldValue.text)
                    }
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

            Box (
                modifier = Modifier.padding(top = 10.dp)
            ){
                KeyBoardKt(onClick = {
                    var display: TextFieldValue = textFieldValue
                    if (it.equals("x", ignoreCase = true)) {
                        if (!android.text.TextUtils.isEmpty(display.text)) {
                            display = display.removeText()
                            textFieldValue = display
                        }
                    } else {
                        if (it == "." && display.text.contains(".")) {

                        } else {
                            display = display.insertText(it)
                        }
                        textFieldValue = display
                    }
                })
            }

            Box(
                Modifier
                    .height(50.dp)
                    .background(color = GrayBg))

        }

    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalComposeUiApi::class)
@Composable
fun EditTextEnterKeyBoardNumber(
    label: String,
    value: TextFieldValue,
    amountInput: (TextFieldValue) -> Unit,
    modifier : Modifier
) {
    val mKeyBoard = LocalSoftwareKeyboardController.current

    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
        .also { interactionSource ->
            LaunchedEffect(interactionSource) {
                interactionSource.interactions.collect {
                    if (it is PressInteraction.Release) {
                        mKeyBoard?.hide()
                    }
                }
            }
        }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        mKeyBoard?.hide()
    }

    TextField(
        singleLine = true,
        interactionSource = interactionSource,
        value = value,
        placeholder = { Text(label) },
        onValueChange = {
            amountInput.invoke(it)
        },
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusEvent {
                mKeyBoard?.hide()
            }
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .padding(horizontal = 5.dp, vertical = 5.dp),
        keyboardOptions = KeyboardOptions(
            imeAction =  ImeAction.Done,
            keyboardType = KeyboardType.Number,
        ),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}

@Preview(showBackground = true)
@Composable
fun KhQrCodePreview() {
    AceledaComposeUITheme {
        KhQrCodeKt(navController = rememberNavController())

        // MainItemBottomSheet {}
    }
}