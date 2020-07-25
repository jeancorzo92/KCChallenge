package com.example.kcchallenge.contacts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kcchallenge.R
import com.example.kcchallenge.contacts.model.ContactDetailDataItem
import kotlinx.android.synthetic.main.contact_detail_item.view.*

class ContactDetailsAdapter : RecyclerView.Adapter<ContactDetailsAdapter.ContactDetailsViewHolder>() {

    var contactDetailItems: List<ContactDetailDataItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactDetailsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_detail_item, parent, false)
        return ContactDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactDetailsViewHolder, position: Int) {
        holder.bind(contactDetailItems[position])
    }

    override fun getItemCount() = contactDetailItems.size

    class ContactDetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(dataItem: ContactDetailDataItem) {
            val name = itemView.resources.getString(dataItem.valueName)
            itemView.contact_detail_item_value.text = dataItem.value
            itemView.contact_detail_item_name.text = itemView.resources.getString(R.string.contact_detail_item_label_format, name)
            itemView.contact_detail_item_description.text = itemView.resources.getString(dataItem.valueDescription)
        }

    }

}
