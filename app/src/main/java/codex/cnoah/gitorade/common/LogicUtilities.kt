package codex.cnoah.gitorade.common

import android.util.Log
import java.net.MalformedURLException
import java.net.URL
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Function to verify whether a provided [url] is valid or not.
 *
 * Return a [Boolean] respectively based on the result.
 */
fun String.isValidUrl(): Boolean {
    return try {
        URL(this)
        true
    } catch (ex: MalformedURLException) {
        Log.e("LogicUtils", "The provided htmlUrl: \"$this\" is invalid.")
        false
    }
}

/**
 * Function to parse the provided [date] based on its [actualDateFormat] into [desiredDateFormat] and return
 * the result.
 */
@Throws(ParseException::class)
fun getDesiredDateInDesiredFormat(date: String, actualDateFormat: String, desiredDateFormat: String): String {

    val simpleDateFormat = SimpleDateFormat(actualDateFormat, Locale.getDefault())

    if (actualDateFormat == UTC_DATE_FORMAT) {
        simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    }

    val finalDate = simpleDateFormat.parse(date)

    val desiredDate = SimpleDateFormat(desiredDateFormat, Locale.getDefault())
    desiredDate.timeZone = TimeZone.getDefault()
    return desiredDate.format(finalDate)
}

