package com.example.aceledacomposeui.ui.ui

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.aceledacomposeui.R
import com.example.aceledacomposeui.model.NotificationModel
import com.example.aceledacomposeui.ui.theme.AceledaComposeUITheme
import com.example.aceledacomposeui.ui.theme.AcledaAppLogo
import com.example.aceledacomposeui.ui.theme.Black
import com.example.aceledacomposeui.ui.theme.LightGray
import com.example.aceledacomposeui.ui.theme.Primary
import com.example.aceledacomposeui.ui.theme.White
import com.example.aceledacomposeui.ui.theme.Yellow
import com.example.aceledacomposeui.ui.widget.ToolAppBar

@Composable
fun ProfileKt(
    navController : NavController
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightGray),
    ){
        ToolAppBar(
            mTitle = "Me",
            mOnClickNavigation = { navController.navigateUp() }
        )

        val listState = rememberLazyListState()
        val width = LocalConfiguration.current.screenWidthDp

        Column (
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween,
        ){
            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                state = listState
            ){
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((width / 3).dp)
                            .background(color = Primary)
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 10.dp)
                    ) {

                        val context = LocalContext.current
                        var bitmap by remember {
                            mutableStateOf<Bitmap?>(null)
                        }

                        val cameraLauncher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.TakePicturePreview(),
                            onResult = { newImage ->
                                bitmap = newImage
                            }
                        )

                        val permissionLauncher = rememberLauncherForActivityResult(
                            ActivityResultContracts.RequestPermission()
                        ) { isGranted ->
                            if (isGranted) {
                                cameraLauncher.launch()
                            }
                        }

                        if (bitmap == null) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_my_profile),
                                contentDescription = "Localized description",
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .clip(CircleShape)
                                    .size(80.dp)
                                    .clickable {
                                        // Checks if the permission is granted
                                        val permissionCheckResult =
                                            ContextCompat.checkSelfPermission(
                                                context,
                                                Manifest.permission.CAMERA
                                            )

                                        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                            // The permission is already granted
                                            cameraLauncher.launch()
                                        } else {
                                            // Launches the permission request
                                            permissionLauncher.launch(Manifest.permission.CAMERA)
                                        }
                                    },
                            )
                        } else {
                            Image(
                                bitmap = bitmap!!.asImageBitmap(),
                                contentDescription = "Localized description",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .clip(CircleShape)
                                    .size(80.dp)
                                    .clickable {
                                        // Checks if the permission is granted
                                        val permissionCheckResult =
                                            ContextCompat.checkSelfPermission(
                                                context,
                                                Manifest.permission.CAMERA
                                            )

                                        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                            // The permission is already granted
                                            cameraLauncher.launch()
                                        } else {
                                            // Launches the permission request
                                            permissionLauncher.launch(Manifest.permission.CAMERA)
                                        }
                                    },
                            )
                        }
                    }
                }

                item {
                    Text(
                        text = "Information",
                        textAlign = TextAlign.Start,
                        fontSize = 24.sp,
                        color = Primary,
                        modifier = Modifier
                            .padding(10.dp)
                    )
                }

                item {
                    ProfileKtItem(mTitle = "Mr. Nop", mImageVector = Icons.Outlined.Person)

                    ProfileKtItem(mTitle = "086 790 170", mImageVector = Icons.Outlined.Phone)

                    ProfileKtItem(mTitle = "nop10@gmail.com", mImageVector = Icons.Outlined.Email)

                    ProfileKtItem(mTitle = "01 Jan 2000", mImageVector = Icons.Outlined.DateRange)

                    ProfileKtItem(mTitle = "Phnom Penh", mImageVector = Icons.Outlined.LocationOn)

                }

                item {
                    Text(
                        text = "Setting",
                        color = Primary,
                        textAlign = TextAlign.Start,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(10.dp)
                    )
                }

                item {
                    ProfileKtItem(mTitle = "Change PIN", mImageVector = Icons.Outlined.Lock)

                    ProfileKtItem(mTitle = "Log out", mImageVector = Icons.Outlined.ExitToApp)
                }


            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize()
                    .padding(5.dp),
            ) {
                Text(
                    text = "Version 1.0",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize()
                        .padding(10.dp),
                    color = Primary
                )
            }
        }

    }
}

@Composable
fun ProfileKtItem(
    modifier: Modifier = Modifier,
    mTitle : String,
    mImageVector : ImageVector
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clickable {},

        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = White
        )
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .padding(horizontal = 10.dp, vertical = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Right arrow icon
            Row (
                modifier = Modifier
                    .wrapContentWidth()
            ){
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 5.dp),
                    imageVector = mImageVector,
                    contentDescription = "contentDescription"
                )

                Text(
                    text = mTitle,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(start = 5.dp, end = 5.dp)
                )
            }
            // Right arrow icon
            Icon(
                modifier = Modifier
                    .padding(end = 5.dp)
                    .weight(weight = 1f, fill = false),
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = "contentDescription"
            )
        }

        /*Row (
            modifier = modifier
                .padding(bottom = 5.dp)
        ){
            Icon(
                modifier = modifier
                    .size(30.dp),
                imageVector = Icons.Filled.Person,
                tint = Black,
                contentDescription = "Localized description"
            )

            Text(
                text = "User Name",
                maxLines = 1,
                color = Primary,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

            Icon(
                modifier = Modifier
                    .size(30.dp),
                imageVector = Icons.Filled.KeyboardArrowRight,
                tint = Black,
                contentDescription = "Localized description"
            )

        }*/
    }
}

@Composable
fun OnTackCamera() {

    var bitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { newImage ->
            bitmap = newImage
        }
    )

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            cameraLauncher.launch()
        }
    }

    Column {
        val context = LocalContext.current

        TextButton(
            onClick = {
                // Checks if the permission is granted
                val permissionCheckResult =
                    ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                    // The permission is already granted
                    cameraLauncher.launch()
                } else {
                    // Launches the permission request
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }
        ) {
            Text(
                text = "Use camera"
            )
        }
    }

    bitmap?.let {
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .size(150.dp)
        )
    }

}

@Composable
fun OnPickUpImageOnlyOne() {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                imageUri = it
            }
        }
    )

    Column {
        imageUri?.let {
            Image(
                painter = rememberAsyncImagePainter(model = imageUri),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .size(100.dp)
            )
        }

        TextButton(
            onClick = {
                galleryLauncher.launch("image/*")
            }
        ) {
            Text(
                text = "Pick image"
            )
        }
    }
}


@Preview(showBackground = true)
@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_UNDEFINED, showBackground = true)
@Preview(name = "Full Preview", showSystemUi = true)
@Composable
fun ProfilePreview() {
    AceledaComposeUITheme {
        ProfileKt(rememberNavController())
    }
}