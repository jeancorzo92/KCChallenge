package com.example.kcchallenge.contacts.repository

import com.example.kcchallenge.contacts.repository.dto.ContactDTO
import retrofit2.http.GET

interface ContactsService {

    @GET("contacts.json")
    suspend fun getContacts(): List<ContactDTO>

}