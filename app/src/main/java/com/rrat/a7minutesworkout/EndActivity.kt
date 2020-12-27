package com.rrat.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rrat.a7minutesworkout.databinding.ActivityEndBinding
import java.text.SimpleDateFormat
import java.util.*

class EndActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEndBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEndBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarEndActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbarEndActivity.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.finishButton.setOnClickListener {
            finish()
        }

        addDateToDataBase()
    }

    private fun addDateToDataBase(){
        val calendar = Calendar.getInstance()
        val dateTime = calendar.time

        Log.i("DATE", "" + dateTime)

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)

        val dbHandler = SqliteOpenHelper(this, null)
        dbHandler.addDate(date)

    }
}