package codex.cnoah.gitorade.data.models

import com.google.gson.annotations.SerializedName

data class Contributor(
    @SerializedName(ID) val id: Int,
    @SerializedName(LOGIN) val loginName: String?,
    @SerializedName(AVATAR_IMAGE) val profilePicUrl: String?,
    @SerializedName(REPOS_URL) val reposLink: String?
) {
    companion object {
        const val ID = "id"
        const val LOGIN = "login"
        const val AVATAR_IMAGE = "avatar_url"
        const val REPOS_URL = "repos_url"
    }
}