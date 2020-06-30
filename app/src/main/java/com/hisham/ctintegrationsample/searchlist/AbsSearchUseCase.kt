package com.hisham.ctintegrationsample.searchlist

import com.hisham.ctintegrationsample.core.LocalStorage
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem

abstract class AbsSearchUseCase<T : SearchListItem>(private val localStorage: LocalStorage) {

    fun updateValue(value: T) {
        when (value) {
            is SearchListItem.Country -> localStorage.country = value.countryISO
            is SearchListItem.Currency -> localStorage.currency = value.currencyISO
        }
    }

    abstract fun fetch(): List<T>
}
