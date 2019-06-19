package id.dana.onboarding.movie.view.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.Collection;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import id.dana.onboarding.movie.R;
import id.dana.onboarding.movie.internal.component.MovieComponent;
import id.dana.onboarding.movie.model.FavoriteMovieModel;
import id.dana.onboarding.movie.presenter.FavoriteMovieListPresenter;
import id.dana.onboarding.movie.view.FavoriteMovieListView;
import id.dana.onboarding.movie.view.adapter.FavoriteMovieAdapter;
import id.dana.onboarding.movie.view.decoration.GridSpacingItemDecoration;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version FavoriteMovieFragment, v 0.1 2019-06-14 16:24 by Derayan Bima A
 */
public class FavoriteMovieFragment extends BaseFragment implements FavoriteMovieListView {

    @Inject
    FavoriteMovieAdapter adapter;

    @BindView(R.id.pb_loading_process_favorite_movie)
    ProgressBar pbLoadProcess;

    @Inject
    FavoriteMovieListPresenter presenter;

    @BindView(R.id.rv_favorite_movie)
    RecyclerView rvFavoriteMovie;

    private MovieListListener movieListListener;

    private FavoriteMovieAdapter.OnItemClickListener onItemClickListener = favoriteMovieModel -> {
        if (FavoriteMovieFragment.this.presenter != null && favoriteMovieModel != null) {
            FavoriteMovieFragment.this.presenter.onMovieClicked(favoriteMovieModel);
        }
    };

    private Unbinder unbinder;

    public FavoriteMovieFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MovieListListener) {
            this.movieListListener = (MovieListListener) context;

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(MovieComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View fragmentView = inflater
            .inflate(R.layout.fragment_favorite_movie, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        setUpRecyclerView();

        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter.setView(this);
        if (savedInstanceState == null) {
            this.loadMovieList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.presenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rvFavoriteMovie.setAdapter(null);
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.movieListListener = null;
    }

    private void loadMovieList() {
        presenter.initialize();
    }

    private void setUpRecyclerView() {
        adapter.setOnItemClickListener(onItemClickListener);
        rvFavoriteMovie.setLayoutManager(new GridLayoutManager(context(), 2));
        rvFavoriteMovie.addItemDecoration(new GridSpacingItemDecoration(2, convertDpToPx(), true));
        rvFavoriteMovie.setItemAnimator(new DefaultItemAnimator());
        rvFavoriteMovie.setAdapter(adapter);
    }

    private int convertDpToPx() {
        Resources r = getResources();
        return Math.round(
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, r.getDisplayMetrics()));

    }

    @Override
    public void showLoading() {
        if (pbLoadProcess != null) {
            pbLoadProcess.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if (pbLoadProcess != null) {
            pbLoadProcess.setVisibility(View.GONE);
        }
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void renderFavoriteMovieList(
        Collection<FavoriteMovieModel> favoriteMovieModelCollection) {
        if (favoriteMovieModelCollection != null) {
            this.adapter.setMoviesCollection(favoriteMovieModelCollection);
        }

    }

    @Override
    public void viewFavoriteMovie(FavoriteMovieModel favoriteMovieModel) {
        if (movieListListener != null) {
            movieListListener.onMovieClicked(favoriteMovieModel);
        }
    }

    private interface MovieListListener {

        void onMovieClicked(final FavoriteMovieModel favoriteMovieModel);

    }
}

