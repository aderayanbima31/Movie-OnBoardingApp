package id.dana.data.entity.mapper;

import id.dana.data.entity.MovieEntity;
import id.dana.data.entity.MovieResponse;
import id.dana.data.network.RestApi;
import id.dana.onboarding.domain.Movie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link MovieEntity} (in the data layer) to {@link Movie} in the
 * domain layer.
 */
@Singleton
public class MovieEntityDataMapper {

    @Inject
    MovieEntityDataMapper() {
    }

    /**
     * Transform a List of {@link MovieEntity} into a Collection of {@link Movie}.
     *
     * @param movieEntityCollection Object Collection to be transformed.
     * @return {@link Movie} if valid {@link MovieEntity} otherwise null.
     */
    public List<Movie> transform(Collection<MovieEntity> movieEntityCollection) {
        final List<Movie> movieList = new ArrayList<>();
        for (MovieEntity movieEntity : movieEntityCollection) {
            final Movie movie = transform(movieEntity);
            if (movie != null) {
                movieList.add(movie);
            }
        }
        return movieList;
    }

    /**
     * Transform a {@link MovieEntity} into an {@link Movie}.
     *
     * @param movieEntity Object to be transformed.
     * @return {@link Movie} if valid {@link MovieEntity} otherwise null.
     */
    public Movie transform(MovieEntity movieEntity) {
        Movie movie = new Movie();

        movie.setMovieId(String.valueOf(movieEntity.getId()));
        movie.setTitle(String.valueOf(movieEntity.getOriginalTitle()));
        movie.setMovieRate(String.valueOf(movieEntity.getVoteAverage()));
        movie.setReleaseDate(movieEntity.getReleaseDate());
        movie.setSynopsis(movieEntity.getOverview());
        movie.setMovieDuration(movieEntity.getRuntime());
        movie.setUrlPoster(RestApi.API_POSTER_BASE_URL + movieEntity.getPosterPath());

        return movie;
    }

    public List<Movie> transformMovieEntity(MovieResponse movieResponse) {
        List<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < movieResponse.getResults().size(); i++) {
            MovieEntity movieEntity = movieResponse.getResults().get(i);
            Movie movie = new Movie();
            movie.setMovieId(String.valueOf(movieEntity.getId()));
            movie.setTitle(movieEntity.getOriginalTitle());
            movie.setMovieRate(String.valueOf(movieEntity.getVoteAverage()));
            movie.setReleaseDate(movieEntity.getReleaseDate());
            movie.setSynopsis(movieEntity.getOverview());
            movie.setUrlPoster(RestApi.API_POSTER_BASE_URL + movieEntity.getPosterPath());
            movie.setMovieDuration(movieEntity.getRuntime());
            movieList.add(movie);
        }
        return movieList;
    }
}
