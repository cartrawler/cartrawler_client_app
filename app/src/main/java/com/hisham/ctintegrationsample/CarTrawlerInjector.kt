package com.hisham.ctintegrationsample

import android.app.Activity
import cartrawler.core.engine.CartrawlerSDK
import cartrawler.core.engine.CartrawlerSDKPassenger
import java.util.*

object CarTrawlerInjector {

    const val REQUEST_CODE_STANDALONE = 123
    const val REQUEST_CODE_IN_PATH = 124

    fun initStandalone(activity: Activity) {
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
            .setTheme(R.style.GenericTheme)
            .startRentalStandalone(activity, REQUEST_CODE_STANDALONE)
    }

    fun initInPath(activity: Activity) {
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
            .setTheme(R.style.GenericTheme)
            .startRentalInPath(activity, REQUEST_CODE_IN_PATH)
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
        val dayOfMonth = 11 // zero based
        val monthOffset = 11 // zero based

        val pickupDateTime = GregorianCalendar.getInstance() as GregorianCalendar

        pickupDateTime.add(GregorianCalendar.MONTH, monthOffset)
        pickupDateTime.set(GregorianCalendar.DAY_OF_MONTH, dayOfMonth)
        return pickupDateTime
    }

    private fun getDropOffDate(): GregorianCalendar {
        val rentalPeriodDays = 2
        val dayOfMonth = 11
        val monthOffset = 11

        val dropOfDateTime = GregorianCalendar.getInstance() as GregorianCalendar
        dropOfDateTime.add(GregorianCalendar.MONTH, monthOffset)
        dropOfDateTime.set(GregorianCalendar.DAY_OF_MONTH, dayOfMonth)
        dropOfDateTime.add(GregorianCalendar.DAY_OF_MONTH, rentalPeriodDays)
        return dropOfDateTime
    }
}