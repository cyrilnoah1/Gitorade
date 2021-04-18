package codex.cnoah.gitorade.features.home.ui

import android.content.Context
import android.content.Intent
import codex.cnoah.gitorade.common.BaseActivity
import codex.cnoah.gitorade.databinding.ActivityHomeBinding
import codex.cnoah.gitorade.features.home.viewModels.HomeViewModel
import java.lang.ref.WeakReference

class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>(
    HomeViewModel::class.java,
    ActivityHomeBinding::inflate
) {

    companion object {

        fun getIntent(context: WeakReference<Context>) = Intent(
            context.get(),
            HomeActivity::class.java
        )
    }
}