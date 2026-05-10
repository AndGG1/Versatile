package com.example.chainsearch.initialAction.loadingPack.Templates

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chainsearch.R
//import com.example.chainsearch.initialAction.auth.loginFunctionality.callLoginEmail //todo
import com.example.chainsearch.feature_register.presentation.helpers.LayoutHelpers.StatesManager
import com.example.chainsearch.feature_register.presentation.helpers.LayoutHelpers.TextConfig
import com.example.chainsearch.initialAction.viewModels.LoadingScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInTemplate(viewModel: LoadingScreenViewModel) {
    val currContext = LocalContext.current

    val orange = Color(204, 106, 20, 255)
    val lightOrange1 = Color(251, 237, 216, 255)
    val lightOrange2 = Color(255, 212, 159, 255)
    val lightOrange3 = Color(255, 159, 56, 255)

    val emailReady = remember { mutableStateOf(false) }
    val passwordReady = remember { mutableStateOf(false) }

    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }

    val showError = remember { mutableStateOf(false) }
    var errorM by remember { mutableStateOf("") }

    val anim = rememberInfiniteTransition().animateFloat(
        initialValue = 5000F,
        targetValue = 7000F,
        animationSpec = infiniteRepeatable(
            animation = tween(1750),
            repeatMode = RepeatMode.Reverse
        )
    )

    val animColor = rememberInfiniteColorTransition(
        start = Color.White,
        end = lightOrange2
    )

    val animColor2 = rememberInfiniteColorTransition(
        start = lightOrange2,
        end = Color.White
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    0.1f to animColor.value,
                    0.3f to animColor2.value,
                    startY = 0f,
                    endY = anim.value
                )
            ),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.Bottom
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(.25f)
            ) {
                drawCircle(
                    color = Color(255, 166, 77, 255),
                    radius = size.height / 2f,
                    center = Offset(size.width / 2f, size.height / 1.4f)
                )
            }
        }

        Box {
            if (showError.value) {
                DisplayErrorMessage(
                    errorM,
                    onClose = { showError.value = false },
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Versatile",
                    modifier = Modifier.padding(top = 40.dp),
                    style = TextStyle(fontSize = 40.sp),
                    fontWeight = FontWeight(200)
                )

                Text(
                    text = "_",
                    style = TextStyle(fontSize = 40.sp),
                    fontWeight = FontWeight(200),
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .offset(x = (-63).dp, y = (-48).dp)
                )
            }

            Text(
                text = "Log in",
                modifier = Modifier.padding(start = 190.dp, top = 85.dp),
                style = TextStyle(fontSize = 25.sp),
                fontWeight = FontWeight(200)
            )

            ColumnConfig(lightOrange3, 280, "  Authentication Info.  ", Color.White)

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "~ or ~",
                    modifier = Modifier.padding(top = 510.dp),
                    style = TextStyle(fontSize = 18.sp, fontStyle = FontStyle.Italic),
                    fontWeight = FontWeight(200)
                )
            }

            var isEnabled by remember { mutableStateOf(true) }
            val scope = rememberCoroutineScope()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 535.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CompositionLocalProvider(LocalRippleConfiguration provides null) {
                    Button(
                        onClick = {
                            isEnabled = false
                            scope.launch {
                                delay(1500)
                                isEnabled = true
                            }
                        },
                        enabled = isEnabled,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            disabledContentColor = Color.Transparent
                        ),
                        modifier = Modifier.background(color = Color.Transparent)
                    ) {
                        Text(
                            text = "       Log in with Google  ",
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(6.dp, 6.dp, 6.dp, 6.dp))
                                .background(Color.White)
                                .padding(6.dp)
                                .background(Color.White),
                            color = Color.DarkGray,
                            style = TextStyle(fontSize = 20.sp)
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 549.dp)
                    .offset(x = (-95).dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = "Menu icon (vector)",
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 650.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CompositionLocalProvider(LocalRippleConfiguration provides null) {
                    Button(
                        onClick = {
                            isEnabled = false
                            scope.launch {
                                delay(3000)
                                isEnabled = true
                            }

                            if (!emailReady.value) {
                                errorM = "Email should not be empty!"
                                showError.value = true
                            } else if (!passwordReady.value) {
                                errorM = "Password should be between 4 and 16 characters long!"
                                showError.value = true
                            }

                            if (emailReady.value && passwordReady.value) {
                                viewModel.setNewVal(2)
                                // callLoginEmail(emailValue.value, passwordValue.value, viewModel) //TODO
                            }
                        },
                        enabled = isEnabled,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            disabledContentColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .background(color = Color.Transparent)
                            .scale(1.35F)
                    ) {
                        Text(
                            text = "Log In",
                            color = lightOrange1,
                            modifier = Modifier
                                .background(
                                    color = lightOrange3,
                                    shape = RoundedCornerShape(50)
                                )
                                .padding(horizontal = 14.dp, vertical = 8.dp),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
            }
        }

        val emailScaleState = remember { mutableStateOf(false) }
        Box(modifier = Modifier.padding(top = 350.dp, start = 70.dp)) {
            ImageHolder(emailScaleState)
        }
        val stateManager = StatesManager(emailScaleState, emailReady, emailValue)
        val textConfig = TextConfig("", "Email", 328, 1..254)
        CustomTextLabel(stateManager, textConfig)

        val passwordScaleState = remember { mutableStateOf(false) }
        Box(modifier = Modifier.padding(top = 450.dp, start = 70.dp)) {
            ImageHolder(passwordScaleState)
        }
        val stateManager2 = StatesManager(passwordScaleState, passwordReady, passwordValue)
        val textConfig2 = TextConfig("", "Password", 428, 4..16)
        CustomTextLabel(stateManager2, textConfig2)
    }
}

