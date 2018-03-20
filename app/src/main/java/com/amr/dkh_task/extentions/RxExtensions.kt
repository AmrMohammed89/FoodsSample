import com.amr.dkh_task.SchedulerContract
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addToCompositeDisposable(composite: CompositeDisposable) {
    composite.add(this)
}

fun <T> Observable<T>.uiSubscribe(schedulerContract: SchedulerContract): Observable<T> {
    return subscribeOn(schedulerContract.io)
            .observeOn(schedulerContract.ui)
}