package com.example.kcchallenge.contacts.ui

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.kcchallenge.contacts.model.Contact

class ContactDetailsContract : ActivityResultContract<Contact, Contact?>() {

    override fun createIntent(context: Context, contact: Contact): Intent {
        return Intent(context, ContactDetailActivity::class.java).apply {
            putExtra(EXTRA_CONTACT, contact)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Contact? = when {
        resultCode != RESULT_CONTACT_EDITED -> null
        else -> intent?.getParcelableExtra(EXTRA_CONTACT)
    }

}