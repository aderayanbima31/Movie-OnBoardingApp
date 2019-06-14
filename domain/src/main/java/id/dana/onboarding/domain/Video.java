package id.dana.onboarding.domain;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version Video, v 0.1 2019-06-14 10:54 by Derayan Bima A
 */
public class Video {

    private String videoId;

    private String name;

    private String key;

    private String site;

    public Video() {
    }

    public Video(String videoId, String name, String key, String site) {
        this.videoId = videoId;
        this.name = name;
        this.key = key;
        this.site = site;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
