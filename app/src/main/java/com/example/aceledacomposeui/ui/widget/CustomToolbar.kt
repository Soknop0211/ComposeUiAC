package com.example.aceledacomposeui.ui.widget

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aceledacomposeui.R
import com.example.aceledacomposeui.ui.theme.AceledaSplashLogo
import com.example.aceledacomposeui.ui.theme.Primary
import com.example.aceledacomposeui.ui.theme.SecondPrimary
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar (mActivity : Activity? = null,
                   mTitle : String = "",
                   mOnClickNavigation : () -> Unit = {},
                   mIsArrowBack : Boolean = true,
                   mIsProfile : Boolean = false,
                   mIconProfile : Painter = painterResource(id = R.drawable.splash_screen_ac),
                   mOnClickProfileNavigation : () -> Unit = {})  {
    val verticalGradientBrush = Brush.verticalGradient(
        colors = listOf(
            SecondPrimary,
            Primary,
        )
    )
    return CenterAlignedTopAppBar(
        modifier = Modifier.background(verticalGradientBrush),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Transparent
        ),
        title = {
            Text(
                mTitle,
                color = colorResource(id = R.color.white),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )
        },
        navigationIcon = {
            if (mIsArrowBack) {
                IconButton(
                    onClick = {
                        if (mActivity == null)
                            mOnClickNavigation.invoke()
                        else
                            mActivity.finish()
                    }
                ){
                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        tint = colorResource(id = R.color.white),
                        contentDescription = "Localized description"
                    )
                }
            }
        },
        actions = {
            if (mIsProfile) {
                IconButton(onClick = { mOnClickProfileNavigation.invoke() }) {
                    Icon(
                        painter = mIconProfile,
                        contentDescription = "Localized description",
                        modifier = Modifier
                            .size(25.dp)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolAppBar(
    mTitle : String,
    mOnClickNavigation : () -> Unit = {},
) {
    val verticalGradientBrush = Brush.verticalGradient(
        colors = listOf(
            SecondPrimary,
            Primary,
        )
    )

    return TopAppBar(
        modifier = Modifier.background(verticalGradientBrush),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Transparent
        ),
        title = {
            Text(
                text = mTitle,
                color = colorResource(id = R.color.white),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    mOnClickNavigation.invoke()
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
                        mOnClickNavigation.invoke()
                    }
                    .size(30.dp),
                painter = painterResource(id = AceledaSplashLogo),
                contentDescription = "Localized description",
            )
        }
    )
}
