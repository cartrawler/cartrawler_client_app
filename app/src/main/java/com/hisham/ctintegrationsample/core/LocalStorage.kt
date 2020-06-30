package com.hisham.ctintegrationsample.core

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
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

    private fun savePalette(paletteDetails: PaletteDetails) {
        sharedPrefs.edit {
            putInt(KEY_THEME_NAME, paletteDetails.name)
            putInt(KEY_CLIENT_ID, paletteDetails.clientId)
            putInt(KEY_PRIMARY_COLOUR, paletteDetails.primary)
            putInt(KEY_PRIMARY_DARK_COLOUR, paletteDetails.primaryDark)
            paletteDetails.accent?.let { putInt(KEY_ACCENT_COLOUR, it) }
        }
    }

    private fun loadPalette(): PaletteDetails {
        return sharedPrefs.run {
            PaletteDetails(
                getInt(KEY_THEME_NAME, R.string.carTrawlerGeneric),
                getInt(KEY_CLIENT_ID, R.string.carTrawlerGenericClientId),
                getInt(KEY_PRIMARY_COLOUR, R.color.genericPrimary),
                getInt(KEY_PRIMARY_DARK_COLOUR, R.color.genericDarkPrimary),
                getInt(KEY_ACCENT_COLOUR, R.color.genericAccent)
            )
        }
    }

    fun saveCurrency(value: String) {
        sharedPrefs.edit {
            putString(KEY_CURRENCY_VALUE, value)
        }
    }

    fun currency(): String? = sharedPrefs.run {
            getString(KEY_CURRENCY_VALUE, "EUR")
    }

    fun countryISO(): String? = sharedPrefs.run {
        getString(KEY_COUNTRY_ISO_VALUE, "IE")
    }

    fun saveCountry(value: String) {
        sharedPrefs.edit {
            putString(KEY_COUNTRY_ISO_VALUE, value)
        }
    }

    private companion object {
        private const val CT_APP_SHARED_PREFS = "com.cartrawler.android.app"

        private const val KEY_THEME_NAME = "KEY_THEME_NAME"
        private const val KEY_CLIENT_ID = "KEY_CLIENT_ID"
        private const val KEY_PRIMARY_COLOUR = "KEY_PRIMARY_COLOUR"
        private const val KEY_PRIMARY_DARK_COLOUR = "KEY_PRIMARY_DARK_COLOUR"
        private const val KEY_ACCENT_COLOUR = "KEY_ACCENT_COLOUR"

        private const val KEY_CURRENCY_VALUE = "KEY_CURRENCY_VALUE"
        private const val KEY_COUNTRY_ISO_VALUE = "KEY_COUNTRY_ISO_VALUE"
    }
}