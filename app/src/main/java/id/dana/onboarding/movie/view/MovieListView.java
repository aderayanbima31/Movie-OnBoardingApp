package id.dana.onboarding.movie.view;

import java.util.Collection;

import id.dana.onboarding.movie.model.MovieModel;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version MovieListView, v 0.1 2019-06-17 10:35 by Derayan Bima A
 */
public interface MovieListView extends  LoadDataView{

    void renderMovieList(Collection<MovieModel> movieModelCollection);

    void viewMovie(MovieModel movieModel);
}
