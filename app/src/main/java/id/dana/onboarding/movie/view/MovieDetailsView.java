package id.dana.onboarding.movie.view;

import java.util.List;

import id.dana.onboarding.movie.model.MovieModel;
import id.dana.onboarding.movie.model.MovieTrailerModel;

/**
 * Interface representing a View in a MVP pattern.
 * In this case is used as a view representing a movie details.
 */
public interface MovieDetailsView extends LoadDataView {

    void renderMovie(MovieModel movieModel);

    void onLikedMovie(boolean success);

    void onRemoveMovie(boolean remove);

    void isMovieFavorite();

    void showMovieTrailer(List<MovieTrailerModel> videoModels);

}
