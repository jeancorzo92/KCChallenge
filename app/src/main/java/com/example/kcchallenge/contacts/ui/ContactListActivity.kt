package com.example.kcchallenge.contacts.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kcchallenge.R
import com.example.kcchallenge.contacts.model.Contact
import com.example.kcchallenge.contacts.viewmodel.ContactListViewModel
import com.example.kcchallenge.extensions.gone
import com.example.kcchallenge.extensions.visible
import kotlinx.android.synthetic.main.activity_contact_list.*

class ContactListActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(ContactListViewModel::class.java) }
    private val editContact = registerForActivityResult(ContactDetailsContract()) { contact ->
        if (contact != null) {
            viewModel.contactUpdated(contact)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        setUpContactListRecycler()
        viewModel.loadContacts()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.contactDataItems.observe(this) { contactDataItems ->
            (contact_list_recycler.adapter as ContactListAdapter).dataItems = contactDataItems
        }

        viewModel.showLoading.observe(this) { setVisible ->
            if (setVisible) {
                contact_list_loading_view.visible()
            } else {
                contact_list_loading_view.gone()
            }
        }

        viewModel.error.observe(this) { errorOccurred ->
            if (errorOccurred) {
                error_message.visible()
            }
        }
    }

    private fun setUpContactListRecycler() {
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val itemDecoration = ContactDividerItemDecoration(this)
        val adapter = ContactListAdapter()
        adapter.contactClickListener = {
            goToContactDetailView(it)
        }
        contact_list_recycler.apply {
            this.layoutManager = layoutManager
            addItemDecoration(itemDecoration)
            contact_list_recycler.adapter = adapter
        }
    }

    private fun goToContactDetailView(contact: Contact) {
        editContact.launch(contact)
    }
}

