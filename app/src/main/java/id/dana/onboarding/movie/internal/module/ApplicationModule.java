package id.dana.onboarding.movie.internal.module;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version ApplicationModule, v 0.1 2019-06-14 14:21 by Derayan Bima A
 */

import android.content.Context;

import javax.inject.Singleton;

import androidx.annotation.UiThread;
import dagger.Module;
import dagger.Provides;
import id.dana.data.executor.JobExecutor;
import id.dana.data.repository.MovieEntityRepository;
import id.dana.onboarding.domain.executor.PostExecutionThread;
import id.dana.onboarding.domain.executor.ThreadExecutor;
import id.dana.onboarding.domain.repository.MovieRepository;
import id.dana.onboarding.movie.MainApp;
import id.dana.onboarding.movie.UIThread;

/**
 * Dagger module that provides objects which will live during the application lifecycle
 */

@Module
public class ApplicationModule {

    private final MainApp application;

    public ApplicationModule(MainApp application){
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext(){
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {

        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread){
        return uiThread;
    }

    @Provides
    @Singleton
    MovieRepository providesMovieRepository(MovieEntityRepository movieEntityRepository){
        return movieEntityRepository;
    }


}
