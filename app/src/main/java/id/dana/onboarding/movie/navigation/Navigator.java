package id.dana.onboarding.movie.navigation;

/**
 * Class used to navigate through the application.
 */

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.dana.onboarding.movie.view.activity.DetailMovieActivity;
import id.dana.onboarding.movie.view.activity.MainActivity;

@Singleton
public class Navigator {

    @Inject
    public Navigator() {
    }

    public void navigateToMovieList(Context context) {
        if (context != null) {
            Intent intentToLaunch = MainActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToDetailMovie(Context context, String movieId) {
        if (context != null) {
            Intent intentToLaunch = DetailMovieActivity.getCallingIntent(context, movieId);
            intentToLaunch.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentToLaunch);
        }
    }
}