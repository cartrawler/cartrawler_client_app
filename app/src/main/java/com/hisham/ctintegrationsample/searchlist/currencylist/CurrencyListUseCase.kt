package com.hisham.ctintegrationsample.searchlist.currencylist

import com.hisham.ctintegrationsample.core.LocalStorage
import com.hisham.ctintegrationsample.searchlist.AbsSearchUseCase
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem
import java.util.*
import javax.inject.Inject

class CurrencyListUseCase @Inject constructor(storage: LocalStorage) : AbsSearchUseCase(storage) {

    override fun fetch(): List<SearchListItem> {
        val currencies = Currency.getAvailableCurrencies().toList()
        val currencyDataItems = mutableListOf<SearchListItem.Currency>()
        currencies.map {
            val data = SearchListItem.Currency(it.displayName, it.currencyCode)
            currencyDataItems.add(data)
        }
        return currencyDataItems.toList().sortedBy { it.currencyISO }
    }

}