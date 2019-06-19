package id.dana.data.favoritemovie.repository.source;

import id.dana.data.favoritemovie.repository.source.persistence.entity.FavoriteMovieEntity;

import java.util.List;

import io.reactivex.Observable;


public interface FavoriteMovieEntityData {

    Observable<List<FavoriteMovieEntity>> getAllMovieFavorite();

    Observable<Long> addMovieAsFavorite(FavoriteMovieEntity favoriteMovieEntity);

    Observable<Integer> removeMovieAsFavorite(String movieId);

    Observable<FavoriteMovieEntity> isMovieFavorite(String movieId);
}
