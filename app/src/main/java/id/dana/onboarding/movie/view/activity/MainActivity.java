package id.dana.onboarding.movie.view.activity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.dana.onboarding.movie.R;
import id.dana.onboarding.movie.internal.HasComponent;
import id.dana.onboarding.movie.internal.component.DaggerMovieComponent;
import id.dana.onboarding.movie.internal.component.MovieComponent;
import id.dana.onboarding.movie.view.fragment.FavoriteMovieFragment;
import id.dana.onboarding.movie.view.fragment.PopularMovieFragment;
import id.dana.onboarding.movie.view.fragment.TopRatedMovieFragment;

import id.dana.onboarding.movie.internal.component.DaggerApplicationComponent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements HasComponent<MovieComponent>,
    BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bottom_navigation_menu)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.main_toolbar)
    Toolbar toolbarMain;

    @BindView(R.id.main_toolbar_title)
    TextView tvTitleToolbar;

    private MovieComponent movieComponent;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbarMain);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        this.initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.frame_container, new PopularMovieFragment());
            tvTitleToolbar.setText(R.string.popular);
            bottomNavigationView.setOnNavigationItemSelectedListener(this);
        }
    }

    private void initializeInjector() {
        this.movieComponent = DaggerMovieComponent.builder()
            .applicationComponent(getApplicationComponent())
            .activityModule(getActivityModule())
            .build();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_popular_movies:
                fragment = new PopularMovieFragment();
                break;

            case R.id.navigation_top_rated_movies:
                fragment = new TopRatedMovieFragment();
                break;

            case R.id.navigation_favorite_movies:
                fragment = new FavoriteMovieFragment();
                break;
        }
        return loadFragment(fragment);
    }

    public void setToolbarTitle(Fragment fragment) {
        if (fragment instanceof PopularMovieFragment) {
            tvTitleToolbar.setText(R.string.popular);
        } else if (fragment instanceof TopRatedMovieFragment) {
            tvTitleToolbar.setText(R.string.top_rated);
        } else if (fragment instanceof FavoriteMovieFragment) {
            tvTitleToolbar.setText(R.string.favorite_movies);
        }
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out)
                .replace(R.id.frame_container, fragment)
                .addToBackStack(null)
                .commit();

            setToolbarTitle(fragment);
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }

    @Override
    public MovieComponent getComponent() {
        return movieComponent;
    }
}