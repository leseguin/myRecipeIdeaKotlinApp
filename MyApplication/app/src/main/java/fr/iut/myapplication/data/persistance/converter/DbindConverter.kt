package fr.iut.myapplication.data.persistance.converter

import android.text.Html
import android.text.Spanned
import android.text.TextUtils
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.databinding.InverseMethod

object DbindConverter {

    @InverseMethod("convertStringToInteger")
    @JvmStatic
    fun convertIntegerToString(value: String): Int? {
        if (TextUtils.isEmpty(value) || !TextUtils.isDigitsOnly(value)) {
            return null
        }
        return value.toInt()
    }

    @JvmStatic
    fun convertStringToInteger(value: Int?): String {
        return value?.toString() ?: ""
    }

    @JvmStatic
    fun listEmptyToVisibility(size: Int): Int {
        return if (size <= 0) View.VISIBLE else View.GONE
    }

    @JvmStatic
    fun htmtToString( string : String) : Spanned {
        return HtmlCompat.fromHtml(string, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}