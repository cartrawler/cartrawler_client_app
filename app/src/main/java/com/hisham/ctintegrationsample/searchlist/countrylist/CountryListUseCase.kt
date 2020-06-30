package com.hisham.ctintegrationsample.searchlist.countrylist

import com.hisham.ctintegrationsample.core.LocalStorage
import com.hisham.ctintegrationsample.searchlist.AbsSearchUseCase
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem
import java.util.*
import javax.inject.Inject

class CountryListUseCase @Inject constructor(localStorage: LocalStorage) :
    AbsSearchUseCase(localStorage) {

    override fun fetch(): List<SearchListItem> {
        val isoCountries = Locale.getISOCountries().asList()

        return isoCountries
            .map { SearchListItem.Country(countryName(it), it) }
            .toList()
            .sortedBy { it.countryISO }
    }

    private fun countryName(country: String): String {
        val locale = Locale("", country)
        return locale.displayCountry
    }
}
