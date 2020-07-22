package com.example.kcchallenge.contacts.repository

import com.example.kcchallenge.contacts.model.Address
import com.example.kcchallenge.contacts.model.Contact
import com.example.kcchallenge.contacts.model.Phone
import com.example.kcchallenge.contacts.repository.dto.AddressDTO
import com.example.kcchallenge.contacts.repository.dto.ContactDTO
import com.example.kcchallenge.contacts.repository.dto.PhoneDTO

object ContactAdapter {
    fun toModel(contactDTO: ContactDTO): Contact {
        return Contact(
            contactDTO.id,
            contactDTO.name,
            contactDTO.companyName ?: "",
            contactDTO.isFavorite,
            contactDTO.smallImageURL,
            contactDTO.largeImageURL,
            contactDTO.emailAddress,
            contactDTO.birthDate,
            PhoneAdapter.toModel(contactDTO.phone),
            AddressAdapter.toModel(contactDTO.address)
        )
    }
}

object PhoneAdapter {
    fun toModel(phoneDTO: PhoneDTO) : Phone {
        return Phone(
            phoneDTO.work ?: "",
            phoneDTO.home ?: "",
            phoneDTO.mobile ?: ""
        )
    }
}

object AddressAdapter {
    fun toModel(addressDTO: AddressDTO) : Address {
        return Address(
            addressDTO.street,
            addressDTO.city,
            addressDTO.state,
            addressDTO.country,
            addressDTO.zipCode
        )
    }
}