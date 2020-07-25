package com.example.kcchallenge.contacts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.example.kcchallenge.R
import com.example.kcchallenge.contacts.model.Contact
import com.example.kcchallenge.contacts.model.ContactListDataItem
import kotlinx.android.synthetic.main.contact_list_group_header.view.*
import kotlinx.android.synthetic.main.contact_list_item.view.*

typealias ContactClickListener = (Contact) -> Unit

class ContactListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewTypeHeader = 0
    private val viewTypeContact = 1
    var contactClickListener: ContactClickListener = {}

    var dataItems = listOf<ContactListDataItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            viewTypeHeader -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_list_group_header, parent, false)
                HeaderViewHolder(view)
            }
            viewTypeContact -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_list_item, parent, false)
                ContactViewHolder(view)
            }
            else -> throw Exception("Unknown View type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                val headerItem = dataItems[position] as ContactListDataItem.Header
                holder.bind(headerItem.title)
            }
            is ContactViewHolder -> {
                val contactItem = dataItems[position] as ContactListDataItem.ContactItem
                holder.bind(contactItem.contact)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataItems[position]) {
            is ContactListDataItem.Header -> viewTypeHeader
            is ContactListDataItem.ContactItem -> viewTypeContact
        }
    }

    override fun getItemCount() = dataItems.size

    inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(@StringRes title: Int) {
            itemView.contact_group_header_title.setText(title)
        }
    }

    inner class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(contact: Contact) {
            itemView.contact_name.text = contact.name
            itemView.contact_company_name.text = contact.companyName
            itemView.contact_favorite.text = if (contact.isFavorite) itemView.resources.getString(R.string.star_emoji) else ""
            itemView.contact_image.setImageURI(contact.smallImageURL)
            itemView.setOnClickListener { contactClickListener(contact) }
        }
    }
}



