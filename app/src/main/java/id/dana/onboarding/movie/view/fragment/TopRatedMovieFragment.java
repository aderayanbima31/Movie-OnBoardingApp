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
import id.dana.onboarding.movie.model.MovieModel;
import id.dana.onboarding.movie.presenter.TopRatedListPresenter;
import id.dana.onboarding.movie.view.MovieListView;
import id.dana.onboarding.movie.view.adapter.TopRatedMovieAdapter;
import id.dana.onboarding.movie.view.decoration.GridSpacingItemDecoration;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version TopRatedMovieFragment, v 0.1 2019-06-14 16:08 by Derayan Bima A
 */
public class TopRatedMovieFragment extends BaseFragment implements MovieListView {

    @BindView(R.id.pb_loading_process_top_rated_movie)
    ProgressBar pbLoadProcess;

    @BindView(R.id.rv_top_rated_movie)
    RecyclerView rvTopRatedMovie;

    @Inject
    TopRatedListPresenter topRatedListPresenter;

    @Inject
    TopRatedMovieAdapter topRatedMovieAdapter;

    private MovieListListener movieListListener;

    private TopRatedMovieAdapter.OnItemClickListener onItemClickListener =
        new TopRatedMovieAdapter.OnItemClickListener() {
            @Override
            public void onMovieItemClicked(MovieModel movieModel) {
                if (topRatedListPresenter != null && movieModel != null) {
                    topRatedListPresenter.onMovieClicked(movieModel);
                }
            }
        };

    private Unbinder unbinder;

    public TopRatedMovieFragment() {
        // Required empty public constructor
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
            .inflate(R.layout.fragment_top_rated_movie, container, false);
        ButterKnife.bind(this, fragmentView);
        unbinder = ButterKnife.bind(this, fragmentView);
        setUpRecyclerView();

        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        topRatedListPresenter.setView(this);
        if (savedInstanceState == null) {
            loadMovieList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.topRatedListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.topRatedListPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rvTopRatedMovie.setAdapter(null);
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        topRatedListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unbinder.unbind();
        topRatedListPresenter = null;
    }

    private void loadMovieList() {
        topRatedListPresenter.initialize();
    }

    private void setUpRecyclerView() {
        topRatedMovieAdapter.setOnItemClickListener(onItemClickListener);
        rvTopRatedMovie.setLayoutManager(new GridLayoutManager(context(), 2));
        rvTopRatedMovie.addItemDecoration(new GridSpacingItemDecoration(2, convertDpToPx(), true));
        rvTopRatedMovie.setItemAnimator(new DefaultItemAnimator());
        rvTopRatedMovie.setAdapter(topRatedMovieAdapter);
    }

    private int convertDpToPx() {
        Resources r = getResources();
        return Math.round(
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, r.getDisplayMetrics()));
    }

    @Override
    public void showLoading() {
        pbLoadProcess.setVisibility(View.VISIBLE);
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
        showToastMessage(message);
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void renderMovieList(Collection<MovieModel> movieModelCollection) {
        if (movieModelCollection != null) {
            topRatedMovieAdapter.setMoviesCollection(movieModelCollection);
        }
    }

    @Override
    public void viewMovie(MovieModel movieModel) {
        if (movieListListener != null) {
            movieListListener.onMovieClicked(movieModel);
        }
    }

    public interface MovieListListener {

        void onMovieClicked(final MovieModel movieModel);
    }
}

