package com.hisham.ctintegrationsample.searchlist.currencylist

import androidx.hilt.lifecycle.ViewModelInject
import com.hisham.ctintegrationsample.searchlist.AbsSearchViewModel

class CurrencyListViewModel @ViewModelInject constructor(useCase: CurrencyListUseCase): AbsSearchViewModel(useCase)