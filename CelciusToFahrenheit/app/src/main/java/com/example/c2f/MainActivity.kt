package com.example.c2f

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar;


class MainActivity : AppCompatActivity() {
    private lateinit var cSeekBar: SeekBar
    private lateinit var fSeekBar: SeekBar
    private lateinit var cTextView: TextView
    private lateinit var fTextView: TextView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get components by their id
        cSeekBar = findViewById(R.id.cBar)
        fSeekBar = findViewById(R.id.fBar)
        cTextView = findViewById(R.id.cNumber)
        fTextView = findViewById(R.id.fNumber)

        cSeekBar.max = 100
        cSeekBar.min = 0
        fSeekBar.max = 212
        fSeekBar.min = 0

        cTextView.text = "0"
        fTextView.text = "32"

        cSeekBar.progress = 0
        fSeekBar.progress = 32

        cSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            var currVal: Int = 0
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                currVal = p1

                cTextView.text = p1.toString()
//                fTextView.text = "%.2f".format(p1 * 1.8f + 32.0)
                fTextView.text = (p1 * 1.8f + 32).toInt().toString()
                fSeekBar.progress = (p1 * 1.8f + 32).toInt()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                // show snack bar if necessary
                showSnackbar(currVal)
            }
        })

        fSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            // keep current value of bar
            var currVal: Int = 0
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                currVal = p1

                fTextView.text = p1.toString()
//                cTextView.text = "%.2f".format((p1 - 32)/1.8f)
                cTextView.text = ((p1 - 32)/1.8f).toInt().toString()
                cSeekBar.progress = ((p1 - 32)/1.8f).toInt()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                if(currVal < 32){

                    fTextView.text = (32).toString()
                    cTextView.text = (0).toString()
                    fSeekBar.progress = 32
                    cSeekBar.progress = 0
                }

                // show snack bar if necessary
                showSnackbar(((currVal - 32)/1.8f).toInt())
            }

        })
    }

    fun showSnackbar(currVal : Int){
        val layout : View = findViewById(R.id.layout)
        if(currVal <= 20){
            val wishWarmBar : Snackbar = Snackbar.make(layout, "I wish it were warmer.", Snackbar.LENGTH_LONG)
            wishWarmBar.show()
        }
        else{
            val wishColdBar : Snackbar = Snackbar.make(layout, "I wish it were colder.", Snackbar.LENGTH_SHORT)
            wishColdBar.show()
        }
    }

}