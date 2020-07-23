package com.example.kcchallenge.contacts.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kcchallenge.R
import com.example.kcchallenge.contacts.model.Contact
import com.example.kcchallenge.contacts.viewmodel.ContactListViewmodel
import kotlinx.android.synthetic.main.activity_contact_list.*

class ContactListActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(ContactListViewmodel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        setUpContactListRecycler()

        viewModel.contactList.observe(this) { contactDataItems ->
            (contact_list_recycler.adapter as ContactListAdapter).dataItems = contactDataItems
        }
    }

    private fun setUpContactListRecycler() {
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val itemDecoration = ContactDividerItemDecoration(this)
        contact_list_recycler.apply {
            this.layoutManager = layoutManager
            addItemDecoration(itemDecoration)
            contact_list_recycler.adapter = ContactListAdapter().apply {
                contactClickListener = {
                    goToContactDetailView(it)
                }
            }
        }
    }

    private fun goToContactDetailView(contact: Contact) {
        Toast.makeText(this, "This is ${contact.name}", Toast.LENGTH_SHORT).show()
    }

}