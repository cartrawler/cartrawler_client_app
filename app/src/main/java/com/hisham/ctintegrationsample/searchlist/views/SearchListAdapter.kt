package com.hisham.ctintegrationsample.searchlist.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hisham.ctintegrationsample.R
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem
import kotlinx.android.synthetic.main.search_list_item_view.view.*
import java.util.*

class SearchListAdapter(private val list: List<SearchListItem>) :
    RecyclerView.Adapter<SearchListAdapter.SearchListViewHolder>() {

    var itemCallback: ((SearchListItem) -> Unit)? = null

    private var isFiltering: Boolean = false
    private var filteredList = mutableListOf<SearchListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_list_item_view, parent, false)
        return SearchListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (isFiltering) {
            filteredList.size
        } else {
            list.size
        }
    }

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {
        val item = if (isFiltering) filteredList[position] else list[position]
        when (item) {
            is SearchListItem.Country -> {
                holder.bindCountry(item)
            }
            is SearchListItem.Currency -> {
                holder.bindCurrency(item)
            }
        }
    }

    fun onItemClickListener(callback: (SearchListItem) -> Unit) {
        itemCallback = callback
    }

    fun filterList(value: String) {
        isFiltering = true

        val currencyItemIndex = list.indexOfFirst { it is SearchListItem.Currency }
        val countryItemIndex = list.indexOfFirst { it is SearchListItem.Country }

        if (currencyItemIndex != -1) {
            filterCurrencies(value)
        }

        if (countryItemIndex != -1) {
            filterCountries(value)
        }

    }

    private fun filterCountries(value: String) {
        filteredList = list.filter {
            it as SearchListItem.Country
            it.countryName.toLowerCase(Locale.getDefault())
                .contains(value.toLowerCase(Locale.getDefault()))
        }.toMutableList()

        DiffUtil.calculateDiff(DiffUtilsCallback(list, filteredList), false).dispatchUpdatesTo(this)
    }

    private fun filterCurrencies(value: String) {
        filteredList = list.filter {
            val currency = it as SearchListItem.Currency
            val currencyName = currency.currencyName.toLowerCase(Locale.getDefault())
            val currencyISO = currency.currencyISO.toLowerCase(Locale.getDefault())
            val filterValue = value.toLowerCase(Locale.getDefault())

            currencyName.contains(filterValue)
            currencyISO.contains(filterValue)
        }.toMutableList()

        DiffUtil.calculateDiff(DiffUtilsCallback(list, filteredList), false).dispatchUpdatesTo(this)
    }

    inner class SearchListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindCurrency(currency: SearchListItem.Currency) {
            itemView.title.text = itemView.context.getString(
                R.string.currency_format,
                currency.currencyISO,
                currency.currencyName
            )
            itemView.setOnClickListener {
                itemCallback?.invoke(currency)
            }
        }

        fun bindCountry(country: SearchListItem.Country) {
            itemView.title.text = country.countryName
            itemView.setOnClickListener {
                itemCallback?.invoke(country)
            }
        }
    }
}