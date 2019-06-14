package id.dana.onboarding.movie;

import javax.inject.Inject;

import androidx.annotation.UiThread;
import id.dana.onboarding.domain.executor.PostExecutionThread;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version UIThread, v 0.1 2019-06-14 14:36 by Derayan Bima A
 */
public class UIThread implements PostExecutionThread {

    @Inject
    UIThread(){

    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
