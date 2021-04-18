package codex.cnoah.gitorade.data.repositories

import codex.cnoah.gitorade.data.models.User

interface UserRepository {

    suspend fun saveUser(username: String, password: String)

    suspend fun getUser(username: String, password: String) : User?

    suspend fun getUsers(): List<User>
}