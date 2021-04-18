package codex.cnoah.gitorade.data.local.dataSources

import codex.cnoah.gitorade.data.local.CacheProvider
import codex.cnoah.gitorade.data.models.User

class UserDataSourceImpl : UserDataSource {

    private val cache by lazy { CacheProvider.setup.userDao() }

    override suspend fun saveUser(username: String, password: String) {
        cache.saveUser(User(username, password))
    }

    override suspend fun getUser(username: String, password: String): User? {
        return cache.getUser(username, password)
    }

    override suspend fun getUsers(): List<User> {
        return cache.getUsers()
    }
}