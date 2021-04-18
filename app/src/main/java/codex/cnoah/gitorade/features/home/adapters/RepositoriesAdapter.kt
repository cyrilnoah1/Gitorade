package codex.cnoah.gitorade.features.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import codex.cnoah.gitorade.common.setUrlSource
import codex.cnoah.gitorade.data.models.Repository
import codex.cnoah.gitorade.databinding.ItemRepositoryBinding

class RepositoriesAdapter(
    private val onClick: (data: Repository) -> Unit
) : ListAdapter<Repository, RepositoriesAdapter.RepoViewHolder>(DIFF) {

    override fun onCreateViewHolder(viewHolder: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            ItemRepositoryBinding.inflate(
                LayoutInflater.from(viewHolder.context),
                viewHolder,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: RepoViewHolder, position: Int) {
        getItem(position)?.let { viewHolder.bind(it) }
    }

    inner class RepoViewHolder(private val item: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(item.root) {

        fun bind(repo: Repository) {
            item.apply {
                tvNameContent.text = repo.name
                tvFullNameContent.text = repo.fullName
                tvWatcherCountContent.text = repo.watchersCount.toString()
                ivOwnerImage.setUrlSource(repo.ownerImage)
                clRoot.setOnClickListener { onClick(repo) }
            }
        }
    }

    companion object {

        val DIFF = object : DiffUtil.ItemCallback<Repository>() {

            override fun areItemsTheSame(old: Repository, new: Repository) = old.id == new.id

            override fun areContentsTheSame(old: Repository, new: Repository) = old == new
        }
    }
}