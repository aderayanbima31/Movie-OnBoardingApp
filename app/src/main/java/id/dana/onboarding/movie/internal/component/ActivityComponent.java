package id.dana.onboarding.movie.internal.component;

import android.app.Activity;

import dagger.Component;
import id.dana.onboarding.movie.internal.PerActivity;
import id.dana.onboarding.movie.internal.module.ActivityModule;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version ActivityComponent, v 0.1 2019-06-14 14:13 by Derayan Bima A
 */
@PerActivity

@Component(
    dependencies = ApplicationComponent.class,
    modules = ActivityModule.class
)
public interface ActivityComponent {

    //Exposed to sub-graphs.
    Activity activity();
}
