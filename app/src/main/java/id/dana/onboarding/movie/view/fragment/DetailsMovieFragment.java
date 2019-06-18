package id.dana.onboarding.movie.view.fragment;

import com.bumptech.glide.Glide;
import com.fernandocejas.arrow.checks.Preconditions;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import id.dana.onboarding.movie.R;
import id.dana.onboarding.movie.internal.component.MovieComponent;
import id.dana.onboarding.movie.model.FavoriteMovieModel;
import id.dana.onboarding.movie.model.MovieModel;
import id.dana.onboarding.movie.model.MovieTrailerModel;
import id.dana.onboarding.movie.presenter.PopularMovieDetailsPresenter;
import id.dana.onboarding.movie.view.MovieDetailsView;
import id.dana.onboarding.movie.view.adapter.MovieTrailerAdapter;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version DetailsMovieFragment, v 0.1 2019-06-14 16:14 by Derayan Bima A
 */
public class DetailsMovieFragment extends BaseFragment implements MovieDetailsView {

    private static final String PARAM_MOVIE_ID = "param_movie_id";

    private static final String TAG = DetailsMovieFragment.class.getSimpleName();

    @BindView(R.id.iv_movie_like_favorite)
    ImageView icMovieFavorite;

    @BindView(R.id.iv_movie_poster_detail)
    ImageView ivMoviePoster;

    @BindView(R.id.pb_movie_load_process_detail)
    ProgressBar pbLoadMovie;

    @BindView(R.id.rv_video_trailer_url)
    RecyclerView rvVideoTrailerUrl;

    @Inject
    PopularMovieDetailsPresenter popularMovieDetailsPresenter;

    @Inject
    MovieTrailerAdapter videoTrailerAdapter;

    @BindView(R.id.tv_movie_overview_detail)
    TextView tvOverview;

    @BindView(R.id.tv_movie_release_date_detail)
    TextView tvReleaseDate;

    @BindView(R.id.tv_movie_runtime_detail)
    TextView tvRuntime;

    @BindView(R.id.tv_movie_title_detail)
    TextView tvTitle;

    @BindView(R.id.tv_movie_vote_average_detail)
    TextView tvVoteAverage;

    private boolean isFavoriteMovie = false;

    private String movieRuntime;

    private String movieTitle;

    private String overview;

    private String posterPath;

    private String releaseDate;

    private Unbinder unbinder;

    private Double voteAverage;

    private MovieTrailerListListener movieTrailerListListener;

    private MovieTrailerAdapter.OnItemClickListener onItemClickListener = videoModel -> {
        if (popularMovieDetailsPresenter != null && videoModel != null) {
            popularMovieDetailsPresenter.onVideoTrailerClicker(videoModel);

        }
    };

    public DetailsMovieFragment() {
        setRetainInstance(true);
    }

    public static DetailsMovieFragment forMovie(String movieId) {
        final DetailsMovieFragment detailsMovieFragment = new DetailsMovieFragment();
        final Bundle arguments = new Bundle();
        arguments.putString(PARAM_MOVIE_ID, movieId);
        detailsMovieFragment.setArguments(arguments);
        return detailsMovieFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(MovieComponent.class).inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View fragmentView = inflater
            .inflate(R.layout.fragment_details_movie, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        setUpRecyclerView();

        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        popularMovieDetailsPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadMovieDetails();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        popularMovieDetailsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        popularMovieDetailsPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        popularMovieDetailsPresenter.destroy();
    }

    private void loadMovieDetails() {
        if (popularMovieDetailsPresenter != null) {
            popularMovieDetailsPresenter.initialize(currentMovieId());

        }
    }

    private String currentMovieId() {
        final Bundle arguments = getArguments();
        Preconditions.checkNotNull(arguments, "Fragment arguments cannot be null!");
        return arguments.getString(PARAM_MOVIE_ID);
    }

    @Override
    public void renderMovie(MovieModel movieModel) {
        if (movieModel != null) {
            movieTitle = movieModel.getTitle();
            posterPath = movieModel.getUrlPoster();
            voteAverage = Double.valueOf(movieModel.getMovieRate());
            releaseDate = movieModel.getReleaseDate();
            movieRuntime = movieModel.getMovieDuration();
            overview = movieModel.getSynopsis();

            tvTitle.setText(movieTitle);
            tvReleaseDate.setText(releaseDate);
            tvRuntime.setText(String.format("%s min", movieRuntime));
            tvVoteAverage.setText(String.format("%s/10", voteAverage));
            tvOverview.setText(overview);
            ;
            Glide.with(context()).load(posterPath).into(ivMoviePoster);
        }
    }

    @Override
    public void onLikedMovie(boolean success) {
        if (success) {
            showToastMessage("Movie liked");
        }
    }

    @Override
    public void onRemoveMovie(boolean remove) {
        if (remove) {
            showToastMessage("Remove liked");
        }
    }

    @Override
    public void isMovieFavorite() {
        icMovieFavorite
            .setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_red_24dp));
        isFavoriteMovie = true;
    }

    @Override
    public void showMovieTrailer(List<MovieTrailerModel> movieTrailerModels) {
        if (movieTrailerModels != null) {
            videoTrailerAdapter.setVideoTrailerList(movieTrailerModels);
            Log.e(TAG, "showVideoTrailer: " + movieTrailerModels.size());
        }
    }


    @Override
    public void showLoading() {
        pbLoadMovie.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbLoadMovie.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        //No implementation
    }

    @Override
    public void hideRetry() {
        //No implementation
    }

    @Override
    public void showError(String message) {
        showToastMessage(message);
    }

    @Override
    public Context context() {
        return getContext();
    }

    @OnClick(R.id.iv_movie_like_favorite)
    public void favoriteTheMovie() {
        if (!isFavoriteMovie) {
            isFavoriteMovie = true;
            FavoriteMovieModel favoriteMovieModel = new FavoriteMovieModel(currentMovieId());
            favoriteMovieModel.setOriginalTitle(movieTitle);
            favoriteMovieModel.setPosterPath(posterPath);
            favoriteMovieModel.setFavorite(isFavoriteMovie);
            favoriteMovieModel.setVoteAverage(voteAverage);
            favoriteMovieModel.setFavorite(isFavoriteMovie);
            popularMovieDetailsPresenter.addMovieFavorite(favoriteMovieModel);
            icMovieFavorite
                .setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_red_24dp));
        } else {
            popularMovieDetailsPresenter.removeMovieFavorite(currentMovieId());
            icMovieFavorite
                .setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_gray_24dp));
            isFavoriteMovie = false;
        }
    }

    private void setUpRecyclerView() {
        videoTrailerAdapter.setOnItemClickListener(onItemClickListener);
        rvVideoTrailerUrl.setHasFixedSize(true);
        rvVideoTrailerUrl.setLayoutManager(new LinearLayoutManager(context()));
        rvVideoTrailerUrl.setItemAnimator(new DefaultItemAnimator());
        rvVideoTrailerUrl.setAdapter(videoTrailerAdapter);
    }

    public interface MovieTrailerListListener {

        void onUrlClicked(final MovieTrailerModel movieTrailerModel);
    }
}
