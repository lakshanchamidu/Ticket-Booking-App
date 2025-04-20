package com.example.ticketbookingapp.Activites.Splash

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ticketbookingapp.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen()
        }
    }
}
@Preview
@Composable
fun SplashScreen(onGetStartedClick:()-> Unit={}) {
    Column(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (backgroundImg, title, subtitle, startbtn) = createRefs()

            // Background image
            Image(
                painter = painterResource(R.drawable.splash_bg),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(backgroundImg) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            )

            // Styled Title Text
            val styledText = buildAnnotatedString {
                append("Discover your\nDream ")
                withStyle(style = SpanStyle(color = colorResource(id = R.color.orange))) {
                    append("Flight")
                }
                append("\nEasily")
            }

            Text(
                text = styledText,
                fontSize = 53.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 32.dp, start = 16.dp)
                    .constrainAs(title) {
                        top.linkTo(parent.top, margin = 100.dp)
                        start.linkTo(parent.start)
                    }
            )
            Text(
                text = stringResource(R.string.subtitle_splash),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.orange),
                modifier = Modifier
                    .padding(top = 32.dp, start = 16.dp)
                    .constrainAs(subtitle){
                        top.linkTo(title.bottom)
                        start.linkTo(title.start)
                    }
            )

            Box(
                modifier = Modifier .constrainAs(startbtn){
                    bottom.linkTo(parent.bottom)
                }
            ){
                GradientButton(onClick = onGetStartedClick, "Get Started", 32)
            }
        }
    }
}
