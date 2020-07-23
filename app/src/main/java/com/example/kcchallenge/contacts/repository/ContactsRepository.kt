package com.example.kcchallenge.contacts.repository

import com.example.kcchallenge.contacts.model.Contact
import com.example.kcchallenge.service.ServiceGenerator

class ContactsRepository {
    private val client by lazy { ServiceGenerator.createService(ContactsService::class.java) }

    suspend fun getContacts(): List<Contact> {
        return client.getContacts().map { contactDTO ->
            ContactAdapter.toModel(contactDTO)
        }
    }
}