package codex.cnoah.gitorade.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Repository.REPOSITORY)
data class Repository(
    @ColumnInfo(name = ID) @PrimaryKey val id: Int,
    @ColumnInfo(name = NAME) val name: String,
    @ColumnInfo(name = OWNER_IMAGE) val ownerImage: String,
    @ColumnInfo(name = OWNER_ID) val ownerId: Int,
    @ColumnInfo(name = DESCRIPTION) val description: String,
    @ColumnInfo(name = FULL_NAME) val fullName: String,
    @ColumnInfo(name = HAS_DOWNLOADS) val hasDownloads: Boolean?,
    @ColumnInfo(name = HAS_PROJECTS) val hasProjects: Boolean?,
    @ColumnInfo(name = HAS_ISSUES) val hasIssues: Boolean?,
    @ColumnInfo(name = HAS_WIKI) val hasWiki: Boolean?,
    @ColumnInfo(name = WATCHERS_COUNT) val watchersCount: Int,
    @ColumnInfo(name = HTML_URL) val htmlUrl: String,
    @ColumnInfo(name = COMMITS_URL) val commitsUrl: String,
    @ColumnInfo(name = CONTRIBUTORS_URL) val contributorsUrl: String
) {

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val DESCRIPTION = "description"
        const val FULL_NAME = "full_name"
        const val WATCHERS_COUNT = "watchers_count"
        const val HTML_URL = "html_url"
        const val OWNER_IMAGE = "owner"
        const val OWNER_ID = "owner_id"
        const val COMMITS_URL = "commits_url"
        const val CONTRIBUTORS_URL = "contributors_url"
        const val HAS_ISSUES = "has_issues"
        const val HAS_PROJECTS = "has_projects"
        const val HAS_DOWNLOADS = "has_downloads"
        const val HAS_WIKI = "has_wiki"

        const val REPOSITORY = "repository"
    }
}