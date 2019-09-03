package id.dana.onboarding.domain.interactor;


import com.fernandocejas.arrow.checks.Preconditions;

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
public class GetPopularMovieList extends UseCase<List<Movie>, GetPopularMovieList.Params> {

    private final MovieRepository movieRepository;

    @Inject
    GetPopularMovieList(MovieRepository movieRepository, ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    @Override
    Observable<List<Movie>> buildUseCaseObservable(GetPopularMovieList.Params params) {
        Preconditions.checkNotNull(params);
        return this.movieRepository.retrofitPopularMovies(params.page);
    }

    public static final class Params {

        private final int page;

        public Params(int page) {
            this.page = page;
        }

        public static Params forMovie(int page) {
            return new Params(page);
        }
    }
}
