package codex.cnoah.gitorade.common

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.toast(message: String, isLong: Boolean = false) {
    Toast.makeText(this, message, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(message: String, isLong: Boolean = false) {
    requireActivity().toast(message, isLong)
}