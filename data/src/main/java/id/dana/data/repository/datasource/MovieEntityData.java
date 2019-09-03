package id.dana.data.repository.datasource;

import id.dana.data.entity.MovieEntity;
import id.dana.data.entity.MovieResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface MovieEntityData {

    /**
     * Get an {@link Observable} which will emit a List of {@link MovieEntity}.
     */
    Observable<List<MovieEntity>> popularMovieEntityList();

    Observable<List<MovieEntity>> topRatedMovieEntityList();

    /**
     * Get an {@link Observable} which will emit a {@link MovieEntity} by its id.
     *
     * @param movieId this id to retrieve movie data.
     */
    Observable<MovieEntity> movieEntityDetails(final String movieId);

    Observable<MovieResponse> getPopularMovieEntityList();

    Observable<MovieResponse> getTopRatedMovieEntityList();

    Observable<MovieEntity> getDetailsMovie(String movieId);
}
