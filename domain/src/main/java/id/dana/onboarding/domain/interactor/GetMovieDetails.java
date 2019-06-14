package id.dana.onboarding.domain.interactor;

import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;

import id.dana.onboarding.domain.Movie;
import id.dana.onboarding.domain.executor.PostExecutionThread;
import id.dana.onboarding.domain.executor.ThreadExecutor;
import id.dana.onboarding.domain.repository.MovieRepository;
import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link Movie}.
 */
public class GetMovieDetails extends UseCase<Movie, GetMovieDetails.Params> {

    private final MovieRepository movieRepository;

    @Inject
    GetMovieDetails(MovieRepository movieRepository, ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    @Override
    Observable<Movie> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return this.movieRepository.retrofitDetailMovie(params.movieId);
    }

    public static final class Params {

        private final String movieId;

        private Params(String movieId) {
            this.movieId = movieId;
        }

        public static Params forMovie(String movieId) {
            return new Params(movieId);
        }
    }
}
