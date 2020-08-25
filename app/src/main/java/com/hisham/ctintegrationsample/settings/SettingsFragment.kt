package com.hisham.ctintegrationsample.settings

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import cartrawler.core.engine.CartrawlerSDK
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

        themeView.editText?.apply {
            setText(name)
            setOnClickListener {
                findNavController()
                    .navigate(SettingsFragmentDirections.actionSettingsToPalettesFragment())
            }
        }

        themePaletteView.apply(primary, dark, accent)

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

        changeLanguageTv.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }

        when(localStorage.environment) {
            CartrawlerSDK.Environment.PRODUCTION -> environmentRG.check(R.id.productionRBtn)
            else -> environmentRG.check(R.id.developmentRBtn)
        }

        environmentRG.setOnCheckedChangeListener { _, checkedId ->
            localStorage.environment = when(checkedId) {
                R.id.productionRBtn -> CartrawlerSDK.Environment.PRODUCTION
                else -> CartrawlerSDK.Environment.STAGING
            }
        }
    }
}