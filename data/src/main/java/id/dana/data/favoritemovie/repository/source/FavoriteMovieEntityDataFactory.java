package id.dana.data.favoritemovie.repository.source;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version FavoriteMovieEntityDataFactory.java, v 0.1 12/04/19 14.59 by Abraham Ginting
 */
@Singleton
public class FavoriteMovieEntityDataFactory {

    private final PersistenceFavoriteMovieEntityData persistenceFavoriteMovieEntityData;

    @Inject
    public FavoriteMovieEntityDataFactory(
        PersistenceFavoriteMovieEntityData persistenceFavoriteMovieEntityData) {
        this.persistenceFavoriteMovieEntityData = persistenceFavoriteMovieEntityData;
    }

    public FavoriteMovieEntityData createData(String source) {
        switch (source) {
            case "MOCK":
                return new MockFavoriteMovieEntityData();
            case "LOCAL":
            default:
                return persistenceFavoriteMovieEntityData;
        }
    }

}
