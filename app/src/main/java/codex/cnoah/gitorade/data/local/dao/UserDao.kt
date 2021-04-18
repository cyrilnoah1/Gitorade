package codex.cnoah.gitorade.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import codex.cnoah.gitorade.data.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: User)

    @Query("SELECT * from user WHERE username = :username AND password = :password")
    fun getUser(username: String, password: String): User?

    @Query("SELECT * from user")
    fun getUsers(): List<User>
}