package codex.cnoah.gitorade.data.models

import com.google.gson.annotations.SerializedName

data class RemoteRepository(
    @SerializedName(ID) val id: Int,
    @SerializedName(NAME) val name: String?,
    @SerializedName(OWNER) val owner: Contributor?,
    @SerializedName(DESCRIPTION) val description: String?,
    @SerializedName(HAS_DOWNLOADS) val hasDownloads: Boolean?,
    @SerializedName(HAS_PROJECTS) val hasProjects: Boolean?,
    @SerializedName(HAS_ISSUES) val hasIssues: Boolean?,
    @SerializedName(HAS_WIKI) val hasWiki: Boolean?,
    @SerializedName(HTML_URL) val htmlUrl: String?,
    @SerializedName(FULL_NAME) val fullName: String?,
    @SerializedName(WATCHERS_COUNT) val watchersCount: Int?,
    @SerializedName(COMMITS_URL) val commitsUrl: String?,
    @SerializedName(CONTRIBUTORS_URL) val contributorsUrl: String?
) {

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val DESCRIPTION = "description"
        const val FULL_NAME = "full_name"
        const val WATCHERS_COUNT = "watchers_count"
        const val OWNER = "owner"
        const val COMMITS_URL = "commits_url"
        const val CONTRIBUTORS_URL = "contributors_url"
        const val HTML_URL = "html_url"
        const val HAS_ISSUES = "has_issues"
        const val HAS_PROJECTS = "has_projects"
        const val HAS_DOWNLOADS = "has_downloads"
        const val HAS_WIKI = "has_wiki"
    }
}