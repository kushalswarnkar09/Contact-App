package com.ui.contactapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ui.contactapp.presentation.ContactViewModel
import com.ui.contactapp.presentation.navigation.NavGraph
import com.ui.contactapp.ui.theme.ContactAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = hiltViewModel<ContactViewModel>()
            val navHostController = rememberNavController()
            ContactAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(){
    val viewModel = hiltViewModel<ContactViewModel>()
    val navHostController = rememberNavController()

    val splashScreen = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            splashScreen.value = false
        },4000)
    }

    if (splashScreen.value){
        SplashScreen()
    }
    else{
        NavGraph(navHostController,viewModel)
    }
}

@Composable
fun SplashScreen(){
    Box(Modifier.fillMaxSize().background(Color.White), contentAlignment = Alignment.Center){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(R.drawable.tel),null,
                modifier= Modifier.size(200.dp).clip(CircleShape))
            BasicText(text = "Contact App",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = Color.Black,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                ))
        }
    }
}

//fun MainScreen(){
//    val navHostController = rememberNavController()
//    var showSplash = remember { mutableStateOf(true) }

//
//    LaunchedEffect(Unit) {
//        Handler(Looper.getMainLooper()).postDelayed({
//            showSplash.value = false
//        },3000
//        )
//    }
//
//    if (showSplash.value){
//        SplashScreen()
//    }
//    else{
//        NavGraph(navHostController)
//    }
//}
//
//@Composable
//fun SplashScreen(){
//    Box(Modifier.fillMaxSize().background(Color.Magenta), contentAlignment = Alignment.Center){
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Image(painter = painterResource(R.drawable.book),null,
//                modifier= Modifier.size(300.dp))
//            BasicText(text = "Welcome to the Book Library",
//                style = MaterialTheme.typography.labelLarge.copy(
//                    color = Color.Black,
//                    fontSize = 18.sp,
//                    textAlign = TextAlign.Center
//                ))
//        }
//    }
//}