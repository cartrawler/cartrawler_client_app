package com.hisham.ctintegrationsample.searchlist.countrylist

import androidx.fragment.app.viewModels
import com.hisham.ctintegrationsample.searchlist.AbsSearchFragment
import com.hisham.ctintegrationsample.searchlist.AbsSearchViewModel
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem
import com.hisham.ctintegrationsample.searchlist.AbsSearchAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryListFragment : AbsSearchFragment<SearchListItem.Country>() {

    private val viewModel: CountryListViewModel by viewModels()

    override fun viewModel(): AbsSearchViewModel<SearchListItem.Country> = viewModel

    override fun adapter(list: List<SearchListItem.Country>): AbsSearchAdapter<SearchListItem.Country> {
        return CountryAdapter(list)
    }
}