package com.hisham.ctintegrationsample.searchlist.data

sealed class SearchListItem {
    data class Currency(val currencyName: String, val currencyISO: String): SearchListItem()
    data class Country(val countryName: String, val countryISO: String): SearchListItem()
}