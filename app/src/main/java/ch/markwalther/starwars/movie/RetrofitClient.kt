package ch.markwalther.starwars.movie

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val movieService: MovieService by lazy {
        Retrofit.Builder()
            .baseUrl("https://swapi.co/api/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(MovieService::class.java)
    }

}