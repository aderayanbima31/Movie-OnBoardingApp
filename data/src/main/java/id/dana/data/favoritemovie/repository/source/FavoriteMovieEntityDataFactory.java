package id.dana.data.favoritemovie.repository.source;

import javax.inject.Inject;
import javax.inject.Singleton;


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
