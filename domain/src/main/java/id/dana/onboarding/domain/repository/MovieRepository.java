package id.dana.onboarding.domain.repository;

import java.util.List;

import id.dana.onboarding.domain.Movie;
import id.dana.onboarding.domain.Video;
import io.reactivex.Observable;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version MovieRepository, v 0.1 2019-06-14 10:58 by Derayan Bima A
 */
public interface MovieRepository {

    /**
     * Get an {@link Observable} which will emit a List of {@link com.android.domain.Movie}
     */
    Observable<List<Movie>> popularMovies(int page);

    Observable<List<Movie>> topRatedMovies(int page);

    /**
     * Get an {@link Observable} which will emit a {@link Movie}.
     *
     * @param movieId The movie id used to retrieve movie data.
     */
    Observable<Movie> movie(final String movieId);

    /**
     * Get an {@link Observable} which will emit a List of {@link com.android.domain.Movie} using
     * Retrofit
     */
    Observable<List<Movie>> retrofitPopularMovies(int page);

    Observable<List<Movie>> retrofitTopRateMovies(int page);

    Observable<Movie> retrofitDetailMovie(String movieId);

    Observable<List<Video>> retrofitVideoTrailer(int movieId);
}
