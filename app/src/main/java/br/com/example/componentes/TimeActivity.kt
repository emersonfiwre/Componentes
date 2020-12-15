package br.com.example.componentes

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_time.*
import java.text.SimpleDateFormat
import java.util.*

class TimeActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener,
    TimePicker.OnTimeChangedListener {
    private val mSimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        button_date.setOnClickListener(this)
        button_get_time.setOnClickListener(this)
        button_set_time.setOnClickListener(this)
        //button_time.setOnClickListener(this)
        timepicker.setOnTimeChangedListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.button_date -> {
                val calendar = Calendar.getInstance()
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val month = calendar.get(Calendar.MONTH)
                val year = calendar.get(Calendar.YEAR)
                DatePickerDialog(this, this, year, month, day).show()
            }
            /*R.id.button_time ->{
                TimePickerDialog(this,this,1,1,true).show()
            }*/
            R.id.button_get_time -> {
                progress.visibility = View.GONE

                if (Build.VERSION.SDK_INT >= 23) {
                    val hour = timepicker.hour
                    val minute = timepicker.minute

                    toast("$hour:$minute")
                } else {
                    val hour = timepicker.currentHour
                    val minute = timepicker.currentMinute
                    toast("$hour:$minute")
                }
            }
            R.id.button_set_time -> {

                if (Build.VERSION.SDK_INT >= 23) {
                    timepicker.hour = 20
                    timepicker.minute = 20

                } else {
                    timepicker.currentHour = 20
                    timepicker.currentMinute = 20
                }
            }

        }
    }

    private fun toast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        val date = Calendar.getInstance()
        date.set(p1, p2, p3)
        button_date.text = mSimpleDateFormat.format(date.time)
    }

    /*override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        toast("$p1:$p2")
    }*/

    override fun onTimeChanged(p0: TimePicker?, p1: Int, p2: Int) {
        toast("$p1:$p2")
    }
}