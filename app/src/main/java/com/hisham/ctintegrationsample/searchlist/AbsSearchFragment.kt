package com.hisham.ctintegrationsample.searchlist

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import com.hisham.ctintegrationsample.BaseFragment
import com.hisham.ctintegrationsample.R
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem
import kotlinx.android.synthetic.main.search_list_view.*
import kotlin.properties.Delegates


abstract class AbsSearchFragment<T : SearchListItem> : BaseFragment() {

    private var searchListAdapter: AbsSearchAdapter<T> by Delegates.notNull()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.search_list_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbarIcon(0)

        initAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search, menu)
        val searchItem = menu.findItem(R.id.searchAction)
        val searchView = searchItem.actionView as SearchView

        searchItem.expandActionView()

        searchView.clearFocus()

        searchItem.setOnActionExpandListener(object: MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return false
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                findNavController().navigateUp()
                return true
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(value: String?): Boolean {
                return updateFilterValue(value)
            }

            override fun onQueryTextChange(value: String?): Boolean {
                return updateFilterValue(value)
            }

            private fun updateFilterValue(value: String?): Boolean {
                value?.let { searchListAdapter.filter(it) }
                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun initAdapter() {
        viewModel().run {
            // Observe
            liveData.observe(viewLifecycleOwner, Observer {
                searchListRecyclerView.apply {
                    setHasFixedSize(true)
                    addItemDecoration(
                        DividerItemDecoration(
                            context,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                    searchListAdapter = adapter(it)
                    adapter = searchListAdapter
                    searchListAdapter.apply {
                        onItemClickListener { item ->
                            update(item)
                            findNavController().navigateUp()
                        }
                    }
                }
            })

            fetchList()
        }
    }

    abstract fun viewModel(): AbsSearchViewModel<T>

    abstract fun adapter(list: List<T>): AbsSearchAdapter<T>
}