package com.example.kcchallenge.contacts.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kcchallenge.R
import com.example.kcchallenge.contacts.model.Contact
import com.example.kcchallenge.contacts.viewmodel.ContactDetailViewModel
import kotlinx.android.synthetic.main.activity_contact_detail.*
import kotlinx.android.synthetic.main.checkbox_favorite.view.*

class ContactDetailActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(ContactDetailViewModel::class.java) }
    private val contact: Contact
        get() {
            return intent.getParcelableExtra(EXTRA_CONTACT)!!
        }
    private var contactEdited = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        setUpActionBar()
        showContactInfo()
        setUpContactDetailsRecycler()
        observeViewModel()
    }

    private fun showContactInfo() {
        contact_detail_image.setImageURI(contact.largeImageURL)
        contact_detail_name.text = contact.name
        contact_detail_company_name.text = contact.companyName
        viewModel.prepareContactDetails(contact)
    }

    private fun setUpContactDetailsRecycler() {
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val itemDecoration = ContactDividerItemDecoration(this)
        val adapter = ContactDetailsAdapter()
        contact_detail_recycler.apply {
            this.layoutManager = layoutManager
            this.adapter = adapter
            addItemDecoration(itemDecoration)
        }
    }

    private fun observeViewModel() {
        viewModel.contactDetails.observe(this) { contactDetailItems ->
            val adapter = contact_detail_recycler.adapter as ContactDetailsAdapter
            adapter.contactDetailItems = contactDetailItems
        }
    }

    private fun setUpActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.contact_details_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (menu != null) {
            val favoriteButtonItem = menu.findItem(R.id.menu_item_set_contact_favorite)
            val favoriteButton = favoriteButtonItem.actionView.checkbox_contact_favorite
            favoriteButton.isChecked = contact.isFavorite
            favoriteButton.setOnClickListener {
                favoriteButtonItem.isChecked = favoriteButton.isChecked
                onOptionsItemSelected(favoriteButtonItem)
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.menu_item_set_contact_favorite -> {
                updateContactFavorite(item.isChecked)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onBackPressed() {
        if (contactEdited) {
            val intent = Intent().apply {
                putExtra(EXTRA_CONTACT, contact)
            }
            setResult(RESULT_CONTACT_EDITED, intent)
        }
        super.onBackPressed()
    }

    private fun updateContactFavorite(isFavorite: Boolean) {
        contact.isFavorite = isFavorite
        contactEdited = true
    }

}