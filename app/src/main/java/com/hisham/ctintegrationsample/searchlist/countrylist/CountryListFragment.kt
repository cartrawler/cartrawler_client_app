package com.hisham.ctintegrationsample.searchlist.countrylist

import androidx.fragment.app.viewModels
import com.hisham.ctintegrationsample.searchlist.AbsSearchFragment
import com.hisham.ctintegrationsample.searchlist.AbsSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryListFragment : AbsSearchFragment() {

    private val viewModel: CountryListViewModel by viewModels()

    override fun viewModel(): AbsSearchViewModel = viewModel
}