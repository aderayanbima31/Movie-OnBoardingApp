package id.dana.data.favoritemovie.repository.source;

import id.dana.data.favoritemovie.repository.source.persistence.entity.FavoriteMovieEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version MockFavoriteMovieEntityData.java, v 0.1 12/04/19 14.58 by Abraham Ginting
 */
public class MockFavoriteMovieEntityData implements FavoriteMovieEntityData {

    @Override
    public Observable<List<FavoriteMovieEntity>> getAllMovieFavorite() {
        return Observable.fromCallable(this::getMockUpData);
    }

    @Override
    public Observable<Long> addMovieAsFavorite(FavoriteMovieEntity favoriteMovieEntity) {
        return null;
    }

    @Override
    public Observable<Integer> removeMovieAsFavorite(String movieId) {
        //No implementation
        return null;
    }

    @Override
    public Observable<FavoriteMovieEntity> isMovieFavorite(String movieId) {
        return Observable.fromCallable(this::getMockUpDataSingle);
    }

    private List<FavoriteMovieEntity> getMockUpData() {
        List<FavoriteMovieEntity> favoriteMovieEntities = new ArrayList<>();

        FavoriteMovieEntity favoriteMovieEntity = new FavoriteMovieEntity("10000", 7.5,
            "100001.jpg", "Pro Player", true);
        favoriteMovieEntities.add(favoriteMovieEntity);
        return favoriteMovieEntities;
    }

    private FavoriteMovieEntity getMockUpDataSingle() {
        return new FavoriteMovieEntity("10000", 8.0,
            "photoUrl.jpg", "Movie Title", true);
    }

}
