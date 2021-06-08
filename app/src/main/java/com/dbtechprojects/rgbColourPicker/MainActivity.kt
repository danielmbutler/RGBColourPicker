package com.dbtechprojects.rgbColourPicker

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dbtechprojects.rgbColourPicker.databinding.ActivityMainBinding
import com.dbtechprojects.rgbColourPicker.models.RGBModel


// used for logging
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    // viewmodel to hold RGB values, this will persist during changes in the android lifecycle (Rotation)
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    // list of views to make setting values easier
    private lateinit var mainTextViews: List<TextView>
    private lateinit var mainSeekBars: List<SeekBar>

    // seekbar progress
    private var seekbarProgress = RGBModel(R = 0, G = 0, B = 0)


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViewGroups()
        setSeekBarListeners()
        initObservers()
    }


    //observer RGB value changes in viewModel and react accordingly
    @RequiresApi(Build.VERSION_CODES.M)
    private fun initObservers() {
        viewModel.RGBvalue.observe(this, Observer {
            Log.d(TAG, "initObservers: current Color, $it")

            // set values for BackGround and text
            binding.mainBackground.setBackgroundColor(Color.rgb(it.R, it.G, it.B))
            binding.mainCurrentRgbValue.text = "Current values : R: ${it.R}, G: ${it.G}, B: ${it.B}"


            // set statusBar color programmatically, this can only be done on Build versions higher than 21
            if (Build.VERSION.SDK_INT >= 21) {
                window.statusBarColor = Color.rgb(it.R, it.G, it.B)
            }

            // adjust colors in view to accommodate new background

            // if blue value is lower than 124 text and seekbars can be difficult to see, so they should be changed to white
            if (it.B < 124) {
                for (i in mainTextViews) {
                    i.setTextColor(Color.WHITE)
                }
                for (i in mainSeekBars) {
                    i.progressTintList = ColorStateList.valueOf(Color.WHITE)
                    i.thumb.setTint(Color.WHITE)
                }
            } else { // otherwise change back to original colors
                for (i in mainTextViews) {
                    i.setTextColor(Color.BLACK)
                }
                for (i in mainSeekBars) {
                    i.progressTintList = ColorStateList.valueOf(Color.BLACK)
                    i.thumb.setTint(Color.BLACK)
                }
            }
        })
    }

    // configure SeekBars to use MainActivity as the listener to receive updates when the seekbar changes and set max value of seekbars
    private fun setSeekBarListeners() {

        for (i in mainSeekBars) {
            i.setOnSeekBarChangeListener(this)
            i.max = 255 //max value of seek bar
        }

    }


    // used to respond to changes in seekbars
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (seekBar != null) {
            when (seekBar.id) {
                R.id.main_R_seekBar -> {
                    seekbarProgress.R = progress
                    viewModel.setRGBValue(seekbarProgress)
                }
                R.id.main_B_seekBar -> {
                    seekbarProgress.B = progress
                    viewModel.setRGBValue(seekbarProgress)
                }
                R.id.main_G_seekBar -> {
                    seekbarProgress.G = progress
                    viewModel.setRGBValue(seekbarProgress)
                }
            }
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }


    // combine views into groups to make setting values easier
    private fun setViewGroups() {
        mainSeekBars = listOf(binding.mainRSeekBar, binding.mainBSeekBar, binding.mainGSeekBar)

        mainTextViews = listOf(
            binding.mainTitle,
            binding.mainCurrentRgbValue,
            binding.mainDescriptionTextview,
            binding.mainRTv,
            binding.mainBTv,
            binding.mainGTv,

            )
    }
}