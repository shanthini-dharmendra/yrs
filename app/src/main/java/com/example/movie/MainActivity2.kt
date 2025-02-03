package com.example.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val movieA = findViewById<ImageView>(R.id.movieA)
        val movieB = findViewById<ImageView>(R.id.movieB)
        val movieC = findViewById<ImageView>(R.id.movieC)

        movieA.setOnClickListener { openMovieDetails("Movie A Title", "Action | Adventure", "An exciting action-packed movie.", R.drawable.a) }
        movieB.setOnClickListener { openMovieDetails("Movie B Title", "Drama | Thriller", "A thrilling drama with suspense.", R.drawable.b) }
        movieC.setOnClickListener { openMovieDetails("Movie C Title", "Comedy | Family", "A fun-filled comedy for all ages.", R.drawable.c) }
    }

    private fun openMovieDetails(title: String, genre: String, description: String, imageResId: Int) {
        val intent = Intent(this, ItemMovieActivity::class.java).apply {
            putExtra("MOVIE_TITLE", title)
            putExtra("MOVIE_GENRE", genre)
            putExtra("MOVIE_DESCRIPTION", description)
            putExtra("MOVIE_IMAGE", imageResId)
        }
        startActivity(intent)
    }
}
