package com.example.andropeinn

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.provider.CalendarContract.CalendarEntity
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class DataPickerFragment(val listener: (day:Int, month:Int, year:Int)->Unit):DialogFragment(), DatePickerDialog.OnDateSetListener {
    override fun onDateSet(p0: DatePicker?, year: Int,month: Int, dayOfMoth: Int) {
        listener(dayOfMoth, month, year)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c=Calendar.getInstance()
        c.add(Calendar.DAY_OF_MONTH, 5)
        val day=c.get(Calendar.DAY_OF_MONTH)
        val month=c.get(Calendar.MONTH)
        val year=c.get(Calendar.YEAR)
        val picker=DatePickerDialog(activity as Context,this, year, month, day)
        picker.datePicker.minDate=System.currentTimeMillis()-1000
        return picker
    }

}