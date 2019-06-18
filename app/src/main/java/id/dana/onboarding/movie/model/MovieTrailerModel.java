package id.dana.onboarding.movie.model;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version MovieTrailerModel, v 0.1 2019-06-14 16:34 by Derayan Bima A
 */
public class MovieTrailerModel {

    private String videoId;

    private String name;

    private String key;

    private String site;

    public MovieTrailerModel() {
    }

    public MovieTrailerModel(String videoId) {
        this.videoId = videoId;
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
