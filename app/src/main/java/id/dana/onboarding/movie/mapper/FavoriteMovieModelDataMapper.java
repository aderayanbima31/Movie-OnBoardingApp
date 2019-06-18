package id.dana.onboarding.movie.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

import id.dana.onboarding.domain.FavoriteMovie;
import id.dana.onboarding.movie.model.FavoriteMovieModel;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version FavoriteMovieModelDataMapper, v 0.1 2019-06-17 11:08 by Derayan Bima A
 */
public class FavoriteMovieModelDataMapper {

    @Inject
    public FavoriteMovieModelDataMapper(){

    }

    public Collection<FavoriteMovieModel> transform(
        Collection<FavoriteMovie> favoriteMovieCollection) {
        Collection<FavoriteMovieModel> favoriteMovieModelCollection;

        if (favoriteMovieCollection != null && !favoriteMovieCollection.isEmpty()) {
            favoriteMovieModelCollection = new ArrayList<>();
            for (FavoriteMovie favoriteMovie : favoriteMovieCollection) {
                favoriteMovieModelCollection.add(transform(favoriteMovie));
            }
        } else {
            favoriteMovieModelCollection = Collections.emptyList();
        }

        return favoriteMovieModelCollection;
    }

    public FavoriteMovieModel transform(FavoriteMovie favoriteMovie) {
        if (favoriteMovie == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final FavoriteMovieModel favoriteMovieModel = new FavoriteMovieModel(
            favoriteMovie.getMovieId());
        favoriteMovieModel.setOriginalTitle(favoriteMovie.getOriginalTitle());
        favoriteMovieModel.setFavorite(favoriteMovie.getFavorite());
        favoriteMovieModel.setPosterPath(favoriteMovie.getPosterPath());
        favoriteMovieModel.setVoteAverage(favoriteMovie.getVoteAverage());
        favoriteMovieModel.setFavorite(favoriteMovie.getFavorite());
        return favoriteMovieModel;
    }

}
