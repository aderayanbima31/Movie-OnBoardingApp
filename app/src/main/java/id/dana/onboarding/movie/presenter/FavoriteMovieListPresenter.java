package id.dana.onboarding.movie.presenter;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import id.dana.onboarding.domain.FavoriteMovie;
import id.dana.onboarding.domain.interactor.DefaultObserver;
import id.dana.onboarding.domain.interactor.GetFavoriteMovieList;
import id.dana.onboarding.movie.mapper.FavoriteMovieModelDataMapper;
import id.dana.onboarding.movie.model.FavoriteMovieModel;
import id.dana.onboarding.movie.view.FavoriteMovieListView;
import io.reactivex.annotations.NonNull;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version FavoriteMovieListPresenter, v 0.1 2019-06-17 13:37 by Derayan Bima A
 */
public class FavoriteMovieListPresenter implements Presenter {

    private static final String TAG = PopularMovieListPresenter.class.getName();

    private FavoriteMovieListView favoriteMovieListView;

    private FavoriteMovieModelDataMapper favoriteMovieModelDataMapper;

    private GetFavoriteMovieList getFavoriteMovieListUseCase;

    @Inject
    public FavoriteMovieListPresenter(FavoriteMovieModelDataMapper favoriteMovieModelDataMapper,
        GetFavoriteMovieList getFavoriteMovieList) {

        this.favoriteMovieModelDataMapper = favoriteMovieModelDataMapper;
        this.getFavoriteMovieListUseCase = getFavoriteMovieList;
    }

    public void setView(@NonNull FavoriteMovieListView view) {
        this.favoriteMovieListView = view;
    }

    @Override
    public void resume() {
        //No Implementation
    }

    @Override
    public void pause() {
        //No Implementation
    }

    @Override
    public void destroy() {
        this.getFavoriteMovieListUseCase.dispose();
        this.favoriteMovieListView = null;
    }

    public void initialize() {
        loadMovieList();
    }

    private void loadMovieList() {
        this.showViewLoading();
        this.getFavoriteMovieList();
    }

    private void getFavoriteMovieList() {
        this.getFavoriteMovieListUseCase.execute(new DefaultObserver<List<FavoriteMovie>>() {
            @Override
            public void onNext(List<FavoriteMovie> favoriteMovies) {

                Collection<FavoriteMovieModel> favoriteMovieModels = favoriteMovieModelDataMapper
                    .transform(favoriteMovies);
                favoriteMovieListView.renderFavoriteMovieList(favoriteMovieModels);
                hideViewLoading();
            }

            @Override
            public void onError(Throwable e) {
                hideViewLoading();
                favoriteMovieListView.showError("Belum Ada Film Favorite");
            }
        }, null);
    }

    private void showViewLoading() {
        this.favoriteMovieListView.showLoading();
    }

    private void hideViewLoading() {
        this.favoriteMovieListView.hideLoading();
    }

    public void onMovieClicked(FavoriteMovieModel favoriteMovieModel) {
        this.favoriteMovieListView.viewFavoriteMovie(favoriteMovieModel);
    }
}
