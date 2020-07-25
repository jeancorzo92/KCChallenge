package com.example.kcchallenge.contacts.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Contact(
    val id: Long,
    val name: String,
    val companyName: String,
    var isFavorite: Boolean,
    val smallImageURL: String,
    val largeImageURL: String,
    val emailAddress: String,
    val birthDate: Date?,
    val phone: Phone,
    val address: Address
) : Parcelable