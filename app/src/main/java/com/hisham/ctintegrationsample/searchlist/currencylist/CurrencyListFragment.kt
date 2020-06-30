package com.hisham.ctintegrationsample.searchlist.currencylist

import androidx.fragment.app.viewModels
import com.hisham.ctintegrationsample.searchlist.AbsSearchFragment
import com.hisham.ctintegrationsample.searchlist.AbsSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyListFragment : AbsSearchFragment() {

    private val viewModel: CurrencyListViewModel by viewModels()

    override fun viewModel(): AbsSearchViewModel = viewModel
}