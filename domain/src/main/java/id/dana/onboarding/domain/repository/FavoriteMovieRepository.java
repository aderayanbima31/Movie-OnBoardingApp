package id.dana.onboarding.domain.repository;

import java.util.List;

import id.dana.onboarding.domain.FavoriteMovie;
import io.reactivex.Observable;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version FavoriteMovieRepository, v 0.1 2019-06-14 10:57 by Derayan Bima A
 */
public interface FavoriteMovieRepository {

    Observable<List<FavoriteMovie>> getFavoriteMovie();

    Observable<Boolean> addFavoriteMovie(String movieId, String title, String posterPath,
        Double voteAverage, Boolean isFavorite);

    Observable<FavoriteMovie> checkMovieIsFavorite(String movieId);

    Observable<Boolean> removeFavoriteMovie(String movieId);
}
