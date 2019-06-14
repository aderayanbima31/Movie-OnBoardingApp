package id.dana.onboarding.movie.model;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version FavoriteMovieModel, v 0.1 2019-06-14 16:33 by Derayan Bima A
 */
public class FavoriteMovieModel {

    private boolean isFavorite;

    private String movieId;

    private String originalTitle;

    private String posterPath;

    private Double voteAverage;

    public FavoriteMovieModel() {
    }

    public FavoriteMovieModel(String movieId) {
        this.movieId = movieId;
    }

    public FavoriteMovieModel(String originalTitle, String posterPath, boolean isFavorite,
        Double voteAverage) {
        this.isFavorite = isFavorite;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public String toString() {
        return "FavoriteMovieModel{" +
            "isFavorite=" + isFavorite +
            ", movieId='" + movieId + '\'' +
            ", originalTitle='" + originalTitle + '\'' +
            ", posterPath='" + posterPath + '\'' +
            ", voteAverage=" + voteAverage +
            '}';
    }
}
