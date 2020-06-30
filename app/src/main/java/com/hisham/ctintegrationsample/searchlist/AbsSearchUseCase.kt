package com.hisham.ctintegrationsample.searchlist

import cartrawler.core.utils.exhaustive
import com.hisham.ctintegrationsample.core.LocalStorage
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem

abstract class AbsSearchUseCase(val localStorage: LocalStorage) {

    fun updateValue(value: SearchListItem) {
        when(value) {
            is SearchListItem.Country ->localStorage.country = value.countryISO
            is SearchListItem.Currency -> localStorage.currency = value.currencyISO
        }.exhaustive
    }

    abstract fun fetch(): List<SearchListItem>
}