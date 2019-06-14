package id.dana.onboarding.domain.interactor;


import javax.inject.Inject;

import id.dana.onboarding.domain.executor.PostExecutionThread;
import id.dana.onboarding.domain.executor.ThreadExecutor;
import id.dana.onboarding.domain.repository.FavoriteMovieRepository;
import io.reactivex.Observable;

public class RemoveMovieIsFavorite extends UseCase<Boolean, RemoveMovieIsFavorite.Params> {

    private FavoriteMovieRepository repository;

    @Inject
    RemoveMovieIsFavorite(FavoriteMovieRepository repository, ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<Boolean> buildUseCaseObservable(Params params) {
        return repository.removeFavoriteMovie(params.movieId);
    }

    public static class Params {

        private String movieId;

        Params(String movieId) {
            this.movieId = movieId;
        }

        public static Params forRemoveMovieIsFavorite(String movieId) {
            return new RemoveMovieIsFavorite.Params(movieId);
        }
    }
}
