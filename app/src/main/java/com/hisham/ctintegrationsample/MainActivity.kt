package com.hisham.ctintegrationsample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import cartrawler.core.data.external.ReservationDetails
import cartrawler.core.engine.CartrawlerSDK
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startStandaloneBtn.setOnClickListener {
            CarTrawlerInjector.init(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == CarTrawlerInjector.REQUEST_CODE) {
            val reservationDetails = data?.getParcelableExtra<ReservationDetails>(CartrawlerSDK.RESERVATION_DETAILS)
            if (reservationDetails != null) {
                handleCTResult(reservationDetails)
            } else {
                showToast("Failed to reserve!")
            }
        }
    }

    private fun handleCTResult(reservationDetails: ReservationDetails) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Reservation Details")
            .setMessage(reservationDetails.toString())
            .setNegativeButton("Cancel") { dialog, _ -> dialog?.dismiss() }
            .create()

        dialog.show()
    }

    private fun showToast(errorMessage: String) {
        Toast.makeText(this.applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
    }
}
