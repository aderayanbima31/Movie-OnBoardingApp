package id.dana.data.favoritemovie.repository.source;

import id.dana.data.favoritemovie.repository.source.persistence.entity.FavoriteMovieEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version FavoriteMovieEntityData.java, v 0.1 12/04/19 14.59 by Abraham Ginting
 */
public interface FavoriteMovieEntityData {

    Observable<List<FavoriteMovieEntity>> getAllMovieFavorite();

    Observable<Long> addMovieAsFavorite(FavoriteMovieEntity favoriteMovieEntity);

    Observable<Integer> removeMovieAsFavorite(String movieId);

    Observable<FavoriteMovieEntity> isMovieFavorite(String movieId);
}
