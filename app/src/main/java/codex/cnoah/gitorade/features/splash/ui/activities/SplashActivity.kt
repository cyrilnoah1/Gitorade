package codex.cnoah.gitorade.features.splash.ui.activities

import codex.cnoah.gitorade.R
import codex.cnoah.gitorade.common.BaseActivity
import codex.cnoah.gitorade.common.toast
import codex.cnoah.gitorade.databinding.ActivitySplashBinding
import codex.cnoah.gitorade.features.access.ui.AccessActivity
import codex.cnoah.gitorade.features.home.ui.HomeActivity
import codex.cnoah.gitorade.features.splash.viewModels.NavDestination
import codex.cnoah.gitorade.features.splash.viewModels.SplashViewModel
import java.lang.ref.WeakReference

class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>(
    SplashViewModel::class.java,
    ActivitySplashBinding::inflate
) {

    override fun ActivitySplashBinding.setupViews() {
        viewModel.checkAndNavigate()
    }

    override fun ActivitySplashBinding.observeViewModel() {
        viewModel.apply {

            navigateTo.observe(this@SplashActivity) { destination ->
                when (destination) {
                    NavDestination.LOGIN -> navigateToAccessScreen()
                    NavDestination.HOME -> navigateToHomeScreen()
                    else -> toast(getString(R.string.failed_to_navigate))
                }
            }
        }
    }

    private fun navigateToAccessScreen() {
        startActivity(AccessActivity.getIntent(WeakReference(this)))
        finish()
    }

    private fun navigateToHomeScreen() {
        startActivity(HomeActivity.getIntent(WeakReference(this)))
        finish()
    }

}