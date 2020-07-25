package com.example.kcchallenge.contacts.model

import androidx.annotation.StringRes
import com.example.kcchallenge.R

data class ContactDetailDataItem(
    val value: String,
    @StringRes val valueName: Int,
    @StringRes val valueDescription: Int = R.string.blank
)
