package codex.cnoah.gitorade.data.remote

import codex.cnoah.gitorade.data.models.RemoteRepository


interface RemoteGitDataSource {

    fun getRepositories(searchKey: String, callback: RepositoryResponse)

    interface RepositoryResponse {
        fun onSuccess(repos: List<RemoteRepository>)
        fun onFailure()
    }
}