package com.example.kcchallenge.contacts.repository.dto

import com.google.gson.annotations.SerializedName

data class AddressDTO(
    @SerializedName("street")
    val street: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("zipCode")
    val zipCode: String
)

