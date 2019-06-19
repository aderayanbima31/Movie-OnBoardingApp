package id.dana.onboarding.movie.presenter;

import android.widget.Toast;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import id.dana.onboarding.domain.FavoriteMovie;
import id.dana.onboarding.domain.exception.DefaulErrorBundle;
import id.dana.onboarding.domain.exception.ErrorBundle;
import id.dana.onboarding.domain.interactor.DefaultObserver;
import id.dana.onboarding.domain.interactor.GetFavoriteMovieList;
import id.dana.onboarding.movie.exception.ErrorMessageFactory;
import id.dana.onboarding.movie.mapper.FavoriteMovieModelDataMapper;
import id.dana.onboarding.movie.model.FavoriteMovieModel;
import id.dana.onboarding.movie.view.FavoriteMovieListView;
import io.reactivex.annotations.NonNull;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version FavoriteMovieListPresenter, v 0.1 2019-06-17 13:37 by Derayan Bima A
 */
public class FavoriteMovieListPresenter implements Presenter {

    private FavoriteMovieListView favoriteMovieListView;

    private FavoriteMovieModelDataMapper favoriteMovieModelDataMapper;

    private GetFavoriteMovieList getFavoriteMovieListUseCase;

    private static final String TAG = PopularMovieListPresenter.class.getName();

    @Inject
    public FavoriteMovieListPresenter(FavoriteMovieModelDataMapper favoriteMovieModelDataMapper,
        GetFavoriteMovieList getFavoriteMovieList){

        this.favoriteMovieModelDataMapper = favoriteMovieModelDataMapper;
        this.getFavoriteMovieListUseCase = getFavoriteMovieList;
    }

    public void setView(@NonNull FavoriteMovieListView view){
        this.favoriteMovieListView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getFavoriteMovieListUseCase.dispose();
        this.favoriteMovieListView = null;
    }

    public void initialize(){
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
                showErrorMessage(new DefaulErrorBundle((Exception) e));
            }
        }, null);
    }

    private void showViewLoading() {
        this.favoriteMovieListView.showLoading();
    }

    private void hideViewLoading() {
        this.favoriteMovieListView.hideLoading();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory
            .createErrorMessage(this.favoriteMovieListView.context(), errorBundle.getException());
        this.favoriteMovieListView.showError(errorMessage);
    }

    public void onMovieClicked(FavoriteMovieModel favoriteMovieModel) {
        this.favoriteMovieListView.viewFavoriteMovie(favoriteMovieModel);
    }
}
