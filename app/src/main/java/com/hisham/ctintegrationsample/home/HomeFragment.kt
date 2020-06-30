package com.hisham.ctintegrationsample.home

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.hisham.ctintegrationsample.BaseFragment
import com.hisham.ctintegrationsample.CarTrawlerInjector
import com.hisham.ctintegrationsample.R
import com.hisham.ctintegrationsample.core.LocalStorage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    @Inject
    lateinit var localStorage: LocalStorage

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarIcon(R.drawable.ic_app)

        val currency = localStorage.currency() ?: "EUR"
        val countryISO = localStorage.countryISO() ?: "IE"

        startStandaloneBtn.setOnClickListener {
            CarTrawlerInjector.initStandalone(requireActivity(), localStorage.palette, currency, countryISO)
        }

        inPathBtn.setOnClickListener {
            CarTrawlerInjector.initInPath(requireActivity(), localStorage.palette, currency, countryISO)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, findNavController())
                || super.onOptionsItemSelected(item)
    }
}