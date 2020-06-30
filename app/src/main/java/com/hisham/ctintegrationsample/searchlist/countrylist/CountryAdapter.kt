package com.hisham.ctintegrationsample.searchlist.countrylist

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem
import com.hisham.ctintegrationsample.searchlist.views.DiffUtilsCallback
import com.hisham.ctintegrationsample.searchlist.AbsSearchAdapter
import kotlinx.android.synthetic.main.search_list_item_view.view.*
import java.util.*

class CountryAdapter(private val list: List<SearchListItem.Country>) : AbsSearchAdapter<SearchListItem.Country>(list){

    override fun createViewHolder(view: View): RecyclerView.ViewHolder = CountryViewHolder(view)

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, item: SearchListItem.Country) {
        (holder as CountryViewHolder).bind(item)
    }

    override fun filter(value: String) {
        super.filter(value)

        filteredList = list.filter {
            it.countryName
                .toLowerCase(Locale.getDefault())
                .contains(value.toLowerCase(Locale.getDefault()))
        }.toMutableList()

        DiffUtil.calculateDiff(DiffUtilsCallback(list, filteredList), false).dispatchUpdatesTo(this)
    }

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(country: SearchListItem.Country) {
            itemView.title.text = country.countryName
            itemView.setOnClickListener {
                itemCallback?.invoke(country)
            }
        }
    }
}