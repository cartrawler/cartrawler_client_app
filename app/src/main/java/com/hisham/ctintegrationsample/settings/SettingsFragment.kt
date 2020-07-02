package com.hisham.ctintegrationsample.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.hisham.ctintegrationsample.BaseFragment
import com.hisham.ctintegrationsample.R
import com.hisham.ctintegrationsample.core.LocalStorage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.settings_fragment.*
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : BaseFragment() {

    @Inject
    lateinit var localStorage: LocalStorage

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarIcon(0)

        val (name, _, primary, dark, accent) = localStorage.palette

        themeView.apply {
            value(getString(name))
            palette(primary, dark, accent)
            onClick {
                findNavController()
                    .navigate(SettingsFragmentDirections.actionSettingsToPalettesFragment())
            }
        }

        val currencyISO = localStorage.currency
        val currency = Currency.getInstance(currencyISO)
        currencyView.apply {
            editText?.apply {
                setText(currency.displayName)
                setOnClickListener {
                    findNavController()
                        .navigate(SettingsFragmentDirections.actionSettingsFragmentToCurrenciesListFragment())
                }
            }
        }

        val countryISO = localStorage.country
        val country = Locale("", countryISO)
        countryView.apply {
            editText?.apply {
                setText(country.displayName)
                setOnClickListener {
                    findNavController()
                        .navigate(SettingsFragmentDirections.actionSettingsToCountriesListFragment())
                }
            }
        }
    }
}