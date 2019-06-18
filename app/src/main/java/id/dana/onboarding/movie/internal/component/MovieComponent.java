package id.dana.onboarding.movie.internal.component;

import dagger.Component;
import id.dana.onboarding.movie.internal.PerActivity;
import id.dana.onboarding.movie.internal.module.ActivityModule;
import id.dana.onboarding.movie.internal.module.MovieModule;
import id.dana.onboarding.movie.view.fragment.DetailsMovieFragment;
import id.dana.onboarding.movie.view.fragment.FavoriteMovieFragment;
import id.dana.onboarding.movie.view.fragment.PopularMovieFragment;
import id.dana.onboarding.movie.view.fragment.TopRatedMovieFragment;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version MovieComponent, v 0.1 2019-06-14 14:16 by Derayan Bima A
 */

@PerActivity
@Component(
    dependencies = ApplicationComponent.class,
    modules = {
        ActivityModule.class,
        MovieModule.class
    }
)

public interface MovieComponent extends ActivityComponent {

    void inject(PopularMovieFragment popularMovieFragment);

    void inject(TopRatedMovieFragment topRatedMovieFragment);

    void inject(DetailsMovieFragment detailsMovieFragment);

    void inject(FavoriteMovieFragment favoriteMovieFragment);

}
