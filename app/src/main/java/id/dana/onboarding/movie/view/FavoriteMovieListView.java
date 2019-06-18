package id.dana.onboarding.movie.view;


import java.util.Collection;

import id.dana.onboarding.movie.model.FavoriteMovieModel;

public interface FavoriteMovieListView extends LoadDataView {

    void renderFavoriteMovieList(Collection<FavoriteMovieModel> favoriteMovieModelCollection);

    void viewFavoriteMovie(FavoriteMovieModel favoriteMovieModel);

}
