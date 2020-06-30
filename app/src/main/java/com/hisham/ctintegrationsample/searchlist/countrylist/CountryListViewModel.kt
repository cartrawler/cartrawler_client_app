package com.hisham.ctintegrationsample.searchlist.countrylist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem
import com.hisham.ctintegrationsample.utils.asLiveData

class CountryListViewModel @ViewModelInject constructor(private val useCase: CountryListUseCase): ViewModel() {

    private val countriesLiveData = MutableLiveData<List<SearchListItem>>()

    val countriesObservable = countriesLiveData.asLiveData()

    fun init() {
        countriesLiveData.postValue(useCase.fetchCountries())
    }

    fun updateCountry(countryISO: String) {
        useCase.updateSelectedValue(countryISO)
    }

}