package id.dana.onboarding.movie.internal.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import id.dana.onboarding.domain.executor.PostExecutionThread;
import id.dana.onboarding.domain.executor.ThreadExecutor;
import id.dana.onboarding.domain.repository.FavoriteMovieRepository;
import id.dana.onboarding.domain.repository.MovieRepository;
import id.dana.onboarding.movie.internal.module.ApplicationModule;
import id.dana.onboarding.movie.view.activity.BaseActivity;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version ApplicationComponent, v 0.1 2019-06-14 14:14 by Derayan Bima A
 */

@Singleton // Constraints this component to one-per-application or unscope bindings.
@Component(
    modules = ApplicationModule.class
)
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    MovieRepository movieRepository();

    FavoriteMovieRepository favoriteMovieRepository();
}

