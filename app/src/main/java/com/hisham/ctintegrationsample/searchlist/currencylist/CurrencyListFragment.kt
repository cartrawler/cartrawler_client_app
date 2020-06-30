package com.hisham.ctintegrationsample.searchlist.currencylist

import androidx.fragment.app.viewModels
import com.hisham.ctintegrationsample.searchlist.AbsSearchFragment
import com.hisham.ctintegrationsample.searchlist.AbsSearchViewModel
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem
import com.hisham.ctintegrationsample.searchlist.AbsSearchAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyListFragment : AbsSearchFragment<SearchListItem.Currency>() {

    private val viewModel: CurrencyListViewModel by viewModels()

    override fun viewModel(): AbsSearchViewModel<SearchListItem.Currency> = viewModel

    override fun adapter(list: List<SearchListItem.Currency>): AbsSearchAdapter<SearchListItem.Currency> {
        return CurrencyAdapter(list)
    }
}