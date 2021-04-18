package codex.cnoah.gitorade.features.home.ui

import android.content.Context
import android.content.Intent
import codex.cnoah.gitorade.R
import codex.cnoah.gitorade.common.BaseActivity
import codex.cnoah.gitorade.common.toast
import codex.cnoah.gitorade.databinding.ActivityHomeBinding
import codex.cnoah.gitorade.features.home.adapters.RepositoriesAdapter
import codex.cnoah.gitorade.features.home.viewModels.HomeViewModel
import java.lang.ref.WeakReference

class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>(
    HomeViewModel::class.java,
    ActivityHomeBinding::inflate
) {

    private val repositoriesAdapter by lazy {
        RepositoriesAdapter {
// TODO: Add navigation to display details.
        }
    }

    override fun ActivityHomeBinding.setupViews() {
        rvRepositories.adapter = repositoriesAdapter

        fun setupSearch() {
            ivSearch.setOnClickListener {

                val searchText = etSearchRepo.text.toString().trim()
                    .takeIf { it.isNotBlank() } ?: run {
                    toast(getString(R.string.please_search_a_repository))
                    return@setOnClickListener
                }

                viewModel.searchRepository(searchText)
            }
        }

        setupSearch()
    }

    override fun ActivityHomeBinding.observeViewModel() {
        viewModel.apply {
            repositories.observe(this@HomeActivity) { repos ->
                repositoriesAdapter.submitList(repos)
            }
        }
    }

    companion object {

        fun getIntent(context: WeakReference<Context>) = Intent(
            context.get(),
            HomeActivity::class.java
        )
    }
}