package codex.cnoah.gitorade.features.home.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import codex.cnoah.gitorade.common.BaseViewModel
import codex.cnoah.gitorade.data.models.Repository
import codex.cnoah.gitorade.data.repositories.GithubRepo
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    private val githubRepo by lazy { GithubRepo() }

    private val _repositories by lazy { MutableLiveData<List<Repository>>() }
    val repositories: LiveData<List<Repository>> by lazy { _repositories }

    fun searchRepository(searchTerm: String) {
        ioScope.launch {
            githubRepo.fetchRepositories(searchTerm)
            _repositories.postValue(githubRepo.getRepositories(searchTerm))
        }
    }
}