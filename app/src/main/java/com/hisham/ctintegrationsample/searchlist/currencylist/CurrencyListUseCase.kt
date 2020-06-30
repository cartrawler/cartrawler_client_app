package com.hisham.ctintegrationsample.searchlist.currencylist

import com.hisham.ctintegrationsample.core.LocalStorage
import com.hisham.ctintegrationsample.searchlist.AbsSearchUseCase
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem
import java.util.*
import javax.inject.Inject

class CurrencyListUseCase @Inject constructor(storage: LocalStorage) : AbsSearchUseCase<SearchListItem.Currency>(storage) {

    override fun fetch(): List<SearchListItem.Currency> {
        val currencies = CurrenciesFactory.currencies()

        return currencies
            .map { SearchListItem.Currency(currencyName(it), it) }
            .toList()
            .sortedBy { it.currencyISO }
    }

    private fun currencyName(currency: String): String {
        return Currency.getInstance(currency).displayName ?: ""
    }
}

object CurrenciesFactory {

    fun currencies(): Set<String> {
        return setOf(
            "EUR",
            "GBP",
            "USD",
            "AED",
            "ALL",
            "ANG",
            "AOA",
            "ARS",
            "AUD",
            "BAM",
            "BBD",
            "BDT",
            "BGN",
            "BHD",
            "BMD",
            "BND",
            "BOB",
            "BRL",
            "BSD",
            "BWP",
            "CAD",
            "CHF",
            "CLP",
            "CNY",
            "COP",
            "CRC",
            "CUP",
            "CVE",
            "CZK",
            "DKK",
            "DOP",
            "DZD",
            "EEK",
            "EGP",
            "ERN",
            "EUR",
            "FJD",
            "GBP",
            "GHS",
            "GNF",
            "GTQ",
            "HKD",
            "HNL",
            "HRK",
            "HUF",
            "IDR",
            "ILS",
            "INR",
            "IRR",
            "ISK",
            "JMD",
            "JOD",
            "JPY",
            "KES",
            "KRW",
            "KWD",
            "KZT",
            "LBP",
            "LKR",
            "LSL",
            "LTL",
            "LYD",
            "MAD",
            "MGA",
            "MMK",
            "MOP",
            "MUR",
            "MWK",
            "MXN",
            "MYR",
            "MZN",
            "NAD",
            "NGN",
            "NOK",
            "NZD",
            "OMR",
            "PEN",
            "PHP",
            "PKR",
            "PLN",
            "QAR",
            "RON",
            "RSD",
            "RUB",
            "SAR",
            "SDG",
            "SEK",
            "SGD",
            "SZL",
            "THB",
            "TND",
            "TRY",
            "TTD",
            "TWD",
            "TZS",
            "UAH",
            "UGX",
            "USD",
            "UYU",
            "VEB",
            "VEF",
            "VND",
            "ZAR",
            "ZMK",
            "ZWD"
        )
    }
}
