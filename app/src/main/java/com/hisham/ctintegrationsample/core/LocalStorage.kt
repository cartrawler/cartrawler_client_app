package com.hisham.ctintegrationsample.core

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import cartrawler.core.base.USPDisplayType
import cartrawler.core.engine.CartrawlerSDK
import com.hisham.ctintegrationsample.R
import com.hisham.ctintegrationsample.palette.data.PaletteDetails
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalStorage @Inject constructor(@ApplicationContext private val context: Context) {

    private val sharedPrefs: SharedPreferences by lazy {
        context.getSharedPreferences(CT_APP_SHARED_PREFS, Context.MODE_PRIVATE)
    }

    var palette: PaletteDetails
        get() = loadPalette()
        set(value) = savePalette(value)

    var country: String
        get() = countryISO()
        set(value) = saveCountry(value)

    var currency: String
        get() = currency()
        set(value) = saveCurrency(value)

    var environment: String
        get() = environment()
        set(value) = saveEnvironment(value)

    var landingStyle: String
        get() = landingStyle()
        set(value) = saveLandingStyle(value)

    private fun savePalette(paletteDetails: PaletteDetails) {
        sharedPrefs.edit {
            putString(KEY_THEME_NAME, context.getResourceNameById(paletteDetails.name))
            putString(KEY_CLIENT_ID, context.getResourceNameById(paletteDetails.clientId))
            putString(KEY_PRIMARY_COLOUR, context.getResourceNameById(paletteDetails.primary))
            putString(KEY_PRIMARY_DARK_COLOUR, context.getResourceNameById(paletteDetails.primaryDark))
            paletteDetails.accent?.let { putString(KEY_ACCENT_COLOUR, context.getResourceNameById(it)) }
        }
    }

    private fun loadPalette(): PaletteDetails {
        return with(sharedPrefs) {
            with(context) {
                val defaultTheme = getResourceNameById(R.string.carTrawlerGeneric)
                val defaultClientId = getResourceNameById(R.string.carTrawlerGenericClientId)
                val defaultPrimaryColour = getResourceNameById(R.color.genericPrimary)
                val defaultPrimaryDarkColour = getResourceNameById(R.color.genericDarkPrimary)
                val defaultAccentColour = getResourceNameById(R.color.genericAccent)

                PaletteDetails(
                    getStringIdByName(getStringOrDefault(KEY_THEME_NAME, defaultTheme)),
                    getStringIdByName(getStringOrDefault(KEY_CLIENT_ID, defaultClientId)),
                    getColourIdByName(getStringOrDefault(KEY_PRIMARY_COLOUR, defaultPrimaryColour)),
                    getColourIdByName(getStringOrDefault(KEY_PRIMARY_DARK_COLOUR, defaultPrimaryDarkColour)),
                    getColourIdByName(getStringOrDefault(KEY_ACCENT_COLOUR, defaultAccentColour))
                )
            }
        }
    }

    private fun saveCurrency(value: String) = with(sharedPrefs) {
        edit { putString(KEY_CURRENCY_VALUE, value) }
    }

    private fun currency(): String = with(sharedPrefs) {
        getString(KEY_CURRENCY_VALUE, "EUR") ?: "EUR"
    }

    private fun countryISO(): String = with(sharedPrefs) {
        getString(KEY_COUNTRY_ISO_VALUE, "IE") ?: "IE"
    }

    private fun saveCountry(value: String) = with(sharedPrefs) {
        edit { putString(KEY_COUNTRY_ISO_VALUE, value) }
    }

    private fun environment(): String = with(sharedPrefs) {
        getString(KEY_ENVIRONMENT, CartrawlerSDK.Environment.STAGING)
            ?: CartrawlerSDK.Environment.STAGING
    }

    private fun saveEnvironment(value: String) = with(sharedPrefs) {
        edit { putString(KEY_ENVIRONMENT, value) }
    }

    private fun landingStyle(): String = with(sharedPrefs) {
        getString(KEY_LANDING_STYLE_TYPE, USPDisplayType.DEFAULT_STYLE.name) ?: USPDisplayType.DEFAULT_STYLE.name
    }

    private fun saveLandingStyle(value: String) = with(sharedPrefs) {
        edit { putString(KEY_LANDING_STYLE_TYPE, value) }
    }

    private companion object {
        private const val CT_APP_SHARED_PREFS = "com.cartrawler.android.app"

        private const val KEY_THEME_NAME = "KEY_THEME_NAME2"
        private const val KEY_CLIENT_ID = "KEY_CLIENT_ID2"
        private const val KEY_PRIMARY_COLOUR = "KEY_PRIMARY_COLOUR2"
        private const val KEY_PRIMARY_DARK_COLOUR = "KEY_PRIMARY_DARK_COLOUR2"
        private const val KEY_ACCENT_COLOUR = "KEY_ACCENT_COLOUR2"

        private const val KEY_CURRENCY_VALUE = "KEY_CURRENCY_VALUE"
        private const val KEY_COUNTRY_ISO_VALUE = "KEY_COUNTRY_ISO_VALUE"
        private const val KEY_ENVIRONMENT = "KEY_ENVIRONMENT"
        private const val KEY_LANDING_STYLE_TYPE = "KEY_LANDING_STYLE_TYPE"
    }
}