@Composable
private fun CustomTextLabel(stateManager: StatesManager, data: TextConfig) {
    var container by remember { mutableStateOf(data.text) }

    Box(modifier = Modifier.padding(top = data.top.dp)) {
        TextField(
            value = container,
            onValueChange = { newValue ->
                container = newValue
                stateManager.isReady.value = newValue.length in data.range
                stateManager.value.value = newValue
            },
            label = { Text(data.label) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent
            ),
            modifier = Modifier.onFocusChanged { focusState ->
                stateManager.state.value = focusState.isFocused
            }
        )
    }
}

@Composable
private fun ColumnConfig(lightOrange: Color, top: Int, text: String, color: Color) {
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = text,
            modifier = Modifier
                .padding(top = top.dp)
                .clip(shape = RoundedCornerShape(6.dp, 6.dp, 6.dp, 6.dp))
                .background(color = lightOrange)
                .padding(3.dp)
                .background(color, RoundedCornerShape(6.dp)),
            style = TextStyle(fontSize = 15.sp)
        )
    }
}

@Composable
private fun ImageHolder(state: MutableState<Boolean>) {
    Image(
        painter = painterResource(id = R.drawable.rectangle_2),
        contentDescription = "Menu icon (vector)",
        modifier = Modifier
            .scale(if (state.value) {1.82F} else 1.72F),
    )
}

@Composable
private fun rememberInfiniteColorTransition(
    start: Color,
    end: Color,
    duration: Int = 3500
): State<Color> {
    val transition = rememberInfiniteTransition(label = "colorTransition")
    return transition.animateColor(
        initialValue = start,
        targetValue = end,
        animationSpec = infiniteRepeatable(
            animation = tween(duration),
            repeatMode = RepeatMode.Reverse
        ),
        label = "colorAnim"
    )
}

@Composable
private fun DisplayErrorMessage(
    errorMessage: String,
    onClose: () -> Unit,
) {
    val backgroundColor = Color(215, 59, 59, 255).copy(alpha = 0.96f)
    val textColor = Color(239, 188, 188, 255)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(50)
                )
                .padding(horizontal = 14.dp, vertical = 8.dp)
        ) {

            Text(
                text = errorMessage + " ".repeat(6),
                color = textColor,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.align(Alignment.Center)
            )

            IconButton(
                onClick = onClose,
                modifier = Modifier
                    .size(18.dp)
                    .align(Alignment.CenterEnd),
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewLogIn() {
    LogInTemplate(LoadingScreenViewModel())
}