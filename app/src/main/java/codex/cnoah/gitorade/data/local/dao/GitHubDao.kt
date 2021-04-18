package codex.cnoah.gitorade.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import codex.cnoah.gitorade.data.models.Repository


@Dao
interface GitHubDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRepo(repo: Repository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRepos(repos: List<Repository>)

    @Query("SELECT * FROM repository WHERE id = :id")
    fun getRepo(id: Int): Repository

    @Query("SELECT * FROM repository WHERE name LIKE :searchKey ORDER BY watchers_count DESC")
    fun getRepos(searchKey: String): List<Repository>
}