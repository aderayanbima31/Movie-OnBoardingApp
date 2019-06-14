package id.dana.data.favoritemovie.repository.source;

import id.dana.data.favoritemovie.repository.source.persistence.entity.FavoriteMovieEntity;
import id.dana.data.persistence.BasePersistence;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version PersistenceFavoriteMovieEntityData.java, v 0.1 12/04/19 15.02 by Abraham Ginting
 */
@Singleton
public class PersistenceFavoriteMovieEntityData extends BasePersistence implements FavoriteMovieEntityData {

    @Inject
    public PersistenceFavoriteMovieEntityData(Context context) {
        super(context);
    }

    @Override
    public Observable<List<FavoriteMovieEntity>> getAllMovieFavorite() {
        return Observable.defer(() -> {
            List<FavoriteMovieEntity> favoriteMovieEntityList = getDatabase().favoriteMovieDao()
                .getAllFavoriteMovie();

            if (!favoriteMovieEntityList.isEmpty()) {
                return Observable.just(favoriteMovieEntityList);
            }

            return Observable.error(new Throwable("Empty Favorite Movie list"));
        });
    }

    @Override
    public Observable<Long> addMovieAsFavorite(FavoriteMovieEntity favoriteMovieEntity) {
        return Observable.defer(() ->
            Observable
                .just(getDatabase().favoriteMovieDao()
                    .insertMovieAsFavorite(favoriteMovieEntity)));
    }

    @Override
    public Observable<Integer> removeMovieAsFavorite(String movieId) {
        return Observable.defer(() -> Observable
            .just(getDatabase().favoriteMovieDao().removeMoviesAsFavorite(movieId)));
    }

    @Override
    public Observable<FavoriteMovieEntity> isMovieFavorite(String movieId) {
        return Observable.defer(() ->
            Observable.just(getDatabase().favoriteMovieDao().isFavoriteMovie(movieId)));
    }

    private FavoriteMovieEntity getSingleFavoriteMovie(String movieId) {
        return getDatabase().favoriteMovieDao().getFavoriteMovieEntityByMovieId(movieId);
    }

}
