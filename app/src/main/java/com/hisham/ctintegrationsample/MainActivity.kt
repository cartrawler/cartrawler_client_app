package com.hisham.ctintegrationsample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import cartrawler.core.data.external.Payment
import cartrawler.core.data.external.ReservationDetails
import cartrawler.core.engine.CartrawlerSDK
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        title = ""
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == CarTrawlerInjector.REQUEST_CODE_STANDALONE) {
            when (requestCode) {
                CarTrawlerInjector.REQUEST_CODE_STANDALONE -> {
                    val reservationDetails =
                        data?.getParcelableExtra<ReservationDetails>(CartrawlerSDK.RESERVATION_DETAILS)
                    if (reservationDetails != null) {
                        handleResult("Reservation Details", reservationDetails.toString())
                    } else {
                        showToast("Failed to reserve!")
                    }
                }
                CarTrawlerInjector.REQUEST_CODE_IN_PATH -> {
                    val payment: Payment? = data?.getSerializableExtra(CartrawlerSDK.PAYMENT) as? Payment
                    if (payment != null) {
                        val gson = GsonBuilder().setPrettyPrinting().create()
                        val details = gson.toJson(payment)
                        handleResult("Payment", details)
                    } else {
                        showToast("InPath failed")
                    }
                }
            }
        }
    }

    private fun handleResult(title: String, message: String) {
        val dialog = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton("Cancel") { dialog, _ -> dialog?.dismiss() }
            .create()

        dialog.show()
    }

    private fun showToast(errorMessage: String) {
        Toast.makeText(this.applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
    }
}
