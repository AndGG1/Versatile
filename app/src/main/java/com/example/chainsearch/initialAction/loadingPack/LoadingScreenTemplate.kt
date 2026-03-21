package com.example.chainsearch.initialAction.loadingPack

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import com.example.chainsearch.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chainsearch.initialAction.viewModels.LoadingScreenViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource

@Composable
fun LoadingScreen(viewModel: LoadingScreenViewModel) {
    if (!viewModel.activityDrawnState.collectAsState().value) {
        LoadingScreenTemplate_1()
    } else {
        LoadingScreenTemplate_2()
    }
}

@Composable
fun LoadingScreenTemplate_1() {
    val orange: Color = Color(204, 106, 20, 255)
    val lightOrange1: Color = Color(251, 237, 216, 255)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(8.dp)
            .clip(RoundedCornerShape(15.dp)),
        color = lightOrange1
    ) {

        Text(
            text = "Versatile",
            modifier = Modifier.padding(start = 85.dp, top = 30.dp),
            style = TextStyle(fontSize = 40.sp),
            fontWeight = FontWeight(200)
        )

        Text(
            text = "_",
            modifier = Modifier.padding(start = 88.dp, top = 40.dp),
            style = TextStyle(fontSize = 40.sp),
            fontWeight = FontWeight(200)
        )

        Text(
            text = "is loading...",
            modifier = Modifier.padding(start = 180.dp, top = 75.dp),
            style = TextStyle(fontSize = 25.sp),
            fontWeight = FontWeight(200)
        )

        val transition = rememberInfiniteTransition()
        val anim = transition.animateFloat(
            initialValue = 625F,
            targetValue = 775F,
            animationSpec = infiniteRepeatable(
                animation = tween(750),
                repeatMode = RepeatMode.Reverse
            )
        )

        val anim2 = transition.animateFloat(
            initialValue = 800F,
            targetValue = 600F,
            animationSpec = infiniteRepeatable(
                animation = tween(850),
                repeatMode = RepeatMode.Reverse
            )
        )

        val anim3 = transition.animateFloat(
            initialValue = 650F,
            targetValue = 750F,
            animationSpec = infiniteRepeatable(
                animation = tween(650),
                repeatMode = RepeatMode.Reverse
            )
        )

        val animColor = transition.animateColor(
            initialValue = Color(204, 106, 20, 255),
            targetValue = Color(255, 166, 77, 255),
            animationSpec = infiniteRepeatable(
                animation = tween(750),
                repeatMode = RepeatMode.Reverse
            )
        )

        val animColor2 = transition.animateColor(
            initialValue = Color(255, 166, 77, 255),
            targetValue = Color(204, 106, 20, 255),
            animationSpec = infiniteRepeatable(
                animation = tween(850),
                repeatMode = RepeatMode.Reverse
            )
        )

        val animColor3 = transition.animateColor(
            initialValue = Color(204, 106, 20, 255),
            targetValue = Color(255, 166, 77, 255),
            animationSpec = infiniteRepeatable(
                animation = tween(650),
                repeatMode = RepeatMode.Reverse
            )
        )

        Box(modifier = Modifier.padding(top = anim.value.dp)) {
            Image(
                painter = painterResource(id = R.drawable.rectangle),
                contentDescription = "Menu icon (vector)",
                modifier = Modifier
                    .scale(.3F),
                colorFilter = ColorFilter.tint(animColor.value)
            )
        }

        Box(modifier = Modifier.padding(top = anim2.value.dp, start = 85.dp)) {
            Image(
                painter = painterResource(id = R.drawable.rectangle),
                contentDescription = "Menu icon (vector)",
                modifier = Modifier
                    .scale(.3F),
                colorFilter = ColorFilter.tint(animColor2.value)
            )
        }

        Box(modifier = Modifier.padding(top = anim3.value.dp, start = 175.dp)) {
            Image(
                painter = painterResource(id = R.drawable.rectangle),
                contentDescription = "Menu icon (vector)",
                modifier = Modifier
                    .scale(.3F),
                colorFilter = ColorFilter.tint(animColor3.value)
            )
        }
    }
}

@Composable
fun LoadingScreenTemplate_2() {
    //TODO: Make Auth screen
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray)
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
        ,
        color = Color.Blue
    ) {}
}

@Composable
@Preview
fun preview() {
    LoadingScreen(LoadingScreenViewModel())
}