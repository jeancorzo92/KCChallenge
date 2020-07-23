package com.example.kcchallenge.contacts.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.kcchallenge.R
import com.example.kcchallenge.extensions.dpToPx


class ContactDividerItemDecoration(context: Context) : ItemDecoration() {

    private val divider: Drawable = context.resources.getDrawable(R.drawable.contact_list_divider, context.theme)
    private val margin = 16.dpToPx()

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft + margin
        val right = parent.width - parent.paddingRight - margin
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child: View = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top: Int = child.bottom + params.bottomMargin
            val bottom = top + divider.intrinsicHeight
            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
    }

}