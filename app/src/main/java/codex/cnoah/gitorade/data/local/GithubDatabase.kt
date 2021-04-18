package codex.cnoah.gitorade.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import codex.cnoah.gitorade.data.local.dao.GitHubDao
import codex.cnoah.gitorade.data.local.dao.UserDao
import codex.cnoah.gitorade.data.models.Repository
import codex.cnoah.gitorade.data.models.User

/**
 * [RoomDatabase] to handle the C.R.U.D. operations through the respective Data Access Objects.
 */
@Database(entities = [Repository::class, User::class], version = 1, exportSchema = false)
abstract class GithubDatabase : RoomDatabase() {

    abstract fun githubDao(): GitHubDao

    abstract fun userDao(): UserDao
}