package com.hisham.ctintegrationsample.searchlist.countrylist

import androidx.hilt.lifecycle.ViewModelInject
import com.hisham.ctintegrationsample.searchlist.AbsSearchViewModel

class CountryListViewModel @ViewModelInject constructor(useCase: CountryListUseCase): AbsSearchViewModel(useCase)