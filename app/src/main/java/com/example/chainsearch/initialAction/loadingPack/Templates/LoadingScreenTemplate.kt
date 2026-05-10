package com.example.chainsearch.initialAction.loadingPack.Templates

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.with
import com.example.chainsearch.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.example.chainsearch.feature_register.presentation.layout.SignUpTemplate

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LoadingScreen(viewModel: LoadingScreenViewModel) {
    val state = viewModel.activityDrawnState.collectAsState().value

    AnimatedContent(targetState = state,
        transitionSpec = {
            fadeIn() + slideInVertically(animationSpec = tween(200),
                initialOffsetY = { fullHeight -> fullHeight }) with
                    fadeOut(animationSpec = tween(200))
        }) { targetState ->

        when (targetState) {
            1 -> IntroScreenTemplate(viewModel)
            2 -> LoadingScreenTemplate()
            3 -> SignUpTemplate(viewModel)
            4 -> LogInTemplate(viewModel)
        }
    }
}

@Composable
fun LoadingScreenTemplate() {
    val lightOrange1 = Color(251, 237, 216, 255)
    val transition = rememberInfiniteTransition()
    val anim = transition.animateFloat(
        initialValue = 5000F,
        targetValue = 7000F,
        animationSpec = infiniteRepeatable(
            animation = tween(1750),
            repeatMode = RepeatMode.Reverse
        )
    )

    val animColor = transition.animateColor(
        initialValue = Color.White,
        targetValue = lightOrange1,
        animationSpec = infiniteRepeatable(
            animation = tween(3500),
            repeatMode = RepeatMode.Reverse
        )
    )
    val animColor2 = transition.animateColor(
        initialValue = lightOrange1,
        targetValue = Color.White,
        animationSpec = infiniteRepeatable(
            animation = tween(3500),
            repeatMode = RepeatMode.Reverse
        )
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(15.dp))
            .background(brush = Brush.verticalGradient(
                0.1f to animColor.value,
                0.3f to animColor2.value,
                startY = 0f,
                endY = anim.value
            )),
        color = lightOrange1
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Versatile",
                modifier = Modifier.padding(start = 85.dp, top = 30.dp),
                style = TextStyle(fontSize = 40.sp),
                fontWeight = FontWeight(200)
            )

            Text(
                text = "_",
                modifier = Modifier.padding(start = 89.dp, top = 40.dp),
                style = TextStyle(fontSize = 40.sp),
                fontWeight = FontWeight(200)
            )

            val animDots by transition.animateFloat(initialValue = 1f, targetValue = 3f, animationSpec = infiniteRepeatable(tween(250), RepeatMode.Reverse))
            Text(
                text = "is loading" + ".".repeat(animDots.toInt()),
                modifier = Modifier.padding(start = 180.dp, top = 75.dp),
                style = TextStyle(fontSize = 25.sp),
                fontWeight = FontWeight(200)
            )

            val anim1 by transition.animateFloat(initialValue = 625f, targetValue = 775f, animationSpec = infiniteRepeatable(tween(750), RepeatMode.Reverse))
            val anim2 by transition.animateFloat(initialValue = 800f, targetValue = 600f, animationSpec = infiniteRepeatable(tween(850), RepeatMode.Reverse))
            val anim3 by transition.animateFloat(initialValue = 650f, targetValue = 750f, animationSpec = infiniteRepeatable(tween(650), RepeatMode.Reverse))

            val animColor1 by transition.animateColor(initialValue = Color(204, 106, 20), targetValue = Color(255, 166, 77), animationSpec = infiniteRepeatable(tween(750), RepeatMode.Reverse))
            val animColor2 by transition.animateColor(initialValue = Color(255, 166, 77), targetValue = Color(204, 106, 20), animationSpec = infiniteRepeatable(tween(850), RepeatMode.Reverse))
            val animColor3 by transition.animateColor(initialValue = Color(204, 106, 20), targetValue = Color(255, 166, 77), animationSpec = infiniteRepeatable(tween(650), RepeatMode.Reverse))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Top
            ) {
                LoadingIcon(anim1, animColor1, Modifier.weight(1f))
                LoadingIcon(anim2, animColor2, Modifier.weight(1f))
                LoadingIcon(anim3, animColor3, Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun LoadingIcon(yOffset: Float, color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.rectangle),
            contentDescription = null,
            modifier = Modifier
                .offset(y = yOffset.dp)
                .scale(.45f),
            colorFilter = ColorFilter.tint(color)
        )
    }
}

@Composable
@Preview
fun Preview() {
    LoadingScreen(LoadingScreenViewModel())
}
