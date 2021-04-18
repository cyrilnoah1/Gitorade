package codex.cnoah.gitorade.features.splash.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import codex.cnoah.gitorade.common.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashViewModel : BaseViewModel() {

    private val _navigateTo by lazy(LazyThreadSafetyMode.PUBLICATION) {
        MutableLiveData<NavDestination>()
    }

    val navigateTo: LiveData<NavDestination> by lazy(LazyThreadSafetyMode.PUBLICATION) {
        _navigateTo
    }

    fun checkAndNavigate(){
        ioScope.launch {
            delay(PURPOSEFUL_DELAY_MILLIS)

        }
    }


    companion object{
        private const val PURPOSEFUL_DELAY_MILLIS = 1_000L
    }
}

enum class NavDestination {
    LOGIN,
    HOME
}