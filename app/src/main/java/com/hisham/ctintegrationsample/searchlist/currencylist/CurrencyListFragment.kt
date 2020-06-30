package com.hisham.ctintegrationsample.searchlist.currencylist

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
class CurrencyListFragment : BaseSearchFragment() {

    private val viewModel: CurrencyListViewModel by viewModels()

    override fun initAdapter() {
        viewModel.init()
        viewModel.currencyListObservable.observe(viewLifecycleOwner, Observer {
            val searchListAdapter = SearchListAdapter(it)
            searchListRecyclerView.apply {
                setHasFixedSize(true)
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                adapter = searchListAdapter
                searchListAdapter.onItemClickListener { item ->
                    if (item is SearchListItem.Currency) {
                        viewModel.updateCurrency(item.currencyISO)
                        findNavController().navigateUp()
                    }
                }
            }
        })
    }
}