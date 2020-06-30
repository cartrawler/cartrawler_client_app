package com.hisham.ctintegrationsample.searchlist.countrylist

import com.hisham.ctintegrationsample.core.LocalStorage
import com.hisham.ctintegrationsample.searchlist.AbsSearchUseCase
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem
import java.util.*
import javax.inject.Inject

class CountryListUseCase @Inject constructor(storage: LocalStorage) : AbsSearchUseCase(storage) {

    override fun fetch(): List<SearchListItem> {
        val countryDataItems = mutableListOf<SearchListItem.Country>()
        val isoCountries = Locale.getISOCountries().asList()
        isoCountries.map {
            val locale = Locale("", it)
            val countryName = locale.displayCountry
            val data = SearchListItem.Country(countryName, it)
            countryDataItems.add(data)
        }
        return countryDataItems.toList().sortedBy { it.countryISO }
    }

}