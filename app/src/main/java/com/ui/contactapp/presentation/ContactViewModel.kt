package com.ui.contactapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.ui.contactapp.data.database.Contact
import com.ui.contactapp.data.database.ContactDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine

import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(var database: ContactDatabase): ViewModel() {
    private var isSortedByName = MutableStateFlow(true)
    private var contact = isSortedByName.flatMapLatest {
        if (it) database.getDao().getContactsSortedByName()
        else database.getDao().getContactsSortedByDate()
    }.stateIn(viewModelScope , SharingStarted.WhileSubscribed(), emptyList())

    val _state = MutableStateFlow(ContactState())
    val state = combine( _state, contact, isSortedByName){state, contacts, isSortedByName->
        state.copy( contacts = contacts)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(3000), ContactState())

    fun changeInSorting(){
        isSortedByName.value = !isSortedByName.value
    }

    fun saveContact(){
        val contact = Contact(
            id = state.value.id.value,
            name = _state.value.name.value,
            phoneNumber = _state.value.phoneNumber.value,
            email = _state.value.email.value,
            isActive = true,
            dateOfCreation = System.currentTimeMillis().toLong(),
            image = state.value.image.value
        )

        viewModelScope.launch {
            database.getDao().upsertContact(contact)
        }
        state.value.id.value= 0
        _state.value.name.value= ""
        _state.value.phoneNumber.value= ""
        _state.value.email.value= ""
        _state.value.image.value= null
    }
    fun deleteContact(){
        val contact = Contact(
            id = state.value.id.value,
            name = _state.value.name.value,
            phoneNumber = _state.value.phoneNumber.value,
            email = _state.value.email.value,
            isActive = true,
            dateOfCreation = System.currentTimeMillis().toLong(),
            image = state.value.image.value
        )

        viewModelScope.launch {
            database.getDao().deleteContact(contact)
        }
        state.value.id.value= 0
        _state.value.name.value= ""
        _state.value.phoneNumber.value= ""
        _state.value.email.value= ""
        _state.value.dateOfCreation.value= 0
        _state.value.image.value= null
    }
}












