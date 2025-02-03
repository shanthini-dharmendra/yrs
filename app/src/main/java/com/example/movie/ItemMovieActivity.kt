package com.example.movie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ItemMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_moive)

        val movieImage = findViewById<ImageView>(R.id.movieImage)
        val movieTitle = findViewById<TextView>(R.id.movieTitle)
        val movieGenre = findViewById<TextView>(R.id.movieGenre)
        val movieDescription = findViewById<TextView>(R.id.movieDescription)
        val bookTicketsButton = findViewById<Button>(R.id.bookTicketsButton)

        // Get movie details from Intent
        val title = intent.getStringExtra("MOVIE_TITLE") ?: "Unknown Movie"
        val genre = intent.getStringExtra("MOVIE_GENRE") ?: "Unknown Genre"
        val description = intent.getStringExtra("MOVIE_DESCRIPTION") ?: "No description available."
        val imageResId = intent.getIntExtra("MOVIE_IMAGE", R.drawable.d)

        // Set UI elements
        movieTitle.text = title
        movieGenre.text = genre
        movieDescription.text = description
        movieImage.setImageResource(imageResId)

        // Navigate to seat selection (`MainActivity3`)
        bookTicketsButton.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            intent.putExtra("MOVIE_TITLE", title) // Pass movie title to seat selection
            startActivity(intent)
        }
    }
}
