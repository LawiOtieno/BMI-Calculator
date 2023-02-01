package com.streetgrandmaster.bmicalculator

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import com.streetgrandmaster.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var gender : String = "male"
    private var height : Int = 173
    private var weight : Int = 64
    private var age : Int = 29

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getUserGender()
        getUserHeight()
        getUserWeight()
        getUserAge()
        onBtnClicked()
    }

    private fun getUserAge() {
        binding.mainActivityTvAddAge.setOnClickListener {
            age++
            binding.mainActivityTvAge.text = age.toString()
        }
        binding.mainActivityTvDecAge.setOnClickListener {
            age--
            binding.mainActivityTvAge.text = age.toString()
        }
    }

    private fun getUserWeight() {
        binding.mainActivityTvAddWeight.setOnClickListener {
            weight++
            binding.mainActivityTvWeight.text = weight.toString()
        }
        binding.mainActivityTvDecWeight.setOnClickListener {
            weight--
            binding.mainActivityTvWeight.text = weight.toString()
        }
    }

    private fun getUserHeight() {
        binding.mainActivitySeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(SeekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.mainActivityTvHeight.text = progress.toString()
                height = progress
            }

            override fun onStartTrackingTouch(SeekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(SeekBar: SeekBar?) {

            }

        })
    }

    private fun getUserGender() {
        binding.mainActivityLayoutMale.setOnClickListener {
            binding.mainActivityLayoutFemale.setBackgroundResource(R.drawable.rectangle_black_7dp)
            binding.mainActivityLayoutMale.setBackgroundResource(R.drawable.rectangle_purple_outline)
            gender = "male"
        }
        binding.mainActivityLayoutFemale.setOnClickListener {
            binding.mainActivityLayoutFemale.setBackgroundResource(R.drawable.rectangle_purple_outline)
            binding.mainActivityLayoutMale.setBackgroundResource(R.drawable.rectangle_black_7dp)
            gender = "female"
        }
    }

    private fun onBtnClicked() {
        binding.mainActivityBtnCalculateBmi.setOnClickListener {
////            Shows log the output to check if everything is working
//            Log.d("myLog", "onBtnClicked: $gender $height $weight $age")

            showBmiResult()
        }
    }

    private fun showBmiResult(){
//        Now create dialog
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_dialog)
        var imgClose : ImageView = dialog.findViewById(R.id.dialogImgClose)
        var bmiValue : TextView = dialog.findViewById(R.id.dialogTvBmiValue)

        imgClose.setOnClickListener{
            dialog.dismiss()
        }
        bmiValue.text = String.format("%.1f",calculateBmi())
//        Show dialog here
        dialog.show()
    }
    private  fun  calculateBmi() : Double{
        val bmi = (weight/(height*height).toDouble())*10000
        return bmi
    }
}