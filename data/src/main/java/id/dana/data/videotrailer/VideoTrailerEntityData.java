package id.dana.data.videotrailer;

import id.dana.data.videotrailer.repository.source.network.result.VideoTrailerResult;

import io.reactivex.Observable;


public interface VideoTrailerEntityData {

    Observable<VideoTrailerResult> getAllVideoById(int movieId);
}
