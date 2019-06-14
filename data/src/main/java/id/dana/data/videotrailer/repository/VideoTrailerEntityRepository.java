package id.dana.data.videotrailer.repository;

import id.dana.data.videotrailer.VideoTrailerEntityData;
import id.dana.data.videotrailer.mapper.VideoTrailerMapper;
import id.dana.data.videotrailer.repository.source.VideoTrailerDataFactory;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.dana.onboarding.domain.Video;
import id.dana.onboarding.domain.repository.VideoTrailerRepository;
import io.reactivex.Observable;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version FavoriteMovie, v 0.1 2019-06-14 10:53 by Derayan Bima A
 */
@Singleton
public class VideoTrailerEntityRepository implements VideoTrailerRepository {

    private final VideoTrailerDataFactory entityDataFactory;

    private final VideoTrailerMapper entityDataMapper;

    @Inject
    public VideoTrailerEntityRepository(
        VideoTrailerDataFactory entityDataFactory,
        VideoTrailerMapper entityDataMapper) {
        this.entityDataFactory = entityDataFactory;
        this.entityDataMapper = entityDataMapper;
    }

    @Override
    public Observable<List<Video>> getVideoTrailers(int movieId) {
        return createDataStore().getAllVideoById(movieId).map(entityDataMapper::map);
    }

    private VideoTrailerEntityData createDataStore() {
        return entityDataFactory.createData("Network");
    }
}
