package id.dana.onboarding.domain;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version FavoriteMovie, v 0.1 2019-06-14 10:53 by Derayan Bima A
 */
public class FavoriteMovie {

    private Boolean isFavorite;

    private String movieId;

    private String originalTitle;

    private String posterPath;

    private Double voteAverage;

    public FavoriteMovie() {
    }

    public FavoriteMovie(String movieId) {
        this.movieId = movieId;
    }

    public FavoriteMovie(String originalTitle, String posterPath,
        Double voteAverage, Boolean isFavorite) {
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.isFavorite = isFavorite;
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

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }
}
