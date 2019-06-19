package id.dana.data.videotrailer.repository.source.mock;

import id.dana.data.videotrailer.VideoTrailerEntityData;
import id.dana.data.videotrailer.repository.source.network.result.VideoTrailerResult;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


@Singleton
public class MockVideoTrailerEntityData implements VideoTrailerEntityData {

    @Inject
    public MockVideoTrailerEntityData() {
    }

    @Override
    public Observable<VideoTrailerResult> getAllVideoById(int movieId) {
        return null;
    }
}
