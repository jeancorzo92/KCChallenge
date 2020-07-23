package com.example.kcchallenge.contacts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.kcchallenge.R
import com.example.kcchallenge.contacts.model.Contact
import com.example.kcchallenge.contacts.repository.ContactsRepository
import com.example.kcchallenge.contacts.model.ContactListDataItem
import kotlinx.coroutines.Dispatchers

class ContactListViewmodel : ViewModel() {

    private val repository = ContactsRepository()
    private val favoriteContactsHeaderItem = ContactListDataItem.Header(R.string.favorite_contacts)
    private val otherContactsHeaderItem = ContactListDataItem.Header(R.string.other_contacts)

    val showLoading = MutableLiveData<Boolean>()

    val contactList = liveData(Dispatchers.IO) {
        showLoading.postValue(true)
        val contacts = repository.getContacts()
        emit(mapContactsToDataItems(contacts))
        showLoading.postValue(false)
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

}