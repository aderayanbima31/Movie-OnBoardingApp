package id.dana.onboarding.movie.view.activity;

import android.os.Bundle;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import id.dana.onboarding.movie.MainApp;
import id.dana.onboarding.movie.internal.component.ApplicationComponent;
import id.dana.onboarding.movie.internal.module.ActivityModule;
import id.dana.onboarding.movie.navigation.Navigator;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version BaseActivity, v 0.1 2019-06-14 14:23 by Derayan Bima A
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager()
            .beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * Get the Main Application Component for Dependency Injection
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((MainApp) getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity Module for Dependency Injection
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
