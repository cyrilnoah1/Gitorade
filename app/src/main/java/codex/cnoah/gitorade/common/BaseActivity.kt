package codex.cnoah.gitorade.common

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import codex.cnoah.gitorade.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding>(
    viewModelClass: Class<VM>,
    private val inflate: Inflate<VB>
) : FragmentActivity() {

    // Broadcast Receiver to check for the network connectivity.
    private val networkState = NetworkStateReceiver()
    private val networkFilter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")

    // Boolean to inform whether the internet connectivity is available at the moment.
    private var isNetworkAvailable: Boolean = true

    /**
     * [Snackbar] to display "No network connection" message based on the current state
     * of the network connectivity.
     */
    val networkSnack by lazy {
        binding?.root?.snack(getString(R.string.no_inter_msg), Snackbar.LENGTH_INDEFINITE)
    }

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

    /**
     * Function to notify the current state of the network connectivity.
     */
    open fun getNetworkAvailability(isConnected: Boolean) {}

    override fun onResume() {
        super.onResume()
        registerReceiver(networkState, networkFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(networkState)
    }

    /**
     * Function to return the current state of the network connectivity.
     */
    fun getNetworkState() = isNetworkAvailable

    /**
     * [BroadcastReceiver] class to listen to the network state.
     */
    inner class NetworkStateReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            context?.run {
                val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
                val isConnected: Boolean = activeNetwork?.isConnected == true

                getNetworkAvailability(isConnected)
                isNetworkAvailable = isConnected

                if (!isConnected) {
                    networkSnack?.show()
                } else {
                    networkSnack?.dismiss()
                }
                cm
            }
        }
    }
}

typealias Inflate<T> = (LayoutInflater) -> T