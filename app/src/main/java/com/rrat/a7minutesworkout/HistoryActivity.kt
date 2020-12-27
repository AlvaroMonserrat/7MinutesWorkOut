package com.rrat.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.rrat.a7minutesworkout.databinding.ActivityHistoryBinding
import com.rrat.a7minutesworkout.databinding.ActivityIMCBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarHistoryActivity)
        val actionbar = supportActionBar
        if(actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "HISTORY"
        }

        binding.toolbarHistoryActivity.setNavigationOnClickListener {
            onBackPressed()
        }

        getAllCompletedDates()
    }

    private fun getAllCompletedDates(){
        val dbHelper = SqliteOpenHelper(this, null)
        val allCompletedDatesList = dbHelper.getAllCompletedDatesList()

        if(allCompletedDatesList.size > 0){
            binding.textViewNoDataAvailable.visibility = View.GONE
            binding.textViewTitleHistory.visibility = View.VISIBLE
            binding.recyclerViewHistory.visibility = View.VISIBLE

            binding.recyclerViewHistory.layoutManager = LinearLayoutManager(this)

            val itemAdapter = HistoryAdapter(this, allCompletedDatesList)

            binding.recyclerViewHistory.adapter = itemAdapter

        }else{
            binding.textViewNoDataAvailable.visibility = View.VISIBLE
            binding.textViewTitleHistory.visibility = View.GONE
            binding.recyclerViewHistory.visibility = View.GONE
        }


    }
}