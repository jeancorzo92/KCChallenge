package com.example.kcchallenge.contacts.repository.dto

import com.google.gson.annotations.SerializedName
import java.util.*

data class ContactDTO(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("companyName")
    val companyName: String?,
    @SerializedName("isFavorite")
    val isFavorite: Boolean,
    @SerializedName("smallImageURL")
    val smallImageURL: String,
    @SerializedName("largeImageURL")
    val largeImageURL: String,
    @SerializedName("emailAddress")
    val emailAddress: String,
    @SerializedName("birthdate")
    val birthDate: Date,
    @SerializedName("phone")
    val phone: PhoneDTO,
    @SerializedName("address")
    val address: AddressDTO
)