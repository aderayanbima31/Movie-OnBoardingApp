package id.dana.onboarding.movie.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.dana.onboarding.movie.R;
import id.dana.onboarding.movie.internal.HasComponent;
import id.dana.onboarding.movie.internal.component.DaggerMovieComponent;
import id.dana.onboarding.movie.internal.component.MovieComponent;
import id.dana.onboarding.movie.view.fragment.DetailsMovieFragment;

import id.dana.onboarding.movie.internal.component.DaggerApplicationComponent;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version DetailMovieActivity, v 0.1 2019-06-18 09:00 by Derayan Bima A
 */
public class DetailMovieActivity extends BaseActivity implements HasComponent<MovieComponent> {

    private static final String INSTANCE_STATE_PARAM_MOVIE_ID = "id.dana.oboarding.movie" +
        ".STATE_PARAM_MOVIE_ID";

    private static final String INTENT_EXTRA_PARAM_MOVIE_ID = "id.dana.oboarding.movie" +
        ".INTENT_PARAM_MOVIE_ID";

    @BindView(R.id.main_toolbar)
    Toolbar toolbarMain;

    @BindView(R.id.main_toolbar_title)
    TextView tvTitleToolbar;

    private MovieComponent movieComponent;

    private String movieId;

    public static Intent getCallingIntent(Context context, String movieId) {
        Intent callingIntent = new Intent(context, DetailMovieActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_MOVIE_ID, movieId);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_detail_movie);

        ButterKnife.bind(this);

        this.setUpToolbar();
        this.initializeActivity(savedInstanceState);
        this.initializeInjector();
    }

    private void setUpToolbar() {
        if (toolbarMain != null) {
            setSupportActionBar(toolbarMain);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            tvTitleToolbar.setText(getResources().getString(R.string.movie_detail));
        }
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            movieId = getIntent().getStringExtra(INTENT_EXTRA_PARAM_MOVIE_ID);
            addFragment(R.id.frame_container_detail_movie,
                DetailsMovieFragment.forMovie(movieId));
        } else {
            movieId = savedInstanceState.getString(INSTANCE_STATE_PARAM_MOVIE_ID);
        }
    }

    private void initializeInjector() {
        movieComponent = DaggerMovieComponent.builder()
            .applicationComponent(getApplicationComponent())
            .activityModule(getActivityModule())
            .build();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(INSTANCE_STATE_PARAM_MOVIE_ID, movieId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public MovieComponent getComponent() {
        return movieComponent;
    }
}

