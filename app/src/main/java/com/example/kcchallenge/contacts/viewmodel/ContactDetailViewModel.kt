package com.example.kcchallenge.contacts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kcchallenge.R
import com.example.kcchallenge.contacts.model.Address
import com.example.kcchallenge.contacts.model.Contact
import com.example.kcchallenge.contacts.model.ContactDetailDataItem
import com.example.kcchallenge.contacts.model.Phone
import java.text.SimpleDateFormat
import java.util.*

class ContactDetailViewModel : ViewModel() {
    private val birthDateFormatPattern = "MMMM d, yyyy"

    val contactDetails = MutableLiveData<List<ContactDetailDataItem>>()

    fun prepareContactDetails(contact: Contact) {
        val contactDetailList = mutableListOf<ContactDetailDataItem>()
        contactDetailList.addAll(getContactPhoneDetails(contact.phone))

        if (contact.address.isNotBlank()) {
            contactDetailList.add(getContactPhoneFullAddress(contact.address))
        }

        if (contact.birthDate != null) {
            contactDetailList.add(getContactFormattedBirthdate(contact.birthDate))
        }

        if (contact.emailAddress.isNotBlank()) {
            contactDetailList.add(ContactDetailDataItem(contact.emailAddress, R.string.contact_email_label))
        }

        contactDetails.value = contactDetailList
    }

    private fun getContactPhoneDetails(phone: Phone): List<ContactDetailDataItem> {
        val phones = mutableListOf<ContactDetailDataItem>()
        if (phone.home.isNotBlank()) {
            phones.add(ContactDetailDataItem(phone.home, R.string.contact_phone_label, R.string.contact_phone_home_label))
        }
        if (phone.mobile.isNotBlank()) {
            phones.add(ContactDetailDataItem(phone.mobile, R.string.contact_phone_label, R.string.contact_phone_mobile_label))
        }
        if (phone.work.isNotBlank()) {
            phones.add(ContactDetailDataItem(phone.work, R.string.contact_phone_label, R.string.contact_phone_work_label))
        }
        return phones
    }

    private fun getContactPhoneFullAddress(address: Address): ContactDetailDataItem {
        val fullAddress = """
            ${address.street}
            ${address.city}, ${address.state} ${address.zipCode}, ${address.country}
            """.trimMargin().trimIndent()
        return ContactDetailDataItem(fullAddress, R.string.contact_address_label)
    }

    private fun getContactFormattedBirthdate(birthDate: Date): ContactDetailDataItem {
        val dateFormat = SimpleDateFormat(birthDateFormatPattern)
        val formattedDate = dateFormat.format(birthDate)
        return ContactDetailDataItem(formattedDate, R.string.contact_birthdate_label)
    }

}