package id.dana.onboarding.movie.presenter;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import id.dana.onboarding.domain.FavoriteMovie;
import id.dana.onboarding.domain.Movie;
import id.dana.onboarding.domain.Video;
import id.dana.onboarding.domain.exception.DefaulErrorBundle;
import id.dana.onboarding.domain.exception.ErrorBundle;
import id.dana.onboarding.domain.interactor.AddFavoriteMovie;
import id.dana.onboarding.domain.interactor.CheckMovieIsFavorite;
import id.dana.onboarding.domain.interactor.DefaultObserver;
import id.dana.onboarding.domain.interactor.GetMovieDetails;
import id.dana.onboarding.domain.interactor.GetVideoTrailerById;
import id.dana.onboarding.domain.interactor.RemoveMovieIsFavorite;
import id.dana.onboarding.movie.exception.ErrorMessageFactory;
import id.dana.onboarding.movie.mapper.MovieModelDataMapper;
import id.dana.onboarding.movie.mapper.MovieTrailerModelDataMapper;
import id.dana.onboarding.movie.model.FavoriteMovieModel;
import id.dana.onboarding.movie.model.MovieModel;
import id.dana.onboarding.movie.model.MovieTrailerModel;
import id.dana.onboarding.movie.view.MovieDetailsView;
import io.reactivex.annotations.NonNull;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version PopularMovieDetailsPresenter, v 0.1 2019-06-17 11:25 by Derayan Bima A
 */
public class PopularMovieDetailsPresenter implements Presenter {

    private static final String TAG = PopularMovieDetailsPresenter.class.getSimpleName();

    private RemoveMovieIsFavorite removeMovieIsFavoriteUseCase;

    private AddFavoriteMovie addFavoriteMovieUseCase;

    private GetVideoTrailerById getVideoTrailerByIdUseCase;

    private MovieDetailsView movieDetailsView;

    private MovieModelDataMapper movieModelDataMapper;

    private MovieTrailerModelDataMapper movieTrailerModelDataMapper;

    private GetMovieDetails getMovieDetailsUseCase;

    private CheckMovieIsFavorite checkMovieIsFavoriteUseCase;

    @Inject
    public PopularMovieDetailsPresenter(GetMovieDetails getMovieDetailsUseCase,
        AddFavoriteMovie addFavoriteMovie, CheckMovieIsFavorite checkMovieIsFavoriteUseCase,
        RemoveMovieIsFavorite removeMovieIsFavoriteUseCase,
        MovieModelDataMapper movieModelDataMapper,
        MovieTrailerModelDataMapper movieTrailerModelDataMapper,
        GetVideoTrailerById getVideoTrailerByIdUseCase) {
        this.getMovieDetailsUseCase = getMovieDetailsUseCase;
        this.addFavoriteMovieUseCase = addFavoriteMovie;
        this.movieModelDataMapper = movieModelDataMapper;
        this.checkMovieIsFavoriteUseCase = checkMovieIsFavoriteUseCase;
        this.removeMovieIsFavoriteUseCase = removeMovieIsFavoriteUseCase;
        this.movieTrailerModelDataMapper = movieTrailerModelDataMapper;
        this.getVideoTrailerByIdUseCase = getVideoTrailerByIdUseCase;
    }

    public void setView(@NonNull MovieDetailsView view) {
        this.movieDetailsView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

        this.getMovieDetailsUseCase.dispose();
        this.addFavoriteMovieUseCase.dispose();
        this.getVideoTrailerByIdUseCase.dispose();
        this.movieDetailsView = null;
    }

    /**
     * Initializes the presenter by showing/hiding proper views and retrieving movie details.
     */
    public void initialize(String movieId) {
        this.hideViewRetry();
        this.showViewLoading();
        if (movieId != null) {
            this.getMovieDetails(movieId);
            //this.getVideoTrailer(movieId);
            this.checkMovieIsFavorite(movieId);
        }
    }

    private void getMovieDetails(String movieId) {
        this.getMovieDetailsUseCase
            .execute(new DefaultObserver<Movie>() {

                @Override
                public void onNext(Movie movie) {
                    MovieModel movieModel = movieModelDataMapper.transform(movie);
                    movieDetailsView.renderMovie(movieModel);
                    hideViewLoading();

                }

                @Override
                public void onError(Throwable throwable) {
                    hideViewLoading();
                    showErrorMessage(new DefaulErrorBundle((Exception) throwable));
                }
            }, GetMovieDetails.Params.forMovie(movieId));
    }

    public void addMovieFavorite(FavoriteMovieModel favoriteMovieModel) {
        addFavoriteMovieUseCase.execute(new DefaultObserver<Boolean>() {

            @Override
            public void onNext(Boolean success) {
                movieDetailsView.onLikedMovie(success);
            }

            @Override
            public void onError(Throwable throwable) {
                showErrorMessage(new DefaulErrorBundle((Exception) throwable));
            }

        }, AddFavoriteMovie.Params.forAddFavoriteMovie(favoriteMovieModel.getMovieId(),
            favoriteMovieModel.getVoteAverage(), favoriteMovieModel.getPosterPath(),
            favoriteMovieModel.getOriginalTitle(), favoriteMovieModel.isFavorite()));
    }

    public void removeMovieFavorite(String movieId) {
        removeMovieIsFavoriteUseCase.execute(new DefaultObserver<Boolean>() {
            @Override
            public void onNext(Boolean remove) {
                movieDetailsView.onRemoveMovie(remove);
            }

            @Override
            public void onError(Throwable e) {
                showErrorMessage(new DefaulErrorBundle((Exception) e));
            }
        }, RemoveMovieIsFavorite.Params.forRemoveMovieIsFavorite(movieId));
    }

    private void checkMovieIsFavorite(String movieId) {
        checkMovieIsFavoriteUseCase.execute(new DefaultObserver<FavoriteMovie>() {

            @Override
            public void onComplete() {
                movieDetailsView.isMovieFavorite();
            }
        }, CheckMovieIsFavorite.Params.forCheckMovieIsFavorite(movieId));
    }

    private void getVideoTrailer(String movieId) {
        getVideoTrailerByIdUseCase.execute(new DefaultObserver<List<Video>>() {
            @Override
            public void onNext(List<Video> videoList) {
                Log.e(TAG, "onNext" + videoList.size());
                List<MovieTrailerModel> movieModels = movieTrailerModelDataMapper.mapper(videoList);
                Log.e(TAG, "onNext :" + movieModels.size());
                movieDetailsView.showMovieTrailer(movieModels);
            }

            @Override
            public void onError(Throwable throwable) {

                Log.e(TAG, "onError: " + throwable.getMessage());
                showErrorMessage(new DefaulErrorBundle((Exception) throwable));
            }
        }, GetVideoTrailerById.Params.forVideoTrailer(Integer.parseInt(movieId)));
    }

    public void onVideoTrailerClicker(MovieTrailerModel movieTrailerModel) {
        //Todo open youtube app
    }

    private void hideViewRetry() {
        this.movieDetailsView.hideRetry();
    }

    private void showViewLoading() {
        this.movieDetailsView.showLoading();
    }

    private void hideViewLoading() {
        this.movieDetailsView.hideLoading();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory
            .createErrorMessage(this.movieDetailsView.context(), errorBundle.getException());
        this.movieDetailsView.showError(errorMessage);
    }
}

