package id.dana.data.videotrailer.repository.source.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version VideoEntity, v 0.1 24/04/19 14.43 by Abraham Ginting
 */
public class VideoEntity {

    @SerializedName("id")
    private String videoId;

    @SerializedName("name")
    private String name;

    @SerializedName("key")
    private String key;

    @SerializedName("site")
    private String site;

    public VideoEntity() {
    }

    public VideoEntity(String videoId, String name, String key, String site) {
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
