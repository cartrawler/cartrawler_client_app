package com.hisham.ctintegrationsample.palette.views

import com.hisham.ctintegrationsample.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.palette_list_header_item.view.*

class PaletteHeaderItemView(private val title: String) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.paletteHeaderTv.text = title
    }

    override fun getLayout(): Int = R.layout.palette_list_header_item
}