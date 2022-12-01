package com.smartshopper.smart_shopper.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smartshopper.smart_shopper.R
import com.smartshopper.smart_shopper.database.GroceryEntities
import com.smartshopper.smart_shopper.databinding.ListRowBinding

class FinalizeAdapter (val context: Context) :
    RecyclerView.Adapter<FinalizeAdapter.ViewHolder>() {

    var productList = ArrayList<GroceryEntities>()


    fun updateData(list : ArrayList<GroceryEntities>){
        productList.clear()
        productList.addAll(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FinalizeAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_row, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: FinalizeAdapter.ViewHolder, position: Int) {
        val currentItem = productList[position]
        with(holder) {
            binding.productNameTv.text = currentItem.productName
            binding.storeNameTv.text = currentItem.storeName
            binding.priceTv.text = currentItem.price
            if(currentItem.quantity == null || currentItem.quantity.isNullOrEmpty() || currentItem.quantity.equals("null")){
                binding.quantityTv.text = "1 quantity"
            }else{
                binding.quantityTv.text = currentItem.quantity+" quantities"
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ListRowBinding.bind(itemView)
    }
}