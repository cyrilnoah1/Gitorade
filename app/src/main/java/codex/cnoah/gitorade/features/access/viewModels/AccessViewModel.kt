package codex.cnoah.gitorade.features.access.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import codex.cnoah.gitorade.common.BaseViewModel
import codex.cnoah.gitorade.data.repositories.UserRepo
import kotlinx.coroutines.launch

class AccessViewModel : BaseViewModel() {

    private val userRepo by lazy { UserRepo() }

    private val _userType by lazy { MutableLiveData<UserType>() }
    val userType: LiveData<UserType> by lazy { _userType }

    fun gitInUser(username: String, password: String) {
        ioScope.launch {
            userRepo.getUser(username, password)?.let {
                _userType.postValue(UserType.EXISTING)
                return@let
            } ?: run {
                userRepo.saveUser(username, password)
                _userType.postValue(UserType.NEW)
                return@run
            }
        }
    }
}

enum class UserType {
    NEW,
    EXISTING
}