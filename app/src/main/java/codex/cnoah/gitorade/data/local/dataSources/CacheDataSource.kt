package codex.cnoah.gitorade.data.local.dataSources

import codex.cnoah.gitorade.data.local.CacheProvider
import codex.cnoah.gitorade.data.models.Repository

class CacheDataSource : LocalGitDataSource {

    private val cache = CacheProvider.setup.githubDao()

    override fun saveRepositories(repositories: List<Repository>) {
        cache.saveRepos(repositories)
    }

    override fun getRepositories(searchKey: String): List<Repository> {
        return cache.getRepos("%$searchKey%")
    }

    override fun saveRepository(repo: Repository) {
        cache.saveRepo(repo)
    }

    override fun getRepository(id: Int): Repository? {
        return cache.getRepo(id)
    }

}