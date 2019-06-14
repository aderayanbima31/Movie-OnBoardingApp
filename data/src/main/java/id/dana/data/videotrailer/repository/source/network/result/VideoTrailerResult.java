package id.dana.data.videotrailer.repository.source.network.result;

import com.google.gson.annotations.SerializedName;

import id.dana.data.videotrailer.repository.source.network.model.VideoEntity;

import java.util.List;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version VideoTrailerResult, v 0.1 24/04/19 14.31 by Abraham Ginting
 */
public class VideoTrailerResult {

    @SerializedName("id")
    private int id;

    @SerializedName("results")
    private List<VideoEntity> results;

    public VideoTrailerResult() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<VideoEntity> getResults() {
        return results;
    }
}
