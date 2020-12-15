package br.com.example.componentes

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener,
    SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_toast.setOnClickListener(this)
        button_snack.setOnClickListener(this)
        button_get_spinner.setOnClickListener(this)
        button_set_spinner.setOnClickListener(this)
        button_get_seekbar.setOnClickListener(this)
        button_set_seekbar.setOnClickListener(this)

        spinner_static.onItemSelectedListener = this
        spinner_dynamic.onItemSelectedListener = this
        seekbar.setOnSeekBarChangeListener(this)
        switch_on_off.setOnCheckedChangeListener(this)
        check_on_off.setOnCheckedChangeListener(this)
        radio_on.setOnCheckedChangeListener(this)
        radio_off.setOnCheckedChangeListener(this)

        loadSpinner()
    }

    private fun loadSpinner() {
        val mList = listOf("Gramas", "Kg", "Arroba", "Tonelada")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mList)
        spinner_dynamic.adapter = adapter
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_toast -> {
                val toast = Toast.makeText(this, "TOAST", Toast.LENGTH_SHORT)

                /*val textView = toast.view!!.findViewById<TextView>(android.R.id.message)
            textView.setTextColor(Color.RED)*/

                val layout = layoutInflater.inflate(R.layout.toast_layout, null)
                toast.view = layout

                toast.setGravity(Gravity.TOP, 0, 250)
                toast.show()
            }
            R.id.button_snack -> {
                val snack = Snackbar.make(linear_root, "SNACK", Snackbar.LENGTH_SHORT)
                snack.setAction("Desfazer", View.OnClickListener {
                    toast("Desfeito!")
                })
                snack.setActionTextColor(Color.BLUE)
                snack.setBackgroundTint(Color.GRAY)

                snack.show()

            }
            R.id.button_get_spinner -> {
                val selectedItem = spinner_static.selectedItem
                val selectedItemPosition = spinner_static.selectedItemPosition

                toast("Position: $selectedItem  $selectedItemPosition")
            }
            R.id.button_set_spinner -> {
                spinner_dynamic.setSelection(2)
            }
            R.id.button_get_seekbar -> {
                toast("Seekbar: ${seekbar.progress}")
            }
            R.id.button_set_seekbar -> {
                seekbar.progress = 12
            }
        }
    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        when (p0?.id) {
            R.id.switch_on_off -> {
                toast("Switch: ${if (switch_on_off.isChecked) "true" else "false"}")
                //switch_on_off.isChecked
            }
            R.id.check_on_off -> {
                toast("CheckBox: ${if (check_on_off.isChecked) "true" else "false"}")
                //check_on_off.isChecked = true
            }
            R.id.radio_on -> {
                toast("Radion on: ${if (check_on_off.isChecked) "true" else "false"}")
                //check_on_off.isChecked = true
            }
            R.id.radio_off -> {
                toast("Radio off: ${if (check_on_off.isChecked) "true" else "false"}")
                //check_on_off.isChecked = true
            }
        }
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        text_seekbar_value.text = "Valor seekbar: $p1"
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
        toast("Track started")
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
        toast("Track stoped")
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (p0?.id) {
            R.id.spinner_static -> {
                val texto = p0?.getItemAtPosition(p2)//toast("nothing")
            }

        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        toast("NOTHING")
    }

    private fun toast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }


}