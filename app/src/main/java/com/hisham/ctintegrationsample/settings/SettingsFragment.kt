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
import com.hisham.ctintegrationsample.databinding.SettingsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : BaseFragment() {

    @Inject
    lateinit var localStorage: LocalStorage

    private lateinit var binding: SettingsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarIcon(0)

        val (name, _, primary, dark, accent) = localStorage.palette

        binding.themeView.editText?.apply {
            setText(name)
            setOnClickListener {
                it.findNavController()
                    .navigate(SettingsFragmentDirections.actionSettingsToPalettesFragment())
            }
        }

        binding.themePaletteView.apply(primary, dark, accent)

        val currencyISO = localStorage.currency
        val currency = Currency.getInstance(currencyISO)
        binding.currencyView.apply {
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
        binding.countryView.apply {
            editText?.apply {
                setText(country.displayName)
                setOnClickListener {
                    findNavController()
                        .navigate(SettingsFragmentDirections.actionSettingsToCountriesListFragment())
                }
            }
        }

        binding.changeLanguageTv.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }

        when(localStorage.environment) {
            CartrawlerSDK.Environment.PRODUCTION -> binding.environmentRG.check(R.id.productionRBtn)
            else -> binding.environmentRG.check(R.id.developmentRBtn)
        }

        binding.environmentRG.setOnCheckedChangeListener { _, checkedId ->
            localStorage.environment = when(checkedId) {
                R.id.productionRBtn -> CartrawlerSDK.Environment.PRODUCTION
                else -> CartrawlerSDK.Environment.STAGING
            }
        }
    }
}