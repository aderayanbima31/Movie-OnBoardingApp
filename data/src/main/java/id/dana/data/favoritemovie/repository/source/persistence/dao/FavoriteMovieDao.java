package id.dana.data.favoritemovie.repository.source.persistence.dao;

import id.dana.data.favoritemovie.repository.source.persistence.entity.FavoriteMovieEntity;
import id.dana.data.persistence.DBConstant;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


@Dao
public interface FavoriteMovieDao {

    @Query("SELECT * FROM " + DBConstant.MOVIE_TABLE_NAME)
    List<FavoriteMovieEntity> getAllFavoriteMovie();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertMovieAsFavorite(FavoriteMovieEntity favoriteMovieEntity);

    @Query("DELETE FROM " + DBConstant.MOVIE_TABLE_NAME + " WHERE " + DBConstant.MOVIE_ID +
        "=:movieId")
    int removeMoviesAsFavorite(String movieId);

    @Query("SELECT * FROM " + DBConstant.MOVIE_TABLE_NAME + " WHERE " + DBConstant.MOVIE_ID + " " +
        "=:movieId")
    FavoriteMovieEntity getFavoriteMovieEntityByMovieId(String movieId);

    @Query("SELECT * FROM " + DBConstant.MOVIE_TABLE_NAME + " WHERE " + DBConstant.MOVIE_ID + " " +
        "=:movieId")
    FavoriteMovieEntity isFavoriteMovie(String movieId);
}
