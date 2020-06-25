package com.hisham.ctintegrationsample.palette

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hisham.ctintegrationsample.palette.data.PaletteUiData
import com.hisham.ctintegrationsample.palette.views.PaletteHeaderItemView
import com.hisham.ctintegrationsample.palette.views.PaletteItemView

class PalettesAdapter(
    private val list: ArrayList<PaletteUiData>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var callback: ((Int, PaletteUiData.Item) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_ITEM) {
            ItemViewHolder(PaletteItemView(parent.context))
        } else {
            HeaderViewHolder(PaletteHeaderItemView(parent.context))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_ITEM) {
            (holder as ItemViewHolder).bind(position, list[position] as PaletteUiData.Item)
        } else {
            (holder as HeaderViewHolder).bind(list[position] as PaletteUiData.Header)
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return if (list[position] is PaletteUiData.Item) TYPE_ITEM else TYPE_HEADER
    }

    fun onItemClickListener(itemCallback: (Int, PaletteUiData.Item) -> Unit) {
        callback = itemCallback
    }

    fun update(position: Int, data: PaletteUiData.Item) {
        removeCurrentSelection()
        list[position] = data
        notifyItemChanged(position)
    }

    private fun removeCurrentSelection() {
        val pos = list.indexOfFirst { it is PaletteUiData.Item && it.isSelected }

        if (pos != -1) {
            val element = (list[pos] as PaletteUiData.Item).copy(isSelected = false)
            list[pos] = element
            notifyItemChanged(pos)
        }
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: PaletteUiData.Header) {
            (itemView as PaletteHeaderItemView).bind(data.title)
        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int, data: PaletteUiData.Item) {
            (itemView as PaletteItemView).bind(data)
            callback?.let { itemView.setOnClickListener { it(position, data) } }
        }
    }

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1
    }
}