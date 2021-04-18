package codex.cnoah.gitorade.data.repositories

import android.util.Log
import codex.cnoah.gitorade.common.EMPTY_STRING
import codex.cnoah.gitorade.data.local.dataSources.CacheDataSource
import codex.cnoah.gitorade.data.models.RemoteRepository
import codex.cnoah.gitorade.data.models.Repository
import codex.cnoah.gitorade.data.remote.RemoteGitDataSource
import codex.cnoah.gitorade.data.remote.RetrofitDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GithubRepo {

    private val cache = CacheDataSource()
    private val remote = RetrofitDataSource()
    private val ioScope = CoroutineScope(Dispatchers.IO)

    suspend fun fetchRepositories(searchKey: String): List<Repository>? = suspendCoroutine {
        remote.getRepositories(searchKey, object : RemoteGitDataSource.RepositoryResponse {

            override fun onSuccess(repos: List<RemoteRepository>) {
                // Converting to remote model to local model and saving
                // it in the cache.

                ioScope.launch {
                    val mapped = repos.map { repo ->
                        Repository(
                            id = repo.id,
                            name = repo.name ?: EMPTY_STRING,
                            ownerImage = repo.owner?.profilePicUrl ?: EMPTY_STRING,
                            description = repo.description ?: EMPTY_STRING,
                            fullName = repo.fullName ?: EMPTY_STRING,
                            watchersCount = repo.watchersCount ?: 0,
                            htmlUrl = repo.htmlUrl ?: EMPTY_STRING,
                            hasDownloads = repo.hasDownloads ?: false,
                            hasProjects = repo.hasProjects ?: false,
                            hasIssues = repo.hasIssues ?: false,
                            hasWiki = repo.hasWiki ?: false,
                            commitsUrl = repo.commitsUrl ?: EMPTY_STRING,
                            contributorsUrl = repo.contributorsUrl ?: EMPTY_STRING,
                            isFavorite = cache.getRepository(repo.id)?.isFavorite ?: false
                        )
                    }

                    cache.saveRepositories(mapped)

                    it.resume(cache.getRepositories(searchKey))
                }
            }

            override fun onFailure() {
                Log.e(TAG, "Failed to fetch for the data.")
                it.resume(null)
            }
        })
    }

    fun getRepositories(searchKey: String): List<Repository> =
        cache.getRepositories(searchKey)

    fun saveRepository(repo: Repository) = cache.saveRepository(repo)

    companion object {
        val TAG: String = GithubRepo::class.java.simpleName
    }
}