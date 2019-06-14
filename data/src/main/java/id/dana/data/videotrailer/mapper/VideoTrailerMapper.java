package id.dana.data.videotrailer.mapper;

import id.dana.data.videotrailer.repository.source.network.model.VideoEntity;
import id.dana.data.videotrailer.repository.source.network.result.VideoTrailerResult;
import id.dana.onboarding.domain.Video;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version VideoTrailerMapper, v 0.1 25/04/19 14.22 by Abraham Ginting
 */
@Singleton
public class VideoTrailerMapper {

    @Inject
    public VideoTrailerMapper() {
        //For Dagger
    }

    public List<Video> map(VideoTrailerResult videoTrailerResult) {
        List<Video> videos = new ArrayList<>();

        for (int i = 0; i < videoTrailerResult.getResults().size(); i++) {
            VideoEntity entity = videoTrailerResult.getResults().get(i);
            Video video = new Video();
            video.setVideoId(entity.getVideoId());
            video.setSite(entity.getSite());
            video.setKey(entity.getKey());
            video.setName(entity.getName());
            videos.add(video);
        }
        return videos;
    }
}
