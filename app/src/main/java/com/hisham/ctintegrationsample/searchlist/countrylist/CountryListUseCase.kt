package com.hisham.ctintegrationsample.searchlist.countrylist

import com.hisham.ctintegrationsample.core.LocalStorage
import com.hisham.ctintegrationsample.searchlist.AbsSearchUseCase
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem
import java.util.*
import javax.inject.Inject

class CountryListUseCase @Inject constructor(
    localStorage: LocalStorage
) : AbsSearchUseCase<SearchListItem.Country>(localStorage) {

    override fun fetch(): List<SearchListItem.Country> {
        val isoCountries = Locale.getISOCountries()

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
