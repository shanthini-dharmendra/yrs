package com.example.movie

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    private var userRating = 0 // Default rating is 0

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Initialize movie posters
        val movieA = findViewById<ImageView>(R.id.movieA)
        val movieB = findViewById<ImageView>(R.id.movieB)
        val movieC = findViewById<ImageView>(R.id.movieC)
        val movieD = findViewById<ImageView>(R.id.movieD)

        // Register movies for context menu (long press to rate)
        registerForContextMenu(movieA)
        registerForContextMenu(movieB)
        registerForContextMenu(movieC)
        registerForContextMenu(movieD)

        // Set click listeners to open movie details
        movieA.setOnClickListener { openMovieDetails("Movie A Title", "Action | Adventure", "An exciting action-packed movie.", R.drawable.a) }
        movieB.setOnClickListener { openMovieDetails("Movie B Title", "Drama | Thriller", "A thrilling drama with suspense.", R.drawable.b) }
        movieC.setOnClickListener { openMovieDetails("Movie C Title", "Comedy | Family", "A fun-filled comedy for all ages.", R.drawable.c) }
        movieD.setOnClickListener { openMovieDetails("Movie D Title", "Sci-Fi | Fantasy", "A journey through time and space.", R.drawable.d) }
    }

    // Inflate the three-dot options menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    // Handle menu item clicks
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_list -> {
                Toast.makeText(this, "Opening List Page...", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity2::class.java))
                true
            }
            R.id.menu_about -> {
                Toast.makeText(this, "Opening About Page...", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, homeActivity::class.java)) // Now moves to activity_home.xml
                true
            }
            R.id.menu_history -> {
                Toast.makeText(this, "Opening History Page...", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, BookingHistoryActivity::class.java)) // Now moves to booking_item.xml
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Create Context Menu with 5-Star Rating Options (Long Press on Movie)
    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.setHeaderTitle("Rate this Movie")

        // Add 5-star rating options dynamically
        for (i in 1..5) {
            menu.add(0, i, 0, "⭐".repeat(i)) // Adds "⭐", "⭐⭐", "⭐⭐⭐", etc.
        }
    }

    // Handle User Selection from Context Menu
    override fun onContextItemSelected(item: MenuItem): Boolean {
        userRating = item.itemId // Store selected rating
        Toast.makeText(this, "You rated: ${"⭐".repeat(userRating)}", Toast.LENGTH_SHORT).show()
        return true
    }

    private fun openMovieDetails(title: String, genre: String, description: String, imageResId: Int) {
        val intent = Intent(this, ItemMovieActivity::class.java).apply {
            putExtra("MOVIE_TITLE", title)
            putExtra("MOVIE_GENRE", genre)
            putExtra("MOVIE_DESCRIPTION", description)
            putExtra("MOVIE_IMAGE", imageResId)
            putExtra("MOVIE_RATING", userRating) // Pass rating to next activity
        }
        startActivity(intent)
    }
}
