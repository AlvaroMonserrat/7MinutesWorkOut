package com.rrat.a7minutesworkout

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rrat.a7minutesworkout.databinding.ItemHistoryRowBinding

class HistoryAdapter(val context: Context, val items: List<String>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    lateinit var binding: ItemHistoryRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemHistoryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(position % 2 == 0){
            holder.layoutMain.setBackgroundColor(ContextCompat.getColor(context, R.color.gray))
        }else{
            holder.layoutMain.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
        }

        holder.textViewPosition.text = (position + 1).toString()
        holder.textViewDate.text = items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(private val binding: ItemHistoryRowBinding) :  RecyclerView.ViewHolder(binding.root){
        val layoutMain = binding.layoutHistoryItem
        val textViewPosition = binding.textViewPosition
        val textViewDate = binding.textViewDate
    }
}