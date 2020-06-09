package com.hisham.ctintegrationsample.palette.views

import androidx.core.content.ContextCompat
import com.hisham.ctintegrationsample.R
import com.hisham.ctintegrationsample.palette.data.PaletteDetails
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.palette_list_item.view.*

class PaletteItemView(private val paletteDetails: PaletteDetails) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            titleTv.text = context.getString(paletteDetails.name)
            themePaletteView.apply(
                ContextCompat.getColor(context, paletteDetails.primary),
                ContextCompat.getColor(context, paletteDetails.primaryDark),
                if (paletteDetails.accent != null) ContextCompat.getColor(context, paletteDetails.accent) else null
            )
        }
    }

    override fun getLayout(): Int = R.layout.palette_list_item

    override fun getSpanSize(spanCount: Int, position: Int): Int = spanCount / 2
}