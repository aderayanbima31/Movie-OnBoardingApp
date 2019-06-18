package id.dana.onboarding.movie.presenter;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import id.dana.onboarding.domain.Movie;
import id.dana.onboarding.domain.exception.DefaulErrorBundle;
import id.dana.onboarding.domain.exception.ErrorBundle;
import id.dana.onboarding.domain.interactor.DefaultObserver;
import id.dana.onboarding.domain.interactor.GetPopularMovieList;
import id.dana.onboarding.movie.exception.ErrorMessageFactory;
import id.dana.onboarding.movie.internal.PerActivity;
import id.dana.onboarding.movie.mapper.MovieModelDataMapper;
import id.dana.onboarding.movie.model.MovieModel;
import id.dana.onboarding.movie.view.MovieListView;
import io.reactivex.annotations.NonNull;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version PopularMovieListPresenter, v 0.1 2019-06-17 10:32 by Derayan Bima A
 */
@PerActivity
public class PopularMovieListPresenter implements Presenter {

    private static final String TAG = PopularMovieListPresenter.class.getName();

    private GetPopularMovieList getPopularMovieListUseCase;

    private MovieListView movieListView;

    private MovieModelDataMapper movieModelDataMapper;

    @Inject
    public PopularMovieListPresenter(GetPopularMovieList getPopularMovieListUseCase,
        MovieModelDataMapper movieModelDataMapper) {
        this.getPopularMovieListUseCase = getPopularMovieListUseCase;
        this.movieModelDataMapper = movieModelDataMapper;
    }

    public void setView(@NonNull MovieListView view) {
        this.movieListView = view;
    }

    @Override
    public void resume() {
        //No Implementation
    }

    @Override
    public void pause() {
        //No implementation
    }

    @Override
    public void destroy() {
        this.getPopularMovieListUseCase.dispose();
        this.movieListView = null;
    }

    /**
     * Initializes the presenter by start retrieving the movie list/
     */
    public void initialize() {
        this.loadMovieList();
    }

    /**
     * Loads all movies.
     */
    private void loadMovieList() {
        this.getPopularMovieList();
    }

    private void showViewLoading() {
        this.movieListView.showLoading();
    }

    private void getPopularMovieList() {
        this.showViewLoading();
        this.getPopularMovieListUseCase.execute(new DefaultObserver<List<Movie>>() {
            @Override
            public void onNext(List<Movie> movies) {
                Collection<MovieModel> movieModels = movieModelDataMapper.transform(movies);
                movieListView.renderMovieList(movieModels);
            }

            @Override
            public void onError(Throwable e) {
                hideViewLoading();
                showErrorMessage(new DefaulErrorBundle((Exception) e));
            }

            @Override
            public void onComplete() {
                hideViewLoading();
            }
        }, null);
    }

    private void hideViewLoading() {
        this.movieListView.hideLoading();
    }

    public void onMovieClicked(MovieModel movieModel) {
        this.movieListView.viewMovie(movieModel);
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory
            .createErrorMessage(this.movieListView.context(), errorBundle.getException());
        this.movieListView.showError(errorMessage);
    }
}
