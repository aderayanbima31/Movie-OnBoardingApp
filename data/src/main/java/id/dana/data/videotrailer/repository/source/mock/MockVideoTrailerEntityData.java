package id.dana.data.videotrailer.repository.source.mock;

import id.dana.data.videotrailer.VideoTrailerEntityData;
import id.dana.data.videotrailer.repository.source.network.result.VideoTrailerResult;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version MockVideoTrailerEntityData, v 0.1 25/04/19 14.08 by Abraham Ginting
 */
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
