package codex.cnoah.gitorade.common

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding>(
    viewModelClass: Class<VM>,
    private val inflate: Inflate<VB>
) : FragmentActivity() {

    private var binding: VB? = null

    protected val viewModel: VM by lazy { ViewModelProviders.of(this)[viewModelClass] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate.invoke(layoutInflater)
        binding?.apply {
            setContentView(root)
            setupViews()
            observeViewModel()
        }
    }

    open fun VB.setupViews() {}

    open fun VB.observeViewModel() {}
}

typealias Inflate<T> = (LayoutInflater) -> T