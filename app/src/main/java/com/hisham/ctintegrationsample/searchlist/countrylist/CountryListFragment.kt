package com.hisham.ctintegrationsample.searchlist.countrylist

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.hisham.ctintegrationsample.searchlist.BaseSearchFragment
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem
import com.hisham.ctintegrationsample.searchlist.views.SearchListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.search_list_view.*

@AndroidEntryPoint
class CountryListFragment: BaseSearchFragment() {

    private val viewModel: CountryListViewModel by viewModels()

    override fun initAdapter() {
        viewModel.init()
        viewModel.countriesObservable.observe(viewLifecycleOwner, Observer {
            val searchListAdapter = SearchListAdapter(it)
            searchListRecyclerView.apply {
                setHasFixedSize(true)
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                adapter = searchListAdapter
                searchListAdapter.onItemClickListener { item ->
                    if (item is SearchListItem.Country) {
                        viewModel.updateCountry(item.countryISO)
                        findNavController().navigateUp()
                    }
                }
            }
        })
    }

}