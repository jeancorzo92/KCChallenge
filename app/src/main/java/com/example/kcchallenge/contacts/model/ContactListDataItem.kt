package com.example.kcchallenge.contacts.model

import androidx.annotation.StringRes

sealed class ContactListDataItem {
    abstract val id: Long

    data class ContactItem(val contact: Contact) : ContactListDataItem() {
        override val id: Long = contact.id
    }

    data class Header(@StringRes val title: Int) : ContactListDataItem() {
        override val id: Long = Long.MIN_VALUE
    }
}