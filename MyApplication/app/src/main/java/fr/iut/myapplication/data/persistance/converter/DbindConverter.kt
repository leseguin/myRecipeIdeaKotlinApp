package fr.iut.myapplication.data.persistance.converter

import android.text.TextUtils
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
}