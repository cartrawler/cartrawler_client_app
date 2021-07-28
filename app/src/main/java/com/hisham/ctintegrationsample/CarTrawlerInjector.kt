package com.hisham.ctintegrationsample

import android.app.Activity
import androidx.appcompat.app.AppCompatDelegate
import cartrawler.core.base.USPDisplayType
import cartrawler.core.engine.CartrawlerSDK
import cartrawler.core.engine.CartrawlerSDKPassenger
import com.hisham.ctintegrationsample.palette.data.PaletteDetails
import java.util.*

object CarTrawlerInjector {

    const val REQUEST_CODE_STANDALONE = 123
    const val REQUEST_CODE_IN_PATH = 124

    fun initStandalone(
        activity: Activity,
        palette: PaletteDetails,
        currency: String,
        countryISO: String,
        environment: String,
        landingStyleType: USPDisplayType
    ) {
        val builder = CartrawlerSDK.Builder()

        enableCustomCashTreatment(activity, palette, builder)

        builder
            .setRentalStandAloneClientId(clientId(activity, palette))
            .setAccountId("CZ638817950")
            .setCountry(countryISO)
            .setCurrency(currency)
            .setEnvironment(environment)
            .setFlightNumberRequired(true)
            .setLogging(true)
            .setOrderId("123")
            .setPassenger(passenger(countryISO))
            .setVisitorId("123")
            .setTheme(getSelectedTheme(palette))
            .setDarkModeConfig(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            .setUSPDisplayType(landingStyleType)
            .startRentalStandalone(activity, REQUEST_CODE_STANDALONE)
    }

    fun initInPath(
        activity: Activity,
        palette: PaletteDetails,
        currency: String,
        countryISO: String,
        environment: String
    ) {
        val builder = CartrawlerSDK.Builder()

        enableCustomCashTreatment(activity, palette, builder)

        builder
            .setRentalInPathClientId(clientId(activity, palette))
            .setEnvironment(environment)
            .setCurrency(currency)
            .setCountry(countryISO)
            .setFlightNumberRequired(false)
            .setPassenger(passenger(countryISO))
            .setAccountId("CZ638817950")
            .setLogging(true)
            .setPickupTime(getPickUpDate())
            .setPickupLocation("DUB")
            .setDropOffLocationId(11)
            .setDropOffTime(getDropOffDate())
            .setTheme(getSelectedTheme(palette))
            .setDarkModeConfig(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            .startRentalInPath(activity, REQUEST_CODE_IN_PATH)
    }

    private fun clientId(
        activity: Activity,
        palette: PaletteDetails
    ) = activity.getString(palette.clientId)

    private fun getSelectedTheme(palette: PaletteDetails): Int {
        R.string.carTrawlerGeneric
        return when (palette.name) {
            R.string.carTrawlerGeneric -> R.style.GenericDarkTheme
            R.string.carTrawlerGenericLight -> R.style.GenericLightTheme
            R.string.partnerEasyJet -> R.style.EasyJetTheme
            R.string.partnereDreams -> R.style.EDreamsTheme
            R.string.partnerNorwegian -> R.style.NorwegianTheme
            R.string.partnerItaka -> R.style.ItakaTheme
            R.string.partnerGoVoyages -> R.style.GoVoyagesTheme
            R.string.partnerOpodo -> R.style.OpodoTheme
            R.string.partnerTravelStart -> R.style.TravelStartTheme
            R.string.partnerTravelLink -> R.style.TravellinkTheme
            R.string.partnerFinno -> R.style.FinnoTheme
            R.string.partnerTAP -> R.style.TAPTheme
            R.string.partnerArgus -> R.style.ArgusTheme
            R.string.partnerHolidayAutos -> R.style.HolidaysTheme
            R.string.partnereDreamsPrime -> R.style.EDreamsPrimeTheme
            else -> R.style.GenericDarkTheme
        }
    }

    private fun passenger(countryISO: String): CartrawlerSDKPassenger {
        return CartrawlerSDKPassenger(
            "John",
            "Smith",
            "john@example.com",
            "353",
            "81234567",
            "Dundrum Business Park",
            "Dublin",
            "D14 R7V2",
            countryISO,
            "EZY130",
            null,
            "123456")
    }

    private fun getPickUpDate(): GregorianCalendar {
        return (GregorianCalendar.getInstance() as GregorianCalendar).apply {
            add(GregorianCalendar.DAY_OF_MONTH, 7)
        }
    }

    private fun getDropOffDate(): GregorianCalendar {
        val rentalPeriodDays = 2

        val dropOfDateTime = getPickUpDate()
        dropOfDateTime.add(GregorianCalendar.DAY_OF_MONTH, rentalPeriodDays)
        return dropOfDateTime
    }

    private fun enableCustomCashTreatment(activity: Activity, palette: PaletteDetails, builder: CartrawlerSDK.Builder) {
        if (hasValidClientId(activity, palette)) {
            builder.enableCustomCashTreatment()
        }
    }

    private fun hasValidClientId(activity: Activity, palette: PaletteDetails): Boolean {
        return clientId(activity, palette) == activity.getString(R.string.partnereDreamsPrimeClientId)
    }
}