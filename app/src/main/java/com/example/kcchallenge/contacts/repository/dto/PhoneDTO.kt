package com.example.kcchallenge.contacts.repository.dto

import com.google.gson.annotations.SerializedName

data class PhoneDTO(
    @SerializedName("work")
    val work: String?,
    @SerializedName("home")
    val home: String?,
    @SerializedName("mobile")
    val mobile: String?
)
