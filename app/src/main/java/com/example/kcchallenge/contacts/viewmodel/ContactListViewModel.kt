package com.example.kcchallenge.contacts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kcchallenge.R
import com.example.kcchallenge.contacts.model.Contact
import com.example.kcchallenge.contacts.repository.ContactsRepository
import com.example.kcchallenge.contacts.model.ContactListDataItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactListViewModel : ViewModel() {

    private val repository = ContactsRepository()
    private val favoriteContactsHeaderItem = ContactListDataItem.Header(R.string.favorite_contacts)
    private val otherContactsHeaderItem = ContactListDataItem.Header(R.string.other_contacts)
    private val coroutineExceptionHanlder = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
        error.value = true
    }
    private var contactList: List<Contact> = listOf()

    val showLoading = MutableLiveData<Boolean>()
    val contactDataItems = MutableLiveData<List<ContactListDataItem>>()
    val error = MutableLiveData<Boolean>()

    fun loadContacts() {
        showLoading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {
            contactList = repository.getContacts()
            contactDataItems.postValue(mapContactsToDataItems(contactList))
            showLoading.postValue(false)
        }
    }

    private fun mapContactsToDataItems(contacts: List<Contact>): List<ContactListDataItem> {
        val allContacts = contacts.sortedBy { it.name }.map { ContactListDataItem.ContactItem(it) }
        val favoriteContacts = allContacts.filter { it.contact.isFavorite }
        val otherContacts = allContacts - favoriteContacts
        val dataItems = mutableListOf<ContactListDataItem>()
        if (favoriteContacts.isNotEmpty()) {
            dataItems.add(favoriteContactsHeaderItem)
            dataItems.addAll(favoriteContacts)
            if (otherContacts.isNotEmpty()) {
                dataItems.add(otherContactsHeaderItem)
            }
        }
        dataItems.addAll(otherContacts)
        return dataItems
    }

    fun contactUpdated(contact: Contact) {
        this.contactList.find { it.id == contact.id }?.isFavorite = contact.isFavorite
        contactDataItems.value = mapContactsToDataItems(contactList)
    }

}