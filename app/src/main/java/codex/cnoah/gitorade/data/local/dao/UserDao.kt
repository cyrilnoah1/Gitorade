package codex.cnoah.gitorade.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import codex.cnoah.gitorade.data.models.User

interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: User)

    @Query("SELECT * from user WHERE username = :username")
    fun getUser(username: String): User
}