package com.hisham.ctintegrationsample.searchlist.countrylist

import com.hisham.ctintegrationsample.searchlist.AbsSearchViewModel
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    useCase: CountryListUseCase
) : AbsSearchViewModel<SearchListItem.Country>(useCase)
