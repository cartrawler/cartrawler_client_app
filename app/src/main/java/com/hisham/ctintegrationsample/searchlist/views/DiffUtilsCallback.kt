package com.hisham.ctintegrationsample.searchlist.views

import androidx.recyclerview.widget.DiffUtil
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem

class DiffUtilsCallback(private val oldList: List<SearchListItem>, private val newList: List<SearchListItem>): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCountryList = oldList[oldItemPosition] as SearchListItem.Country
        val oldCurrencyList = oldList[oldItemPosition] as SearchListItem.Currency
        val newCountryList = newList[newItemPosition] as SearchListItem.Country
        val newCurrencyList = newList[newItemPosition] as SearchListItem.Currency
        val isCurrencySame = (oldCurrencyList.currencyName == newCurrencyList.currencyName) || (oldCurrencyList.currencyISO == newCurrencyList.currencyISO)
        val isCountrySame = oldCountryList.countryName == newCountryList.countryName
        return isCountrySame || isCurrencySame
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCountryRow = oldList[oldItemPosition] as SearchListItem.Country
        val oldCurrencyRow = oldList[oldItemPosition] as SearchListItem.Currency
        val newCountryRow = newList[newItemPosition] as SearchListItem.Country
        val newCurrencyRow = newList[newItemPosition] as SearchListItem.Currency

        return oldCountryRow == newCountryRow || oldCurrencyRow == newCurrencyRow
    }
}