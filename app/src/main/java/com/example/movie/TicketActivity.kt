package com.example.movie

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.booking_item)  // Reuse booking_item.xml as a ticket view

    // Get ticket details from the intent
    val movieTitle = intent.getStringExtra("MOVIE_TITLE") ?: "No Movie Selected"
    val bookingDate = intent.getStringExtra("BOOKING_DATE") ?: "Unknown Date"
    val selectedSeats = intent.getStringExtra("SELECTED_SEATS") ?: "No seats selected"

    // Find views in booking_item.xml
    val movieNameTextView = findViewById<TextView>(R.id.movieName)
    val bookingDateTextView = findViewById<TextView>(R.id.bookingDateTime)
    val seatNumbersTextView = findViewById<TextView>(R.id.seatNumbers)

    // Set the received values
    movieNameTextView.text = "Movie: $movieTitle"
    bookingDateTextView.text = "Date: $bookingDate"
    seatNumbersTextView.text = "Seats: $selectedSeats"
}
}