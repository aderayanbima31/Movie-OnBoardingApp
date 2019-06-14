package id.dana.data.videotrailer.repository.source.network;

import id.dana.data.network.RestApi;
import id.dana.data.videotrailer.VideoTrailerEntityData;
import id.dana.data.videotrailer.repository.source.network.result.VideoTrailerResult;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version FavoriteMovie, v 0.1 2019-06-14 10:53 by Derayan Bima A
 */
public class NetworkVideoTrailerEntityData implements VideoTrailerEntityData {

    private final RestApi restApi;

    @Inject
    public NetworkVideoTrailerEntityData(
        RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<VideoTrailerResult> getAllVideoById(int movieId) {
        return restApi.getVideoTrailerById(movieId);
    }
}
