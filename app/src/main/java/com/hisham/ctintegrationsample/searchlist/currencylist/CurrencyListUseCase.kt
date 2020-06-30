package com.hisham.ctintegrationsample.searchlist.currencylist

import com.hisham.ctintegrationsample.core.LocalStorage
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem
import java.util.*
import javax.inject.Inject

class CurrencyListUseCase @Inject constructor(private val localStorage: LocalStorage) {

    fun updateCurrency(value: String) {
        localStorage.saveCurrency(value)
    }

    fun fetchCurrencies(): List<SearchListItem.Currency> {
        val currencies = Currency.getAvailableCurrencies().toList()
        val currencyDataItems = mutableListOf<SearchListItem.Currency>()
        currencies.map {
            val data = SearchListItem.Currency(it.displayName, it.currencyCode)
            currencyDataItems.add(data)
        }
        return currencyDataItems.toList().sortedBy { it.currencyISO }
    }

}