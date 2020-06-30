package com.hisham.ctintegrationsample.searchlist.currencylist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem
import com.hisham.ctintegrationsample.utils.asLiveData

class CurrencyListViewModel @ViewModelInject constructor(private val useCase: CurrencyListUseCase): ViewModel() {

    private val currencyListLiveData = MutableLiveData<List<SearchListItem>>()

    val currencyListObservable = currencyListLiveData.asLiveData()

    fun init() {
        currencyListLiveData.postValue(useCase.fetchCurrencies())
    }

    fun updateCurrency(currencyISO: String) {
        useCase.updateCurrency(currencyISO)
    }
}