package com.example.ticketbookingapp.activites.splash


import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.ticketbookingapp.activites.dashboard.DashboardActivity
import com.example.ticketbookingapp.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SplashScreen(onGetStartedClick = {
                startActivity(Intent(this, DashboardActivity::class.java))
            })
        }
    }
}
@Preview
@Composable
fun SplashScreen(onGetStartedClick:()-> Unit={}) {
    StatusBarTop()
    Column(modifier = Modifier
        .fillMaxSize()
        ) {
        ConstraintLayout() {
            val (backgroundImg, title, subtitle, startbtn) = createRefs()

            Image(
                painter = painterResource(R.drawable.splash_bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(backgroundImg) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .fillMaxSize()

            )


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
                    .padding(top = 32.dp)
                    .padding(horizontal = 16.dp)
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
@Composable
fun StatusBarTop(){
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = false

        )
    }
}

