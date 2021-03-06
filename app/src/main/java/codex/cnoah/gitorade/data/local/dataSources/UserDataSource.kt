package codex.cnoah.gitorade.data.local.dataSources

import codex.cnoah.gitorade.data.models.User

interface UserDataSource {

    suspend fun saveUser(username: String, password: String)

    suspend fun getUser(username: String, password: String): User?

    suspend fun getUsers(): List<User>
}