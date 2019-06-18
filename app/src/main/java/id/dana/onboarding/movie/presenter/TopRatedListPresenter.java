package id.dana.onboarding.movie.presenter;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import id.dana.onboarding.domain.Movie;
import id.dana.onboarding.domain.exception.DefaulErrorBundle;
import id.dana.onboarding.domain.exception.ErrorBundle;
import id.dana.onboarding.domain.interactor.DefaultObserver;
import id.dana.onboarding.domain.interactor.GetTopRatedMovieList;
import id.dana.onboarding.movie.exception.ErrorMessageFactory;
import id.dana.onboarding.movie.internal.PerActivity;
import id.dana.onboarding.movie.mapper.MovieModelDataMapper;
import id.dana.onboarding.movie.model.MovieModel;
import id.dana.onboarding.movie.view.MovieListView;

@PerActivity
public class TopRatedListPresenter implements Presenter {

    private static final String TAG = TopRatedListPresenter.class.getSimpleName();

    private GetTopRatedMovieList getTopRatedMovieListUseCase;

    private MovieListView movieListView;

    private MovieModelDataMapper movieModelDataMapper;

    @Inject
    public TopRatedListPresenter(GetTopRatedMovieList getTopRatedMovieList,
        MovieModelDataMapper movieModelDataMapper) {
        this.getTopRatedMovieListUseCase = getTopRatedMovieList;
        this.movieModelDataMapper = movieModelDataMapper;
    }

    public void setView(MovieListView movieListView) {
        this.movieListView = movieListView;
    }

    @Override
    public void resume() {
        //No implementation
    }

    @Override
    public void pause() {
        //No implementation
    }

    @Override
    public void destroy() {
        getTopRatedMovieListUseCase.dispose();
        movieListView = null;
    }

    public void initialize() {
        this.loadTopRatedMovieList();
    }

    private void loadTopRatedMovieList() {
        getTopRateMovieList();
    }

    private void showViewLoading() {
        movieListView.showLoading();
    }

    private void getTopRateMovieList() {
        showViewLoading();
        getTopRatedMovieListUseCase.execute(new DefaultObserver<List<Movie>>() {
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

    public void onMovieClicked(MovieModel movieModel) {
        movieListView.viewMovie(movieModel);
    }

    private void hideViewLoading() {
        movieListView.hideLoading();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String message = ErrorMessageFactory
            .createErrorMessage(movieListView.context(), errorBundle.getException());
        movieListView.showError(message);
    }
}
