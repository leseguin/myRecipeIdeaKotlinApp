package fr.iut.myapplication.data.persistance.converter

import android.widget.CalendarView
import androidx.databinding.InverseBindingListener
import androidx.databinding.BindingAdapter
import java.util.*
import android.widget.CalendarView.OnDateChangeListener


object CalendarViewBindingAdapter {
    @BindingAdapter(
        value = ["android:onSelectedDayChange", "android:dateAttrChanged"],
        requireAll = false
    )
    fun setListeners(
        view: CalendarView, onDayChange: OnDateChangeListener?,
        attrChange: InverseBindingListener?
    ) {
        if (attrChange == null) {
            view.setOnDateChangeListener(onDayChange)
        } else {
            view.setOnDateChangeListener { view, year, month, dayOfMonth ->
                onDayChange?.onSelectedDayChange(view, year, month, dayOfMonth)
                val instance = Calendar.getInstance()
                instance.set(year, month, dayOfMonth)
                view.date = instance.timeInMillis
                attrChange.onChange()
            }
        }
    }
}