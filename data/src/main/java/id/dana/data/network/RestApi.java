package id.dana.data.network;

import id.dana.data.entity.MovieEntity;
import id.dana.data.entity.MovieResponse;
import id.dana.data.videotrailer.repository.source.network.result.VideoTrailerResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {

    String API_BASE_URL = "https://api.themoviedb.org/3/";

    String API_POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185";

    String API_URL_GET_MOVIE_DETAILS = API_BASE_URL + "movie/";

    String API_URL_GET_POPULAR_MOVIE_LIST = API_BASE_URL + "movie/popular";

    String API_URL_GET_TOP_RATED_MOVIE_LIST = API_BASE_URL + "movie/top_rated";

//    Observable<List<MovieEntity>> getPopularMovie();
//
//    Observable<List<MovieEntity>> getTopRatedMovie();

    Observable<MovieEntity> movieEntityById(final String movieId);

    /**
     * Retrofit Service Call
     */
    @GET("movie/popular")
    Observable<MovieResponse> getPopularMovieRetrofit(@Query("page") int page);

    @GET("movie/top_rated")
    Observable<MovieResponse> getTopRatedMovieRetrofit(@Query("page") int page);

    @GET("movie/{movieId}")
    Observable<MovieEntity> getMovieDetailsRetrofit(@Path("movieId") int id);

    @GET("/movie/{movie_id}/videos")
    Observable<VideoTrailerResult> getVideoTrailerById(@Path("movie_id") int movieId);
}