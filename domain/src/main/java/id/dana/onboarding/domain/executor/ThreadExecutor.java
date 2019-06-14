package id.dana.onboarding.domain.executor;

import java.util.concurrent.Executor;

/**
 * Executor implementation can be base on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the
 * {@link id.dana.onboarding.domain.interactor.UseCase} out of the UI thread.
 */
public interface ThreadExecutor extends Executor {

}
