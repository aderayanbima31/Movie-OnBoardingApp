package id.dana.onboarding.domain;

import java.util.List;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version VideoResult, v 0.1 2019-06-14 10:54 by Derayan Bima A
 */
public class VideoResult {

    private List<Video> videos;

    public VideoResult() {
    }

    public VideoResult(List<Video> videos) {
        this.videos = videos;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
