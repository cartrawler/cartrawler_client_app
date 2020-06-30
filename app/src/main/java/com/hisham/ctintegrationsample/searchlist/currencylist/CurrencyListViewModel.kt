package com.hisham.ctintegrationsample.searchlist.currencylist

import androidx.hilt.lifecycle.ViewModelInject
import com.hisham.ctintegrationsample.searchlist.AbsSearchViewModel
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem

class CurrencyListViewModel @ViewModelInject constructor(useCase: CurrencyListUseCase): AbsSearchViewModel<SearchListItem.Currency>(useCase)