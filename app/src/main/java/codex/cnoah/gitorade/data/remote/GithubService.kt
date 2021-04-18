package codex.cnoah.gitorade.data.remote

import codex.cnoah.gitorade.data.QUERY_PARAMETER_KEY
import codex.cnoah.gitorade.data.models.RemoteRepository
import codex.cnoah.gitorade.data.models.Repository
import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Remote service to fetch for the repositories list.
 */
interface GithubService {

    @GET("search/repositories")
    fun getRepositories(@Query(QUERY_PARAMETER_KEY) q: String): Observable<RepositoriesResponseBody>
}


data class RepositoriesResponseBody(
    @SerializedName(TOTAL_COUNT) val totalCount: Int,
    @SerializedName(INCOMPLETE_RESULTS) val incompleteResults: Boolean,
    @SerializedName(ITEMS) val repositories: List<RemoteRepository>
) {

    companion object {
        const val TOTAL_COUNT = "total_count"
        const val INCOMPLETE_RESULTS = "incomplete_results"
        const val ITEMS = "items"
    }
}