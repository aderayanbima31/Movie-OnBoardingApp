package id.dana.onboarding.movie.internal.module;

import android.app.Activity;

import dagger.Provides;
import id.dana.onboarding.movie.internal.PerActivity;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version ActivityModule, v 0.1 2019-06-14 14:15 by Derayan Bima A
 */
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity activity(){
        return this.activity;
    }

}
