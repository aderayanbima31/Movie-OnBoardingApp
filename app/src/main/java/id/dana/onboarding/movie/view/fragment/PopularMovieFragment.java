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
import id.dana.onboarding.movie.presenter.PopularMovieListPresenter;
import id.dana.onboarding.movie.view.MovieListView;
import id.dana.onboarding.movie.view.adapter.PopularMovieAdapter;
import id.dana.onboarding.movie.view.decoration.GridSpacingItemDecoration;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version PopularMovieFragment, v 0.1 2019-06-14 16:05 by Derayan Bima A
 */
public class PopularMovieFragment extends BaseFragment implements MovieListView {

    @BindView(R.id.pb_loading_process_popular_movie)
    ProgressBar pbLoadingProgress;

    @Inject
    PopularMovieAdapter popularMovieAdapter;

    @Inject
    PopularMovieListPresenter popularMovieListPresenter;

    @BindView(R.id.rv_popular_movie)
    RecyclerView rvPopularMovie;

    private MovieListListener movieListListener;

    private boolean itShouldLoadMore = true;

    private PopularMovieAdapter.OnItemClickListener onItemClickListener = movieModel -> {
        if (PopularMovieFragment.this.popularMovieListPresenter != null && movieModel != null) {
            PopularMovieFragment.this.popularMovieListPresenter.onMovieClicked(movieModel);
        }
    };

    private Unbinder unbinder;

    public PopularMovieFragment() {
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
            .inflate(R.layout.fragment_popular_movie, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        setUpRecyclerView();

        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.popularMovieListPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadMovieList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.popularMovieListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.popularMovieListPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rvPopularMovie.setAdapter(null);
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.popularMovieListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.movieListListener = null;
        popularMovieAdapter.clearList();
    }

    private void loadMovieList() {
        popularMovieListPresenter.initialize(popularMovieAdapter.getPageCount());
    }

    private void setUpRecyclerView() {
        popularMovieAdapter.setOnItemClickListener(onItemClickListener);
        rvPopularMovie.setLayoutManager(new GridLayoutManager(context(), 2));
        rvPopularMovie.addItemDecoration(new GridSpacingItemDecoration(2, convertDpToPx(), true));
        rvPopularMovie.setItemAnimator(new DefaultItemAnimator());
        rvPopularMovie.setAdapter(popularMovieAdapter);
        rvPopularMovie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0){
                    if (!rvPopularMovie.canScrollVertically(RecyclerView.FOCUS_DOWN)){
                        if (itShouldLoadMore){
                            loadMore(popularMovieAdapter.getPageCount());
                        }
                    }
                }
            }
        });
    }

    private void loadMore(int pageCount) {
        showToastMessage("Load More");
        popularMovieListPresenter.initialize(pageCount);
    }

    private int convertDpToPx() {
        Resources r = getResources();
        return Math.round(
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, r.getDisplayMetrics()));

    }

    @Override
    public void showLoading() {
        this.pbLoadingProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        if (pbLoadingProgress != null) {
            pbLoadingProgress.setVisibility(View.GONE);
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
    public void renderMovieList(Collection<MovieModel> movieModelCollection) {
        if (movieModelCollection != null) {
            this.popularMovieAdapter.setMoviesCollection(movieModelCollection);
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
