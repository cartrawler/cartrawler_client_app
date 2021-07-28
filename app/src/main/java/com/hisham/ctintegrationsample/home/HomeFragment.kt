package com.hisham.ctintegrationsample.home

import android.os.Bundle
import android.view.*
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import cartrawler.core.base.USPDisplayType
import com.hisham.ctintegrationsample.BaseFragment
import com.hisham.ctintegrationsample.CarTrawlerInjector
import com.hisham.ctintegrationsample.R
import com.hisham.ctintegrationsample.core.LocalStorage
import com.hisham.ctintegrationsample.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    @Inject
    lateinit var localStorage: LocalStorage

    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarIcon(R.drawable.ic_app)

        val currency = localStorage.currency
        val countryISO = localStorage.country
        val environment = localStorage.environment

        val landingStyleType = if (localStorage.landingStyle == USPDisplayType.CHECK_STYLE.name) {
            USPDisplayType.CHECK_STYLE
        } else {
            USPDisplayType.DEFAULT_STYLE
        }

        binding.startStandaloneBtn.setOnClickListener {
            CarTrawlerInjector.initStandalone(
                requireActivity(),
                localStorage.palette,
                currency,
                countryISO,
                environment,
                landingStyleType
            )
        }

        binding.inPathBtn.setOnClickListener {
            CarTrawlerInjector.initInPath(
                requireActivity(),
                localStorage.palette,
                currency,
                countryISO,
                environment
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home, menu)
    }

    private val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.ct_enter_from_right)
        .build()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(item.itemId, null, navOptions)
        return true
    }
}