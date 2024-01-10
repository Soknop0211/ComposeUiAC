package com.example.aceledacomposeui.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aceledacomposeui.ui.theme.AceledaComposeUITheme
import com.example.aceledacomposeui.ui.theme.AceledaSplashLogo
import com.example.aceledacomposeui.ui.theme.Primary
import kotlinx.coroutines.delay


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AceledaComposeUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SplashScreen(this)
                }
            }
        }
    }
}

@Composable
fun SplashScreen(
    activity : Activity,
) {
    val scale = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.8f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            )
        )
        delay(1200L)

        MainActivity.start(activity)

        activity.finishAffinity()
    }

    Splash(scale = scale.value)
}

@Composable
fun Splash(
    modifier: Modifier = Modifier,
    scale: Float
) {
    Box(
        modifier = modifier
            .background(Primary)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .scale(scale)
                .padding(all = 64.dp),
            painter = painterResource(id = AceledaSplashLogo),
            contentDescription = "Logo App"
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash(scale = 0f)
}
