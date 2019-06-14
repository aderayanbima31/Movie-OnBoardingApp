package id.dana.data.repository.datasource;

import id.dana.data.entity.mapper.MovieEntityJsonMapper;
import id.dana.data.network.RestAPIImpl;
import id.dana.data.network.RestApi;
import id.dana.data.videotrailer.VideoTrailerEntityData;
import id.dana.data.videotrailer.repository.source.network.NetworkVideoTrailerEntityData;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementation of {@link MovieEntityData}.
 */
@Singleton
public class MovieEntityDataFactory {

    private final Context context;

    @Inject
    public MovieEntityDataFactory(Context context) {
        this.context = context;
    }

    /**
     * Create {@link MovieEntityData} from a movie id.
     */
    public MovieEntityData create(String movieId) {
        MovieEntityData movieEntityData;
        movieEntityData = createCloudDataStore();

        return movieEntityData;
    }

    /**
     * Create {@link MovieEntityData} to retrieve data from the Cloud.
     */
    public MovieEntityData createCloudDataStore() {
        final MovieEntityJsonMapper movieEntityJsonMapper = new MovieEntityJsonMapper();
        final RestApi restApi = new RestAPIImpl(this.context, movieEntityJsonMapper);

        return new NetworkMovieEntityData(restApi);
    }

    /**
     * Create {@link MovieEntityData} to retrieve data from the Cloud using Retrofit service.
     */
    public MovieEntityData createCloudDataStoreRetrofit() {
        RestApi restApi = new RestAPIImpl(this.context);
        return new NetworkMovieEntityData(restApi);
    }

    public VideoTrailerEntityData createCloudDataVideoRetrofit(int movieId) {
        return createCloudDataStoreVideoTrailerRetrofit();
    }

    private VideoTrailerEntityData createCloudDataStoreVideoTrailerRetrofit() {
        RestApi restApi = new RestAPIImpl(this.context);
        return new NetworkVideoTrailerEntityData(restApi);
    }

}
