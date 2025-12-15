package com.ui.contactapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.blur
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.graphicsLayer
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//@Preview(showSystemUi = true)
//@Composable
//fun tstUI() {
//    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
//    ) {
//        Image(
//            painter = painterResource(R.drawable.my),
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxSize()
//                .graphicsLayer {
//                    rotationZ = 90f
//                    scaleX = 2.2f // increase scale to cover edges
//                    scaleY = 1.5f
//                }
//                .blur(radius = 4.dp),
//
//            )
//
//        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center) {
//            Text(
//                "Hey! Myself Kushal soni",
//                color = Color.Black,
//                fontSize = 20.sp,
//                modifier = Modifier.background(Color.White),
//                textAlign = TextAlign.Center
//            )
//        }
//
//
//    }
//}

//@Composable
//fun Test() {
//    Box(Modifier
//        .fillMaxSize()
//        .background(Color.Yellow)
//        .blur(8.dp)) {
//        Column(modifier = Modifier
//            .fillMaxWidth()
//            .height(330.dp)
//            .background(Color.Cyan , shape = CutCornerShape(bottomStart = 200.dp, bottomEnd = 200.dp))
//            .blur(8.dp)
//        ) {
//
//        }
//        Column(Modifier.fillMaxSize()) {
//        FloatingActionButton(onClick = {}) {
//            Icon(Icons.Default.Add, null)
//        }
//        }
//    }
//}
//
//@Preview(showSystemUi = true)
//@Composable
//fun TestPreview(){
//    Test()
//}