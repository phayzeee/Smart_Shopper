package com.smartshopper.smart_shopper.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smartshopper.smart_shopper.R
import com.smartshopper.smart_shopper.database.GroceryEntities
import com.smartshopper.smart_shopper.database.ProductEntities
import com.smartshopper.smart_shopper.databinding.RowGroceryListBinding
import com.smartshopper.smart_shopper.model.GroceryDate
import com.smartshopper.smart_shopper.model.GroceryList

class GroceryListAdapter(val context: Context) :
    RecyclerView.Adapter<GroceryListAdapter.ViewHolder>() {

    var groceryItemList = ArrayList<GroceryEntities>()

    fun updateData(list : ArrayList<GroceryEntities>){
        groceryItemList.clear()
        groceryItemList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = RowGroceryListBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_grocery_list, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: GroceryListAdapter.ViewHolder, position: Int) {
        var currentItem = groceryItemList[position]
        with(holder){
            binding.etProductName.setText(currentItem.productName)
            if (currentItem.quantity.isNullOrEmpty()){
                binding.etQuantity.setText("")
            } else {
                binding.etQuantity.setText(currentItem.quantity)
            }

        }
    }

    override fun getItemCount(): Int {
        return groceryItemList.size
    }


}