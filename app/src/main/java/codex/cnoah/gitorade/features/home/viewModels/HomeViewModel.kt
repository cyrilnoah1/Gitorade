package codex.cnoah.gitorade.features.home.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import codex.cnoah.gitorade.common.BaseViewModel
import codex.cnoah.gitorade.common.EMPTY_STRING
import codex.cnoah.gitorade.common.SingleLiveEvent
import codex.cnoah.gitorade.data.models.Repository
import codex.cnoah.gitorade.data.repositories.GithubRepo
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : BaseViewModel() {

    private val githubRepo by lazy { GithubRepo() }

    private val _searchTerm by lazy { SingleLiveEvent<String>() }

    private val _repos by lazy { MutableLiveData<List<Repository>>() }
    val repos: LiveData<List<Repository>> by lazy { _repos }

    fun searchResults(searchTerm: String) {
        _searchTerm.postValue(searchTerm)
        ioScope.launch {
            if (withContext(coroutineContext) {
                    githubRepo.fetchRepositories(
                        searchTerm
                    )
                }) {
                _repos.postValue(githubRepo.getRepositories(searchTerm))
            }else{
                _repos.postValue(githubRepo.getRepositories(searchTerm))
            }
        }
    }

    fun toggleFavorite(data: Repository) {
        ioScope.launch {
            githubRepo.saveRepository(data)
            _repos.postValue(githubRepo.getRepositories(_searchTerm.value ?: EMPTY_STRING))
        }
    }
}