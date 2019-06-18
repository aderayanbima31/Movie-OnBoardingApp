package id.dana.onboarding.movie;

import com.squareup.leakcanary.LeakCanary;

import android.app.Application;
import android.os.SystemClock;

import id.dana.onboarding.movie.internal.component.ApplicationComponent;
import id.dana.onboarding.movie.internal.component.DaggerApplicationComponent;
import id.dana.onboarding.movie.internal.module.ApplicationModule;


/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version MainApp, v 0.1 2019-06-14 14:00 by Derayan Bima A
 */
public class MainApp extends Application {

    private static final int sleepLimit = 2500;

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeLeakDetection();
        this.delaySplashActivity();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .build();
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    private void delaySplashActivity() {
        SystemClock.sleep(sleepLimit);
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
