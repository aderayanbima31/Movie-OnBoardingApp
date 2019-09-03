package id.dana.data.network;

import id.dana.data.entity.MovieEntity;
import id.dana.data.entity.MovieResponse;
import id.dana.data.entity.mapper.MovieEntityJsonMapper;
import id.dana.data.exception.NetworkConnectionException;
import id.dana.data.videotrailer.repository.source.network.result.VideoTrailerResult;
import com.squareup.okhttp.HttpUrl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.net.MalformedURLException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RestAPIImpl implements RestApi {

    private static final String TAG = RestAPIImpl.class.getSimpleName();

    private Context context;

    private MovieEntityJsonMapper movieEntityJsonMapper;

    /**
     * Constructor of the class.
     */
    public RestAPIImpl(Context context, MovieEntityJsonMapper movieEntityJsonMapper) {
        if (context == null || movieEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameter cannot be null!!");
        }
        this.context = context.getApplicationContext();
        this.movieEntityJsonMapper = movieEntityJsonMapper;
    }

    public RestAPIImpl(Context context) {
        this.context = context;
    }

    @Override
    public Observable<MovieEntity> movieEntityById(String movieId) {
        return Observable.create(emitter -> {
            if (RestAPIImpl.this.isThereInternetConnection()) {
                try {
                    String responseMovieDetails = RestAPIImpl.this.getMovieDetailsFromApi(movieId);
                    if (responseMovieDetails != null) {
                        emitter.onNext(
                            movieEntityJsonMapper.transformMovieEntity(responseMovieDetails));
                        emitter.onComplete();
                    } else {
                        emitter.onError(new NetworkConnectionException());
                    }
                } catch (Exception e) {
                    emitter.onError(new NetworkConnectionException(e.getCause()));
                }
            } else {
                emitter.onError(new NetworkConnectionException());
            }
        });
    }

    @Override
    public Observable<MovieResponse> getPopularMovieRetrofit(int page) {
        RestApi restApi = ApiClient.getClient().create(RestApi.class);
        return restApi.getPopularMovieRetrofit(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<MovieResponse> getTopRatedMovieRetrofit(int page) {
        RestApi restApi = ApiClient.getClient().create(RestApi.class);
        return restApi.getTopRatedMovieRetrofit(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<MovieEntity> getMovieDetailsRetrofit(int id) {
        RestApi restApi = ApiClient.getClient().create(RestApi.class);
        return restApi.getMovieDetailsRetrofit(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<VideoTrailerResult> getVideoTrailerById(int movieId) {
        RestApi restApi = ApiClient.getClient().create(RestApi.class);
        return restApi.getVideoTrailerById(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }

    private String getMovieDetailsFromApi(String movieId) throws MalformedURLException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(String.valueOf(API_URL_GET_MOVIE_DETAILS))
            .newBuilder();
        urlBuilder.addPathSegment(movieId);
        String urlBuilt = urlBuilder.build().toString();
        Log.e(TAG, "getMovieDetailsFromApi: " + urlBuilt);
        return ApiConnection.createGet(urlBuilt).requestSyncCall();
    }

    private String getMovieTopRatedEntitiesFromApi() throws MalformedURLException {
        return ApiConnection.createGet(API_URL_GET_TOP_RATED_MOVIE_LIST).requestSyncCall();
    }

    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager = (ConnectivityManager) this.context
            .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnected());

        return isConnected;
    }

    private String getMoviePopularEntitiesFromApi() throws MalformedURLException {
        return ApiConnection.createGet(API_URL_GET_POPULAR_MOVIE_LIST).requestSyncCall();
    }
}
