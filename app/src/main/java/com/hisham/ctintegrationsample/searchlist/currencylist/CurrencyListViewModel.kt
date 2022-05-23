package com.hisham.ctintegrationsample.searchlist.currencylist

import com.hisham.ctintegrationsample.searchlist.AbsSearchViewModel
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    useCase: CurrencyListUseCase
) : AbsSearchViewModel<SearchListItem.Currency>(useCase)
