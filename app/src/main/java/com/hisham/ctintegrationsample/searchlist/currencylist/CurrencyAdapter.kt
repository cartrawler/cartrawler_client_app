package com.hisham.ctintegrationsample.searchlist.currencylist

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hisham.ctintegrationsample.R
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem
import com.hisham.ctintegrationsample.searchlist.views.DiffUtilsCallback
import com.hisham.ctintegrationsample.searchlist.AbsSearchAdapter
import kotlinx.android.synthetic.main.search_list_item_view.view.*
import java.util.*

class CurrencyAdapter(private val list: List<SearchListItem.Currency>) : AbsSearchAdapter<SearchListItem.Currency>(list) {

    override fun createViewHolder(view: View): RecyclerView.ViewHolder = CurrencyViewHolder((view))

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, item: SearchListItem.Currency) {
        (holder as CurrencyAdapter.CurrencyViewHolder).bind(item)
    }

    override fun filter(value: String) {
        super.filter(value)

        filteredList = list.filter {
            val currencyName = it.currencyName.toLowerCase(Locale.getDefault())
            val currencyISO = it.currencyISO.toLowerCase(Locale.getDefault())
            val filterValue = value.toLowerCase(Locale.getDefault())

            currencyName.contains(filterValue)
            currencyISO.contains(filterValue)
        }.toMutableList()

        DiffUtil.calculateDiff(DiffUtilsCallback(list, filteredList), false).dispatchUpdatesTo(this)
    }

    inner class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(currency: SearchListItem.Currency) {
            itemView.title.text = itemView.context.getString(
                R.string.currency_format,
                currency.currencyISO,
                currency.currencyName
            )
            itemView.setOnClickListener {
                itemCallback?.invoke(currency)
            }
        }
    }
}