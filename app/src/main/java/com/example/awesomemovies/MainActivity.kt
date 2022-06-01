package com.example.awesomemovies

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var movieListView = findViewById<RecyclerView>(R.id.movieList)
        movieListView.layoutManager = LinearLayoutManager(this)
        val movieListAdapter = MovieListAdapter()
        movieListView.adapter = movieListAdapter

        var searchQueryText = findViewById<AppCompatEditText>(R.id.searchQuery)
        val movieApi = MovieApiClient.getRetrofit().create(MovieApi::class.java)
        var searchButton = findViewById<AppCompatButton>(R.id.searchButton)
        searchButton.setOnClickListener {
            val searchQuery = searchQueryText.text.toString()
            if (searchQuery.isNullOrBlank()) {
                Toast.makeText(
                    this,
                    "search cannot be empty. Minimum 3 characters are required.",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (searchQuery.length < 3) {
                Toast.makeText(
                    this,
                    "Minimum 3 characters are required.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                movieApi.searchMovies(searchQuery, 1).enqueue(object : Callback<SearchResult> {
                    override fun onResponse(
                        call: Call<SearchResult>,
                        response: Response<SearchResult>
                    ) {
                        response.body()?.let {
                            movieListAdapter.setData(it.search)
                        }
                    }

                    override fun onFailure(call: Call<SearchResult>, t: Throwable) {

                    }


                })
            }

        }
    }
}