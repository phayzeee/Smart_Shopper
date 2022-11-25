package com.smartshopper.smart_shopper.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smartshopper.smart_shopper.R
import com.smartshopper.smart_shopper.databinding.RowDealsBinding
import com.smartshopper.smart_shopper.databinding.RowPreviousGroceryBinding
import com.smartshopper.smart_shopper.model.Deals
import com.smartshopper.smart_shopper.model.GroceryDate

class PreviousGroceryAdapter(val context: Context) :
    RecyclerView.Adapter<PreviousGroceryAdapter.ViewHolder>() {

    var dateList = ArrayList<GroceryDate>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = RowPreviousGroceryBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousGroceryAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_previous_grocery, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: PreviousGroceryAdapter.ViewHolder, position: Int) {
        var currentItem = dateList[position]
        with(holder){
            binding.tvDate.text = currentItem.date
        }
    }

    override fun getItemCount(): Int {
        return dateList.size
    }

}