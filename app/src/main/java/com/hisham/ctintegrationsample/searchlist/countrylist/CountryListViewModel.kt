package com.hisham.ctintegrationsample.searchlist.countrylist

import androidx.hilt.lifecycle.ViewModelInject
import com.hisham.ctintegrationsample.searchlist.AbsSearchViewModel
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem

class CountryListViewModel @ViewModelInject constructor(useCase: CountryListUseCase): AbsSearchViewModel<SearchListItem.Country>(useCase)