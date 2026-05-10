package com.example.chainsearch.initialAction.loadingPack.helpers.IntroScreenHelpers

import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun rememberTransparentButtonColors() = ButtonDefaults.buttonColors(
    containerColor = Color.Transparent,
    contentColor = Color.Transparent,
    disabledContainerColor = Color.Transparent,
    disabledContentColor = Color.Transparent
)