package com.example.movie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val movieSummaryText = findViewById<TextView>(R.id.movieSummary)
        val seatSummaryText = findViewById<TextView>(R.id.seatsSummary)
        val paymentMethodGroup = findViewById<RadioGroup>(R.id.paymentMethodGroup)
        val completePaymentButton = findViewById<Button>(R.id.completePaymentButton)

        // Get selected movie and seat details from the previous screen
        val movieTitle = intent.getStringExtra("MOVIE_TITLE") ?: "Unknown Movie"
        val selectedSeats = intent.getStringExtra("SELECTED_SEATS") ?: "No seats selected"

        // Get the current date (booking date)
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        // Display selected details
        movieSummaryText.text = "Movie: $movieTitle"
        seatSummaryText.text = "Seats Selected: $selectedSeats"

        // Handle payment button click
        completePaymentButton.setOnClickListener {
            val selectedPaymentId = paymentMethodGroup.checkedRadioButtonId
            if (selectedPaymentId == -1) {
                Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Send movie, date, and seat details to Booking History
            val intent = Intent(this, BookingHistoryActivity::class.java).apply {
                putExtra("MOVIE_TITLE", movieTitle)
                putExtra("BOOKING_DATE", currentDate)
                putExtra("SELECTED_SEATS", selectedSeats)
            }
            startActivity(intent)
            finish()
        }
    }
}
