package com.example.movie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.gridlayout.widget.GridLayout

class MainActivity3 : AppCompatActivity() {
    private val selectedSeats = mutableListOf<Int>()
    private val seatPrice = 100  // Price per seat
    private lateinit var selectedSeatsText: TextView
    private lateinit var totalPriceText: TextView
    private lateinit var seatButtons: MutableList<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        selectedSeatsText = findViewById(R.id.selectedSeatsText)
        totalPriceText = findViewById(R.id.totalPrice)
        val seatGrid = findViewById<GridLayout>(R.id.seatGrid)
        val submitButton = findViewById<Button>(R.id.submitButton)

        // Create seats dynamically (1 to 20)
        seatButtons = mutableListOf()
        for (i in 1..20) {
            val seatButton = Button(this).apply {
                text = i.toString()
                setBackgroundResource(R.drawable.seat_selector) // Apply selector
                setTextColor(resources.getColor(android.R.color.black))
                width = 120
                height = 120
                textSize = 16f
                setPadding(8, 8, 8, 8)
                setOnClickListener { toggleSeatSelection(this, i) }
            }
            seatButtons.add(seatButton)
            seatGrid.addView(seatButton)
        }

        // Confirm Booking Button Click
        submitButton.setOnClickListener {
            val seatNumbers = selectedSeats.joinToString(", ")
            val totalAmount = selectedSeats.size * seatPrice
            val movieTitle = intent.getStringExtra("MOVIE_TITLE") ?: "Unknown Movie"

            // Open MainActivity4 and pass seat selection details
            val intent = Intent(this, MainActivity4::class.java).apply {
                putExtra("MOVIE_TITLE", movieTitle)
                putExtra("SELECTED_SEATS", seatNumbers)
                putExtra("TOTAL_AMOUNT", totalAmount)
            }
            startActivity(intent)
        }

    }

    private fun toggleSeatSelection(seat: Button, seatNumber: Int) {
        if (selectedSeats.contains(seatNumber)) {
            selectedSeats.remove(seatNumber)
            seat.isSelected = false
        } else {
            selectedSeats.add(seatNumber)
            seat.isSelected = true
        }

        val seatNumbers = if (selectedSeats.isEmpty()) "None" else selectedSeats.joinToString(", ")
        selectedSeatsText.text = "Selected Seats: $seatNumbers"
        totalPriceText.text = "Total Price: ₹${selectedSeats.size * seatPrice}"
    }
}
