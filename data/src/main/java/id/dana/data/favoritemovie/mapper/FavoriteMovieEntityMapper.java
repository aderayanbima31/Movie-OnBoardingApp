package id.dana.data.favoritemovie.mapper;

import id.dana.data.favoritemovie.repository.source.persistence.entity.FavoriteMovieEntity;
import id.dana.onboarding.domain.FavoriteMovie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version FavoriteMovieEntityMapper.java, v 0.1 12/04/19 15.09 by Abraham Ginting
 */
public class FavoriteMovieEntityMapper {

    @Inject
    public FavoriteMovieEntityMapper() {
    }

    public FavoriteMovie apply(FavoriteMovieEntity favoriteMovieEntity) {
        FavoriteMovie favoriteMovie = new FavoriteMovie();

        if (favoriteMovieEntity != null) {
            favoriteMovie.setMovieId(favoriteMovieEntity.getId());
            favoriteMovie.setPosterPath(favoriteMovieEntity.getPosterPath());
            favoriteMovie.setOriginalTitle(favoriteMovieEntity.getOriginalTitle());
            favoriteMovie.setVoteAverage(favoriteMovieEntity.getVoteAverage());
            favoriteMovie.setFavorite(favoriteMovieEntity.isFavorite());
        }

        return favoriteMovie;
    }

    public List<FavoriteMovie> apply(List<FavoriteMovieEntity> favoriteMovieEntities) {
        return map(favoriteMovieEntities);
    }

    private List<FavoriteMovie> map(List<FavoriteMovieEntity> favoriteMovieEntities) {
        if (favoriteMovieEntities != null) {
            List<FavoriteMovie> favoriteMovies = new ArrayList<>();
            for (FavoriteMovieEntity favoriteMovieEntity : favoriteMovieEntities) {
                FavoriteMovie favoriteMovie = new FavoriteMovie();
                favoriteMovie.setMovieId(favoriteMovieEntity.getId());
                favoriteMovie.setOriginalTitle(favoriteMovieEntity.getOriginalTitle());
                favoriteMovie.setVoteAverage(favoriteMovieEntity.getVoteAverage());
                favoriteMovie.setPosterPath(favoriteMovieEntity.getPosterPath());
                favoriteMovie.setFavorite(favoriteMovieEntity.isFavorite());
                favoriteMovies.add(favoriteMovie);
            }
            return favoriteMovies;
        }
        return null;
    }
}
