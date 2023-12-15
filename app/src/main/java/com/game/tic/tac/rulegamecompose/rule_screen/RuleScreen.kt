package com.game.tic.tac.rulegamecompose.rule_screen

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.game.tic.tac.rulegamecompose.R
import com.game.tic.tac.rulegamecompose.ui.theme.Red
import com.game.tic.tac.rulegamecompose.utils.IsBlackUtil
import com.game.tic.tac.rulegamecompose.utils.IsRedUtil
import com.game.tic.tac.rulegamecompose.utils.NumberUtil
import kotlin.math.roundToInt

//https://youtu.be/5DsjouGYJnE?si=OcpCF47j5NYQ2qT9&t=788
@Composable
fun RuleScreen() {

    var rotationValue by remember {
        mutableStateOf(0f)
    }

    var number by remember {
        mutableStateOf(0)
    }

    var isColor by remember {
        mutableStateOf("")
    }

    val angle: Float by animateFloatAsState(
        targetValue = rotationValue,
        animationSpec = tween(
            durationMillis = 2000,
            easing = LinearOutSlowInEasing
        ),
        finishedListener = {
            val cell = ((360f - (it % 360)) / (360f / NumberUtil.list.size)).roundToInt()
            number = NumberUtil.list[cell]
            if (IsRedUtil.list.indexOf(number) > 0) {
                isColor = "Red"
            } else if (IsBlackUtil.list.indexOf(number) > 0) {
                isColor = "Black"
            } else {
                isColor = "Green"
            }
        },
        label = ""
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .wrapContentHeight()
                .wrapContentWidth(),
            text = number.toString(),

            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            color = Color.White,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(103.dp)
                .wrapContentHeight()
                .wrapContentWidth(),
            text = isColor,

            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            color = Color.White,
        )

        Box(modifier = Modifier
            .weight(1f)
            .fillMaxSize()) {

            Image(
                painter = painterResource(id = R.drawable.ruleta),
                contentDescription = "Roulette",
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(angle)
            )
            Image(
                painter = painterResource(id = R.drawable.flecha),
                contentDescription = "Fleche",
                modifier = Modifier
                    .fillMaxSize(),
            )
        }
        Button(
            onClick = {
                      rotationValue = (720..1080).random().toFloat() + angle
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Red,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = "Start",
            )
        }
    }

}