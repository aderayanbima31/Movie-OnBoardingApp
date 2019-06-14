package id.dana.onboarding.domain.interactor;


import java.util.List;

import javax.inject.Inject;

import id.dana.onboarding.domain.Movie;
import id.dana.onboarding.domain.executor.PostExecutionThread;
import id.dana.onboarding.domain.executor.ThreadExecutor;
import id.dana.onboarding.domain.repository.MovieRepository;
import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Movie}
 */
public class GetPopularMovieList extends UseCase<List<Movie>, Void> {

    private final MovieRepository movieRepository;

    @Inject
    GetPopularMovieList(MovieRepository movieRepository, ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    @Override
    Observable<List<Movie>> buildUseCaseObservable(Void aVoid) {
        return this.movieRepository.retrofitPopularMovies();
    }
}
