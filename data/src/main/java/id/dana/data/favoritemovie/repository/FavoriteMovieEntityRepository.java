package id.dana.data.favoritemovie.repository;

import id.dana.data.favoritemovie.mapper.FavoriteMovieEntityMapper;
import id.dana.data.favoritemovie.repository.source.FavoriteMovieEntityData;
import id.dana.data.favoritemovie.repository.source.FavoriteMovieEntityDataFactory;
import id.dana.data.favoritemovie.repository.source.persistence.entity.FavoriteMovieEntity;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.dana.onboarding.domain.FavoriteMovie;
import id.dana.onboarding.domain.repository.FavoriteMovieRepository;
import io.reactivex.Observable;


@Singleton
public class FavoriteMovieEntityRepository implements FavoriteMovieRepository {

    private final FavoriteMovieEntityDataFactory dataFactory;

    private final FavoriteMovieEntityMapper entityMapper;

    @Inject
    public FavoriteMovieEntityRepository(
        FavoriteMovieEntityDataFactory dataFactory,
        FavoriteMovieEntityMapper entityMapper) {
        this.dataFactory = dataFactory;
        this.entityMapper = entityMapper;
    }

    @Override
    public Observable<List<FavoriteMovie>> getFavoriteMovie() {
        return createFavoriteMovieEntityData().getAllMovieFavorite().map(
            entityMapper::apply);
    }

    @Override
    public Observable<Boolean> addFavoriteMovie(String movieId, String title, String posterPath,
        Double voteAverage, Boolean isFavorite) {
        FavoriteMovieEntity favoriteMovieEntity = new FavoriteMovieEntity(movieId, voteAverage,
            posterPath, title, isFavorite);

        return createFavoriteMovieEntityData().addMovieAsFavorite(favoriteMovieEntity)
            .flatMap(aLong -> Observable.just(true));
    }

    @Override
    public Observable<FavoriteMovie> checkMovieIsFavorite(String movieId) {
        return createFavoriteMovieEntityData().isMovieFavorite(movieId).map(
            entityMapper::apply);
    }

    @Override
    public Observable<Boolean> removeFavoriteMovie(String movieId) {
        return createFavoriteMovieEntityData().removeMovieAsFavorite(movieId)
            .flatMap(aLong -> Observable.just(true));
    }

    private FavoriteMovieEntityData createFavoriteMovieEntityData() {
        return dataFactory.createData("LOCAL");
    }
}
