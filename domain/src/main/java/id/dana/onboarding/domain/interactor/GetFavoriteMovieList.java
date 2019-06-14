package id.dana.onboarding.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import id.dana.onboarding.domain.FavoriteMovie;
import id.dana.onboarding.domain.executor.PostExecutionThread;
import id.dana.onboarding.domain.executor.ThreadExecutor;
import id.dana.onboarding.domain.repository.FavoriteMovieRepository;
import io.reactivex.Observable;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version GetFavoriteMovieList, v 0.1 2019-06-14 11:02 by Derayan Bima A
 */
public class GetFavoriteMovieList extends UseCase<List<FavoriteMovie>, Void>  {

    private FavoriteMovieRepository repository;

    @Inject
    GetFavoriteMovieList(FavoriteMovieRepository favoriteMovieRepository,
        ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = favoriteMovieRepository;
    }

    @Override
    Observable<List<FavoriteMovie>> buildUseCaseObservable(Void aVoid) {
        return this.repository.getFavoriteMovie();
    }
}
