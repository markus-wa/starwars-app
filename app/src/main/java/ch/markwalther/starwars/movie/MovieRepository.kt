package ch.markwalther.starwars.movie

class MovieRepository {

    var client: MovieService = RetrofitClient.movieService

    suspend fun all() = client.all()

}