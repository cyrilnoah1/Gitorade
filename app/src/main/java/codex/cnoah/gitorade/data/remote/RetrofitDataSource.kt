package codex.cnoah.gitorade.data.remote

import android.util.Log
import codex.cnoah.gitorade.common.EMPTY_STRING
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class RetrofitDataSource : RemoteGitDataSource {

    private val service = ServiceProvider.setup.create(GithubService::class.java)

    override fun getRepositories(
        searchKey: String,
        callback: RemoteGitDataSource.RepositoryResponse
    ) {
        val disposable = CompositeDisposable()

        val handleSuccess = Consumer<RepositoriesResponseBody> {
            if (it?.repositories != null) {
                it.repositories.let { repos -> callback.onSuccess(repos) }
            } else {
                Log.e(TAG, RESPONSE_FAILURE_MESSAGE)
                callback.onFailure()
            }

            disposable.takeIf { disp -> !disp.isDisposed }?.dispose()
        }

        val handleError = Consumer<Throwable> {
            callback.onFailure()
            Log.e(TAG, it?.message ?: EMPTY_STRING)
            disposable.takeIf { disp -> !disp.isDisposed }?.dispose()
        }

        disposable.add(
            service.getRepositories(searchKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(handleSuccess, handleError)
        )
    }

    companion object {
        val TAG: String = RetrofitDataSource::class.java.simpleName
        const val RESPONSE_FAILURE_MESSAGE = "Failed to fetch the new data."
    }
}