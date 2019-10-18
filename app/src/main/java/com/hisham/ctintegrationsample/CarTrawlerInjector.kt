package com.hisham.ctintegrationsample

import android.app.Activity
import cartrawler.core.engine.CartrawlerSDK
import cartrawler.core.engine.CartrawlerSDKPassenger

object CarTrawlerInjector {

    const val REQUEST_CODE = 123

    fun init(activity: Activity) {
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
            .startRentalStandalone(activity, REQUEST_CODE)
    }

    private fun passenger(): CartrawlerSDKPassenger? {
        return CartrawlerSDKPassenger(
            "Hisham",
            "Gh",
            "hisham@ct.com",
            "353",
            "834736020",
            "23 Railway Close",
            "Dublin",
            "D13 AH90",
            "Ireland",
            "EZY130",
            null)
    }
}