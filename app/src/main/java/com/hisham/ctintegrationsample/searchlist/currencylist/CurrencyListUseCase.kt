package com.hisham.ctintegrationsample.searchlist.currencylist

import com.hisham.ctintegrationsample.core.LocalStorage
import com.hisham.ctintegrationsample.searchlist.AbsSearchUseCase
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem
import java.util.*
import javax.inject.Inject

class CurrencyListUseCase @Inject constructor(storage: LocalStorage) : AbsSearchUseCase<SearchListItem.Currency>(storage) {

    override fun fetch(): List<SearchListItem.Currency> {
        val currencies = Currency.getAvailableCurrencies()

        return currencies
            .map { SearchListItem.Currency(it.displayName, it.currencyCode) }
            .toList()
            .sortedBy { it.currencyISO }
    }
}
