package id.dana.onboarding.domain.interactor;


import javax.inject.Inject;

import id.dana.onboarding.domain.FavoriteMovie;
import id.dana.onboarding.domain.executor.PostExecutionThread;
import id.dana.onboarding.domain.executor.ThreadExecutor;
import id.dana.onboarding.domain.repository.FavoriteMovieRepository;
import io.reactivex.Observable;

public class CheckMovieIsFavorite extends UseCase<FavoriteMovie, CheckMovieIsFavorite.Params> {

    private FavoriteMovieRepository repository;

    @Inject
    CheckMovieIsFavorite(ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread, FavoriteMovieRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<FavoriteMovie> buildUseCaseObservable(Params params) {
        return repository.checkMovieIsFavorite(params.movieId);
    }

    public static class Params {

        private String movieId;

        Params(String movieId) {
            this.movieId = movieId;
        }

        public static Params forCheckMovieIsFavorite(String movieId) {
            return new CheckMovieIsFavorite.Params(movieId);
        }
    }
}
