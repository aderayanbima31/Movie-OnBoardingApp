package id.dana.onboarding.movie.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

import id.dana.onboarding.domain.Movie;
import id.dana.onboarding.movie.internal.PerActivity;
import id.dana.onboarding.movie.model.MovieModel;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version MovieModelDataMapper, v 0.1 2019-06-17 10:37 by Derayan Bima A
 */
@PerActivity
public class MovieModelDataMapper {

    @Inject
    public MovieModelDataMapper(){

    }


    /**
     * Transform a Collection of Movie into a Collection of MovieModel
     */
    public Collection<MovieModel> transform(Collection<Movie> movieCollection){

        Collection<MovieModel> movieModelCollection;

        if (movieCollection != null && !movieCollection.isEmpty()){
            movieModelCollection = new ArrayList<>();
            for (Movie movie: movieCollection){
                movieModelCollection.add(transform(movie));
            }
        } else {
            movieModelCollection = Collections.EMPTY_LIST;
        }

        return movieModelCollection;
    }

    /**
     * Transform a Movie into an MovieModel.
     */
    public MovieModel transform(Movie movie){

        if (movie == null){
            throw  new IllegalArgumentException("Cannot transform data a null value");
        }

        final MovieModel movieModel = new MovieModel(movie.getMovieId());
        movieModel.setTitle(movie.getTitle());
        movieModel.setGenre(movie.getGenre());
        movieModel.setMovieRate(movie.getMovieRate());
        movieModel.setReleaseDate(movie.getReleaseDate());
        movieModel.setSynopsis(movie.getSynopsis());
        movieModel.setUrlPoster(movie.getUrlPoster());
        movieModel.setMovieDuration(movie.getMovieDuration());

        return movieModel;
    }
}
