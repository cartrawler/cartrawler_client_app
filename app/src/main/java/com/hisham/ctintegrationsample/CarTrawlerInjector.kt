package com.hisham.ctintegrationsample

import android.app.Activity
import cartrawler.core.engine.CartrawlerSDK
import cartrawler.core.engine.CartrawlerSDKPassenger
import com.hisham.ctintegrationsample.palette.data.PaletteDetails
import java.util.*

object CarTrawlerInjector {

    const val REQUEST_CODE_STANDALONE = 123
    const val REQUEST_CODE_IN_PATH = 124

    fun initStandalone(activity: Activity, palette: PaletteDetails) {
        CartrawlerSDK.Builder()
            .setRentalStandAloneClientId("512434")  // eJ
            .setAccountId("CZ638817950")
            .setCountry("IE")
            .setCurrency("EUR")
            .setEnvironment(CartrawlerSDK.Environment.STAGING)
            .setFlightNumberRequired(true)
            .setLogging(true)
            .setLoyalty("WEST_JET", "WESTJET_REWARDS")
            .setOrderId("123")
            .setPassenger(passenger())
            .setVisitorId("123")
            .setTheme(getSelectedTheme(palette))
            .startRentalStandalone(activity, REQUEST_CODE_STANDALONE)
    }

    fun initInPath(activity: Activity, palette: PaletteDetails) {
        CartrawlerSDK.Builder()
            .setRentalInPathClientId("512434")
            .setEnvironment(CartrawlerSDK.Environment.STAGING)
            .setCurrency("EUR")
            .setFlightNumberRequired(false)
            .setAccountId("CZ638817950")
            .setLogging(true)
            .setPickupTime(getPickUpDate())
            .setPickupLocation("DUB")
            .setDropOffLocationId(11)
            .setDropOffTime(getDropOffDate())
            .setTheme(getSelectedTheme(palette))
            .startRentalInPath(activity, REQUEST_CODE_IN_PATH)
    }

    private fun getSelectedTheme(palette: PaletteDetails): Int {
        R.string.carTrawlerGeneric
        return when (palette.name) {
            R.string.carTrawlerGeneric -> R.style.GenericTheme
            R.string.carTrawlerGenericLight -> R.style.GenericLightTheme
            R.string.partnerEasyJet -> R.style.EasyJetTheme
            R.string.partnereDreams -> R.style.EDreamsTheme
            R.string.partnerNorwegian -> R.style.NorwegianTheme
            R.string.partnerItaka -> R.style.ItakaTheme
            R.string.partnerGoVoyages -> R.style.GoVoyagesTheme
            R.string.partnerOpodo -> R.style.OpodoTheme
            else -> R.style.GenericTheme
        }
    }

    private fun passenger(): CartrawlerSDKPassenger? {
        return CartrawlerSDKPassenger(
            "John",
            "Smith",
            "john@example.com",
            "353",
            "81234567",
            "Dundrum Business Park",
            "Dublin",
            "D14 R7V2",
            "IE",
            "EZY130",
            "29")
    }

    private fun getPickUpDate(): GregorianCalendar {
        return GregorianCalendar.getInstance() as GregorianCalendar
    }

    private fun getDropOffDate(): GregorianCalendar {
        val rentalPeriodDays = 2

        val dropOfDateTime = GregorianCalendar.getInstance() as GregorianCalendar
        dropOfDateTime.add(GregorianCalendar.DAY_OF_MONTH, rentalPeriodDays)
        return dropOfDateTime
    }
}