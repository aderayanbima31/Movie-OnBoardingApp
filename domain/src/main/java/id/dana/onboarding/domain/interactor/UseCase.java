package id.dana.onboarding.domain.interactor;

import com.fernandocejas.arrow.checks.Preconditions;

import id.dana.onboarding.domain.executor.PostExecutionThread;
import id.dana.onboarding.domain.executor.ThreadExecutor;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version UseCase, v 0.1 2019-06-14 11:03 by Derayan Bima A
 */

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a {@link
 * DisposableObserver}
 * that will execute its job in a background thread and will post the result in UI thread.
 */

public abstract class UseCase<T, Params> {

    private final CompositeDisposable disposables;

    private final PostExecutionThread postExecutionThread;

    private final ThreadExecutor threadExecutor;

    UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.disposables = new CompositeDisposable();
    }

    public void execute(DisposableObserver<T> observer) {
        execute(observer, null);
    }

    /**
     * Executes the current use case.
     *
     * @param observer {@link DisposableObserver} which will be listening to the observer build
     *                 by {@link #buildUseCaseObservable(Params) ()} method.
     * @param params   Parameters (Optional) used to build/execute this use case.
     */
    public void execute(DisposableObserver<T> observer, Params params) {
        Preconditions.checkNotNull(observer);
        final Observable<T> observable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    abstract Observable<T> buildUseCaseObservable(Params params);

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    private void addDisposable(Disposable disposable) {
        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(disposables);
        disposables.add(disposable);
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }
}
