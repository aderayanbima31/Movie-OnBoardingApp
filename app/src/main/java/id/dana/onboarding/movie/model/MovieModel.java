package id.dana.onboarding.movie.model;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version MovieModel, v 0.1 2019-06-14 16:33 by Derayan Bima A
 */
public class MovieModel {

    private String genre;

    private String movieDuration;

    private String movieId;

    private String movieRate;

    private String releaseDate;

    private String synopsis;

    private String title;

    private String urlMovieTrailer;

    private String urlPoster;

    private boolean isFavorite;

    public MovieModel() {
    }

    public MovieModel(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(String movieDuration) {
        this.movieDuration = movieDuration;
    }

    public String getMovieRate() {
        return movieRate;
    }

    public void setMovieRate(String movieRate) {
        this.movieRate = movieRate;
    }

    public String getUrlMovieTrailer() {
        return urlMovieTrailer;
    }

    public void setUrlMovieTrailer(String urlMovieTrailer) {
        this.urlMovieTrailer = urlMovieTrailer;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
