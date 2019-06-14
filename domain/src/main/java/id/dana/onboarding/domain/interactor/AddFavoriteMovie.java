package id.dana.onboarding.domain.interactor;


import javax.inject.Inject;

import id.dana.onboarding.domain.executor.PostExecutionThread;
import id.dana.onboarding.domain.executor.ThreadExecutor;
import id.dana.onboarding.domain.repository.FavoriteMovieRepository;
import io.reactivex.Observable;

public class AddFavoriteMovie extends UseCase<Boolean, AddFavoriteMovie.Params> {

    private final FavoriteMovieRepository repository;

    @Inject
    public AddFavoriteMovie(ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread,
        FavoriteMovieRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<Boolean> buildUseCaseObservable(Params params) {
        return repository
            .addFavoriteMovie(params.movieId, params.originalTitle, params.posterPath,
                params.voteAverage, params.isFavorite);
    }

    public static class Params {

        private Boolean isFavorite;

        private String movieId;

        private String originalTitle;

        private String posterPath;

        private Double voteAverage;

        Params(String movieId, Boolean isFavorite, String originalTitle, String posterPath,
            Double voteAverage) {
            this.movieId = movieId;
            this.isFavorite = isFavorite;
            this.originalTitle = originalTitle;
            this.posterPath = posterPath;
            this.voteAverage = voteAverage;
        }

        public static Params forAddFavoriteMovie(String movieId, Double voteAverage,
            String posterPath, String originalTitle, Boolean isFavorite) {
            return new AddFavoriteMovie.Params(movieId, isFavorite, originalTitle, posterPath,
                voteAverage);
        }
    }
}
