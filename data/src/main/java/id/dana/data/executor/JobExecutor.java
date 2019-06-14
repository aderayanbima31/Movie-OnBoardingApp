package id.dana.data.executor;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.dana.onboarding.domain.executor.ThreadExecutor;

/**
 * Decorated {@link java.util.concurrent.ThreadPoolExecutor}
 */
@Singleton
public class JobExecutor implements ThreadExecutor {

    private final ThreadPoolExecutor threadPoolExecutor;

    @Inject
    public JobExecutor() {
        this.threadPoolExecutor = new ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(), new JobThreadFactory());
    }

    @Override
    public void execute(Runnable command) {
        this.threadPoolExecutor.execute(command);
    }

    private static class JobThreadFactory implements ThreadFactory {

        private int counter = 0;

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "android_" + counter++);
        }
    }
}
