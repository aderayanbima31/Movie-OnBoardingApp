package id.dana.onboarding.domain.interactor;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;

import javax.inject.Inject;

import id.dana.onboarding.domain.Video;
import id.dana.onboarding.domain.executor.PostExecutionThread;
import id.dana.onboarding.domain.executor.ThreadExecutor;
import id.dana.onboarding.domain.repository.MovieRepository;
import io.reactivex.Observable;


public class GetVideoTrailerById extends UseCase<List<Video>, GetVideoTrailerById.Params> {

    private MovieRepository repository;

    @Inject
    GetVideoTrailerById(MovieRepository repository, ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<List<Video>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return repository.retrofitVideoTrailer(params.movieId);
    }

    public static final class Params {

        private final int movieId;

        private Params(int movieId) {
            this.movieId = movieId;
        }

        public static Params forVideoTrailer(int movieId) {
            return new Params(movieId);
        }

    }
}
