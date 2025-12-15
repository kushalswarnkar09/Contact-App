package com.ui.contactapp.presentation.screens

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ui.contactapp.presentation.ContactState
import com.ui.contactapp.presentation.ContactViewModel
import com.ui.contactapp.presentation.navigation.Routes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: ContactState,
    viewModel: ContactViewModel,
    navHostController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contact Keeper") },
                actions = {
                    Icon(
                        Icons.AutoMirrored.Filled.Sort,
                        "sort",
                        modifier = Modifier.clickable { viewModel.changeInSorting() })
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Color.Black,
                contentColor = Color.White,
                onClick = {
                    navHostController.navigate(Routes.AddEdit.route)
                }
            ) {
                Icon(Icons.Default.Add, "add")
            }
        }
    ) { innerpadding ->
        Column(Modifier
            .fillMaxSize()
            .padding(innerpadding)) {
            LazyColumn {
                items(state.contacts) { contacts ->
                    val bitmap = contacts.image?.let {
                        BitmapFactory.decodeByteArray(it, 0, it.size)
                    }?.asImageBitmap()

                    ContactCard(
                        viewModel = viewModel,
                        state = state,
                        name = contacts.name,
                        phone = contacts.phoneNumber,
                        email = contacts.email,
                        imageByteArray = contacts.image,
                        image = bitmap,
                        dateOfCreation = contacts.dateOfCreation,
                        id = contacts.id,
                        navHostController = navHostController,
                    )
                }
            }
        }
    }
}


@Composable
fun ContactCard(
    name: String,
    phone: String,
    email: String,
    imageByteArray: ByteArray?,
    image: ImageBitmap?,
    dateOfCreation: Long,
    id: Int,
    viewModel: ContactViewModel,
    state: ContactState,
    navHostController: NavHostController
) {
    val context = LocalContext.current
    Card(
        onClick = {
            state.id.value = id
            state.name.value = name
            state.email.value = email
            state.image.value = imageByteArray
            state.phoneNumber.value = phone
            state.dateOfCreation.value = dateOfCreation
            navHostController.navigate(Routes.AddEdit.route)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (image != null) {
                androidx.compose.foundation.Image(
                    bitmap = image,
                    contentDescription = "Contac image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Contac image",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.onPrimaryContainer)
                        .padding(16.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                    )}

                Spacer(modifier=Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer)

                    Text(text = phone,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer)

                    Text(text = email,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
                Spacer(Modifier.width(12.dp))
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(
                        onClick = {
                            state.id.value = id
                            state.name.value = name
                            state.email.value = email
                            state.phoneNumber.value = phone
                            state.dateOfCreation.value = dateOfCreation
                            viewModel.deleteContact()
                        },
                    ) {
                        Icon(Icons.Outlined.Delete, contentDescription = "delete", tint = MaterialTheme.colorScheme.error)
                    }
                    IconButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_CALL)
                            intent.data = Uri.parse("tel: $phone")
                            context.startActivity(intent)
                        }
                    ) {
                        Icon(Icons.Default.Call,"call", tint = MaterialTheme.colorScheme.primary)
                    }
                }

            }
        }
    }

