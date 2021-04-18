package codex.cnoah.gitorade.data.local

import androidx.room.Room
import codex.cnoah.gitorade.GitoradeApp

/**
 * Singleton to instantiate and provide the local cache handling object.
 */
object CacheProvider {

    private const val DATABASE_NAME = "gitorade_db"

    /**
     * Lazily provides the [GithubDatabase] object that helps in maintaining the local cache.
     */
    val setup: GithubDatabase by lazy {

        Room.databaseBuilder(
            GitoradeApp.getAppContext(),
            GithubDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}