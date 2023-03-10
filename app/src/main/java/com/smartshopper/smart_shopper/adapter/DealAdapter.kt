package com.smartshopper.smart_shopper.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smartshopper.smart_shopper.R
import com.smartshopper.smart_shopper.database.DealsEntities
import com.smartshopper.smart_shopper.databinding.RowDealsBinding

class DealAdapter(val context: Context) :
    RecyclerView.Adapter<DealAdapter.ViewHolder>() {

    var dealList = ArrayList<DealsEntities>()


    fun updateData(list : ArrayList<DealsEntities>){
        dealList.clear()
        dealList.addAll(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DealAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_deals, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: DealAdapter.ViewHolder, position: Int) {
        val currentItem = dealList[position]
            with(holder) {
               binding.productNameTv.text = currentItem.product
               binding.storeNameTv.text = currentItem.storeName
               binding.dealTv.text = currentItem.deal
               binding.durationTv.text = currentItem.duration
            }
    }

    override fun getItemCount(): Int {
        return dealList.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = RowDealsBinding.bind(itemView)
    }
}