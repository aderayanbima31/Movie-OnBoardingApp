package id.dana.data.videotrailer;

import id.dana.data.videotrailer.repository.source.network.result.VideoTrailerResult;

import io.reactivex.Observable;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version VideoTrailerEntityData, v 0.1 25/04/19 13.04 by Abraham Ginting
 */
public interface VideoTrailerEntityData {

    Observable<VideoTrailerResult> getAllVideoById(int movieId);
}
