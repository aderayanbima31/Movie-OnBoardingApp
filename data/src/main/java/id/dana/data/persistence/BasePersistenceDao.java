package id.dana.data.persistence;

import id.dana.data.favoritemovie.repository.source.persistence.dao.FavoriteMovieDao;
import id.dana.data.favoritemovie.repository.source.persistence.entity.FavoriteMovieEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version FavoriteMovie, v 0.1 2019-06-14 10:53 by Derayan Bima A
 */
@Database(entities = {FavoriteMovieEntity.class}, version = 1, exportSchema = false)
public abstract class BasePersistenceDao extends RoomDatabase {

    public abstract FavoriteMovieDao favoriteMovieDao();
}
