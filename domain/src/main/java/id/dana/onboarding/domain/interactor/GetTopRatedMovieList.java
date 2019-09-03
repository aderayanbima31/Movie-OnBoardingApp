package id.dana.onboarding.domain.interactor;


import java.util.List;

import javax.inject.Inject;

import id.dana.onboarding.domain.Movie;
import id.dana.onboarding.domain.executor.PostExecutionThread;
import id.dana.onboarding.domain.executor.ThreadExecutor;
import id.dana.onboarding.domain.repository.MovieRepository;
import io.reactivex.Observable;

public class GetTopRatedMovieList extends UseCase<List<Movie>, GetTopRatedMovieList.Params> {

    private final MovieRepository movieRepository;

    @Inject
    GetTopRatedMovieList(MovieRepository movieRepository, ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    @Override
    Observable<List<Movie>> buildUseCaseObservable(GetTopRatedMovieList.Params params) {
        return this.movieRepository.retrofitTopRateMovies(params.page);
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
