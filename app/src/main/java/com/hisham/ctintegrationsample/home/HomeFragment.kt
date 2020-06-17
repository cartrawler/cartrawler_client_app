package com.hisham.ctintegrationsample.home

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.hisham.ctintegrationsample.BaseFragment
import com.hisham.ctintegrationsample.BuildConfig
import com.hisham.ctintegrationsample.CarTrawlerInjector
import com.hisham.ctintegrationsample.R
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment() {

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

        startStandaloneBtn.setOnClickListener {
            CarTrawlerInjector.initStandalone(requireActivity())
        }

        inPathBtn.setOnClickListener {
            CarTrawlerInjector.initInPath(requireActivity())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if (BuildConfig.DEBUG) { // Until we finish settings page
            inflater.inflate(R.menu.home, menu)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, findNavController())
                || super.onOptionsItemSelected(item)
    }
}