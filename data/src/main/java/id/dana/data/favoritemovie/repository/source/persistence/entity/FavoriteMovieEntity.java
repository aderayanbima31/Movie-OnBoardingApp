package id.dana.data.favoritemovie.repository.source.persistence.entity;

import com.google.gson.annotations.SerializedName;

import id.dana.data.persistence.DBConstant;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = DBConstant.MOVIE_TABLE_NAME)
public class FavoriteMovieEntity {

    @SerializedName("id")
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = DBConstant.MOVIE_ID)
    private String id;

    @ColumnInfo(name = DBConstant.MOVIE_IS_FAVORITE)
    private boolean isFavorite;

    @SerializedName("original_title")
    @ColumnInfo(name = DBConstant.MOVIE_ORIGINAL_TITLE)
    private String originalTitle;

    @SerializedName("poster_path")
    @ColumnInfo(name = DBConstant.MOVIE_POSTER_PATH)
    private String posterPath;

    @SerializedName("vote_average")
    @ColumnInfo(name = DBConstant.MOVIE_VOTE_AVERAGE)
    private Double voteAverage;

    public FavoriteMovieEntity() {
    }

    @Ignore
    public FavoriteMovieEntity(@NonNull String movieId, Double voteAverage,
        String posterPath,
        String originalTitle, boolean isFavorite) {
        this.id = movieId;
        this.voteAverage = voteAverage;
        this.posterPath = posterPath;
        this.originalTitle = originalTitle;
        this.isFavorite = isFavorite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
