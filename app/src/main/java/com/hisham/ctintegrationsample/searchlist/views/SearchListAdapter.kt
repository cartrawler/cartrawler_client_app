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
import kotlin.collections.ArrayList

class SearchListAdapter(private val list: List<SearchListItem>) : RecyclerView.Adapter<SearchListAdapter.SearchListViewHolder>() {

    var itemCallback: ((SearchListItem) -> Unit)? = null

    private var isFiltering: Boolean = false
    private var filteredList = ArrayList<SearchListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_list_item_view, parent, false)
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

        val newList = list.filter {
            val country = it as SearchListItem.Country
            val currency = it as SearchListItem.Currency
            val countryName = country.countryName.toLowerCase(Locale.getDefault())
            val currencyName = currency.currencyName.toLowerCase(Locale.getDefault())
            val currencyISO = currency.currencyISO.toLowerCase(Locale.getDefault())
            val filterValue = value.toLowerCase(Locale.getDefault())

            countryName.contains(filterValue)
            currencyName.contains(filterValue)
            currencyISO.contains(filterValue)
        } as ArrayList<SearchListItem>

        DiffUtil.calculateDiff(DiffUtilsCallback(list, newList), false).dispatchUpdatesTo(this)
        filteredList = newList
    }

    inner class SearchListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindCurrency(currency: SearchListItem.Currency) {
            itemView.title.text = itemView.context.getString(R.string.currency_format, currency.currencyISO, currency.currencyName)
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