package codex.cnoah.gitorade.features.access.ui

import android.content.Context
import android.content.Intent
import androidx.core.widget.doAfterTextChanged
import codex.cnoah.gitorade.R
import codex.cnoah.gitorade.common.BaseActivity
import codex.cnoah.gitorade.common.toast
import codex.cnoah.gitorade.databinding.ActivityAccessBinding
import codex.cnoah.gitorade.features.access.viewModels.AccessViewModel
import codex.cnoah.gitorade.features.access.viewModels.UserType
import codex.cnoah.gitorade.features.home.ui.HomeActivity
import java.lang.ref.WeakReference

class AccessActivity : BaseActivity<AccessViewModel, ActivityAccessBinding>(
    AccessViewModel::class.java,
    ActivityAccessBinding::inflate
) {

    override fun ActivityAccessBinding.setupViews() {

        fun setupFields() {
            etUsername.doAfterTextChanged { etUsername.error = null }
            etPassword.doAfterTextChanged { etPassword.error = null }
        }

        fun setupGitIn() {

            fun userNameError() {
                etUsername.error = getString(R.string.enter_proper_username)
            }

            fun passwordError() {
                etPassword.error = getString(R.string.enter_proper_password)
            }

            btnGitIn.setOnClickListener {
                val username = etUsername.text.toString().trim().takeIf { it.isNotBlank() } ?: run {
                    userNameError()
                    return@setOnClickListener
                }

                val password = etPassword.text.toString().trim().takeIf { it.isNotBlank() } ?: run {
                    passwordError()
                    return@setOnClickListener
                }

                viewModel.gitInUser(username, password)
            }
        }

        setupFields()
        setupGitIn()
    }

    override fun ActivityAccessBinding.observeViewModel() {
        viewModel.apply {
            userType.observe(this@AccessActivity) { userType ->
                when (userType) {
                    UserType.NEW -> {
                        toast(getString(R.string.welcome))
                        navigateToHomeScreen()
                    }
                    UserType.EXISTING -> {
                        toast(getString(R.string.welcome_back))
                        navigateToHomeScreen()
                    }
                    else -> {
                        toast(getString(R.string.failed_to_auth))
                    }
                }
            }
        }
    }

    private fun navigateToHomeScreen() {
        startActivity(HomeActivity.getIntent(WeakReference(this)))
        finishAffinity()
    }

    companion object {

        fun getIntent(context: WeakReference<Context>) = Intent(
            context.get(),
            AccessActivity::class.java
        )
    }
}