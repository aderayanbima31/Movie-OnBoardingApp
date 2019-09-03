package id.dana.data.repository;

import id.dana.data.entity.mapper.MovieEntityDataMapper;
import id.dana.data.repository.datasource.MovieEntityData;
import id.dana.data.repository.datasource.MovieEntityDataFactory;
import id.dana.data.videotrailer.VideoTrailerEntityData;
import id.dana.data.videotrailer.mapper.VideoTrailerMapper;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.dana.onboarding.domain.Movie;
import id.dana.onboarding.domain.Video;
import id.dana.onboarding.domain.repository.MovieRepository;
import io.reactivex.Observable;

/**
 * {@link MovieRepository} for retrieving movie data.
 */
@Singleton
public class MovieEntityRepository implements MovieRepository {

    private final MovieEntityDataFactory movieEntityDataFactory;

    private final MovieEntityDataMapper movieEntityDataMapper;

    private final VideoTrailerMapper videoTrailerMapper;

    /**
     * Constructs a {@link MovieRepository}.
     *
     * @param movieEntityDataFactory a factory to construct different data source implementation
     * @param movieEntityDataMapper  {@link MovieEntityDataMapper}
     */
    @Inject
    public MovieEntityRepository(MovieEntityDataFactory movieEntityDataFactory,
        MovieEntityDataMapper movieEntityDataMapper, VideoTrailerMapper videoTrailerMapper) {
        this.movieEntityDataFactory = movieEntityDataFactory;
        this.movieEntityDataMapper = movieEntityDataMapper;
        this.videoTrailerMapper = videoTrailerMapper;
    }

    @Override
    public Observable<List<Movie>> popularMovies(int page) {
        final MovieEntityData movieEntityData = this.movieEntityDataFactory.createCloudDataStore();
        return movieEntityData.popularMovieEntityList(page).map(this.movieEntityDataMapper::transform);
    }

    @Override
    public Observable<List<Movie>> topRatedMovies(int page) {
        final MovieEntityData movieEntityData = this.movieEntityDataFactory.createCloudDataStore();
        return movieEntityData.topRatedMovieEntityList(page).map(this.movieEntityDataMapper::transform);
    }

    @Override
    public Observable<Movie> movie(String movieId) {
        final MovieEntityData movieEntityData = this.movieEntityDataFactory.create(movieId);
        return movieEntityData.movieEntityDetails(movieId)
            .map(this.movieEntityDataMapper::transform);
    }


    /**
     * Construct data from {@link retrofit2.Retrofit} source implementation
     */

    @Override
    public Observable<List<Movie>> retrofitPopularMovies(int page) {
        MovieEntityData movieEntityData = this.movieEntityDataFactory
            .createCloudDataStoreRetrofit();
        return movieEntityData.getPopularMovieEntityList(page)
            .map(movieEntityDataMapper::transformMovieEntity);
    }

    @Override
    public Observable<List<Movie>> retrofitTopRateMovies(int page) {
        MovieEntityData movieEntityData = this.movieEntityDataFactory
            .createCloudDataStoreRetrofit();
        return movieEntityData.getTopRatedMovieEntityList(page)
            .map(movieEntityDataMapper::transformMovieEntity);
    }

    @Override
    public Observable<Movie> retrofitDetailMovie(String movieId) {
        MovieEntityData movieEntityData = this.movieEntityDataFactory
            .createCloudDataStoreRetrofit();
        return movieEntityData.getDetailsMovie(movieId)
            .map(movieEntityDataMapper::transform);
    }

    @Override
    public Observable<List<Video>> retrofitVideoTrailer(int movieId) {
        VideoTrailerEntityData videoTrailerEntityData = this.movieEntityDataFactory
            .createCloudDataVideoRetrofit(movieId);
        return videoTrailerEntityData.getAllVideoById(movieId).map(videoTrailerMapper::map);
    }
}
