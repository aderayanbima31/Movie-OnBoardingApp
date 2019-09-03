package id.dana.data.repository.datasource;

import id.dana.data.entity.MovieEntity;
import id.dana.data.entity.MovieResponse;
import id.dana.data.network.RestApi;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link MovieEntityData} implementation based on connections to the API.
 */
public class NetworkMovieEntityData implements MovieEntityData {

    private final RestApi restApi;

    /**
     * Construct a {@link MovieEntityData} based on connection to the API.
     */
    NetworkMovieEntityData(RestApi restApi) {
        this.restApi = restApi;
    }

//    @Override
//    public Observable<List<MovieEntity>> popularMovieEntityList() {
//        return null;
//    }

    @Override
    public Observable<List<MovieEntity>> popularMovieEntityList(int page) {
        return null;
    }

    @Override
    public Observable<List<MovieEntity>> topRatedMovieEntityList(int page) {
        return null;
    }
    
    @Override
    public Observable<MovieEntity> movieEntityDetails(String movieId) {
        return this.restApi.movieEntityById(movieId);
    }

    @Override
    public Observable<MovieResponse> getPopularMovieEntityList(int page) {
        return this.restApi.getPopularMovieRetrofit(page);
    }

    @Override
    public Observable<MovieResponse> getTopRatedMovieEntityList(int page) {
        return this.restApi.getTopRatedMovieRetrofit(page);
    }

    @Override
    public Observable<MovieEntity> getDetailsMovie(String movieId) {
        return this.restApi.getMovieDetailsRetrofit(Integer.parseInt(movieId));
    }
}