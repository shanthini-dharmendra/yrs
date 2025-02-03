package com.example.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BookingHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history)

        val bookingListContainer = findViewById<LinearLayout>(R.id.bookingListContainer)
        val downloadTicketButton = findViewById<Button>(R.id.buttonDownloadTicket)

        // Get the selected movie, booking date, and seats from the intent
        val movieTitle = intent.getStringExtra("MOVIE_TITLE") ?: "No Movie Selected"
        val bookingDate = intent.getStringExtra("BOOKING_DATE") ?: "Unknown Date"
        val selectedSeats = intent.getStringExtra("SELECTED_SEATS") ?: "No seats selected"

        // Clear previous bookings and show only the latest one
        bookingListContainer.removeAllViews()

        // Inflate the selected movie details into the booking history
        val bookingItem = LayoutInflater.from(this).inflate(R.layout.booking_item, bookingListContainer, false)
        val movieNameTextView = bookingItem.findViewById<TextView>(R.id.movieName)
        val bookingDateTextView = bookingItem.findViewById<TextView>(R.id.bookingDateTime)
        val seatNumbersTextView = bookingItem.findViewById<TextView>(R.id.seatNumbers)

        // Set the received values
        movieNameTextView.text = "Movie: $movieTitle"
        bookingDateTextView.text = "Date: $bookingDate"
        seatNumbersTextView.text = "Seats: $selectedSeats"

        bookingListContainer.addView(bookingItem)

        // Handle "Download Ticket" button click
        downloadTicketButton.setOnClickListener {
            val intent = Intent(this, TicketActivity::class.java).apply {
                putExtra("MOVIE_TITLE", movieTitle)
                putExtra("BOOKING_DATE", bookingDate)
                putExtra("SELECTED_SEATS", selectedSeats)
            }
            startActivity(intent)
        }
    }
}
