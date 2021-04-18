package codex.cnoah.gitorade.data.local.dataSources

import codex.cnoah.gitorade.data.models.Repository

interface LocalGitDataSource {

    fun saveRepositories(repositories: List<Repository>)

    fun getRepositories(searchKey: String): List<Repository>
}