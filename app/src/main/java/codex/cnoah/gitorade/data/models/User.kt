package codex.cnoah.gitorade.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = User.USER)
data class User(
    @ColumnInfo(name = USERNAME) @PrimaryKey val userName: String,
    @ColumnInfo(name = PASSWORD) val password: String,
) {

    companion object {
        const val USERNAME = "username"
        const val PASSWORD = "password"
        const val USER = "user"
    }
}