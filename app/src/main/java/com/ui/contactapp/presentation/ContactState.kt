package com.ui.contactapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ui.contactapp.data.database.Contact

data class ContactState(
    val contacts: List<Contact> = emptyList(),
    var id: MutableState<Int> = mutableStateOf(1),
    var name: MutableState<String> = mutableStateOf(""),
    var phoneNumber: MutableState<String> = mutableStateOf(""),
    var email: MutableState<String> = mutableStateOf(""),
    var dateOfCreation: MutableState<Long> = mutableStateOf(0),
    var image: MutableState<ByteArray?> = mutableStateOf(null)
)
