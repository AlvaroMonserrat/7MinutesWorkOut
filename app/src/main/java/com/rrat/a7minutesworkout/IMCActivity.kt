package com.rrat.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.rrat.a7minutesworkout.databinding.ActivityExerciseBinding
import com.rrat.a7minutesworkout.databinding.ActivityIMCBinding
import java.math.BigDecimal
import java.math.RoundingMode

class IMCActivity : AppCompatActivity() {

    private lateinit var binding:  ActivityIMCBinding
    val METRICS_UNITS_VIEW = "METRICS_UNIT_VIEW"
    val US_UNITS_VIEW = "US_UNIT_VIEW"

    var currentVisibleView: String = METRICS_UNITS_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIMCBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarIMCActivity)
        val actionbar = supportActionBar
        if(actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "CALCULATE IMC"
        }

        binding.toolbarIMCActivity.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.buttonCalculateUnits.setOnClickListener {
            if(currentVisibleView == METRICS_UNITS_VIEW){
                if(validateMetricUnits()){
                    val heightValue: Float = binding.editTextMetricUnitHeight.text.toString().toFloat() / 100
                    val weightValue: Float = binding.editTextMetricUnitWeight.text.toString().toFloat()

                    val imc = weightValue / (heightValue*heightValue)
                    displayIMCResult(imc)
                }else{
                    Toast.makeText(this, "Please enter valid values", Toast.LENGTH_LONG).show()
                }
            }else{
                if(validateUSUnits()){
                    val usUnitHeightValueFeet: String = binding.editTextUSUnitHeightFeet.text.toString()
                    val usUnitHeightValueInch: String = binding.editTextUSUnitHeightInch.text.toString()
                    val usUnitWeightValue: Float = binding.editTextUSUnitWeight.text.toString().toFloat()

                    val heightValue = usUnitHeightValueInch.toFloat() + usUnitHeightValueFeet.toFloat() * 12

                    val imc = 703 * (usUnitWeightValue / (heightValue * heightValue))
                    displayIMCResult(imc)


                }else{
                    Toast.makeText(this, "Please enter valid values", Toast.LENGTH_LONG).show()
                }
            }

        }

        makeVisibleMetricUnitsView()
        binding.radioGroup.setOnCheckedChangeListener{
            group, checkID ->
            if(checkID == R.id.rbMetricUnits){
                makeVisibleMetricUnitsView()
            }else{
                makeVisibleUSUnitsView()
            }
        }
    }


    private fun validateMetricUnits(): Boolean{
        var isValid = true

        if(binding.editTextMetricUnitWeight.text.toString().isEmpty()){
            isValid = false
        }else if(binding.editTextMetricUnitHeight.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }

    private fun validateUSUnits(): Boolean{
        var isValid = true

        when {
            binding.editTextUSUnitWeight.text.toString().isEmpty() -> {
                isValid = false
            }
            binding.editTextUSUnitHeightFeet.text.toString().isEmpty() -> {
                isValid = false
            }
            binding.editTextUSUnitHeightInch.text.toString().isEmpty() -> {
                isValid = false
            }
        }
        return isValid
    }


    private fun makeVisibleMetricUnitsView(){
        currentVisibleView = METRICS_UNITS_VIEW
        binding.textInputMetricUnitWeight.visibility = View.VISIBLE
        binding.textInputMetricUnitHeight.visibility = View.VISIBLE

        binding.editTextMetricUnitWeight.text!!.clear()
        binding.editTextMetricUnitHeight.text!!.clear()

        binding.textInputUSUnitWeight.visibility = View.INVISIBLE
        binding.linearLayoutUSUnitsHeight.visibility = View.INVISIBLE

        binding.linearLayoutDisplayIMCResult.visibility = View.INVISIBLE
    }

    private fun makeVisibleUSUnitsView(){
        currentVisibleView = US_UNITS_VIEW
        binding.textInputMetricUnitWeight.visibility = View.INVISIBLE
        binding.textInputMetricUnitHeight.visibility = View.INVISIBLE

        binding.editTextUSUnitWeight.text!!.clear()
        binding.editTextUSUnitHeightFeet.text!!.clear()
        binding.editTextUSUnitHeightInch.text!!.clear()

        binding.textInputUSUnitWeight.visibility = View.VISIBLE
        binding.linearLayoutUSUnitsHeight.visibility = View.VISIBLE

        binding.linearLayoutDisplayIMCResult.visibility = View.INVISIBLE
    }

    private fun displayIMCResult(imc: Float) {
        val imcLabel: String
        val imcDescription: String

        if (imc.compareTo(15f) <= 0) {
            imcLabel = "Very severely underweight"
            imcDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (imc.compareTo(15f) > 0 && imc.compareTo(16f) <= 0) {
            imcLabel = "Severely underweight"
            imcDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (imc.compareTo(16f) > 0 && imc.compareTo(18.5f) <= 0) {
            imcLabel = "Underweight"
            imcDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (imc.compareTo(18.5f) > 0 && imc.compareTo(25f) <= 0) {
            imcLabel = "Normal"
            imcDescription = "Congratulations! You are in good shape!"
        } else if (imc.compareTo(25) > 0 && imc.compareTo(30f) <= 0) {
            imcLabel = "Overweight"
            imcDescription = "Oops! You really need to take care of your better! Eat less!"
        } else if (imc.compareTo(30f) > 0 && imc.compareTo(35f) <= 0) {
            imcLabel = "Obese Class I"
            imcDescription = "Oops! You really need to take care of your better! Eat less!"
        } else if (imc.compareTo(35f) > 0 && imc.compareTo(40f) <= 0) {
            imcLabel = "Obese Class II"
            imcDescription = "Oops! You really need to take care of your better! Eat less!"
        } else {
            imcLabel = "Obese Class III"
            imcDescription = "Oops! You really need to take care of your better! Eat less!"
        }

        binding.linearLayoutDisplayIMCResult.visibility = View.VISIBLE

        /*
        binding.textViewYourIMC.visibility = View.VISIBLE
        binding.textViewIMCValue.visibility = View.VISIBLE
        binding.textViewIMCType.visibility = View.VISIBLE
        binding.textViewIMCDescription.visibility = View.VISIBLE
        */

        val imcValue = BigDecimal(imc.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding.textViewIMCValue.text = imcValue
        binding.textViewIMCType.text = imcLabel
        binding.textViewIMCDescription.text = imcDescription
    }


}