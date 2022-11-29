package com.smartshopper.smart_shopper.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smartshopper.smart_shopper.R
import com.smartshopper.smart_shopper.database.GroceryEntities
import com.smartshopper.smart_shopper.databinding.RowGroceryListBinding


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

    override fun onBindViewHolder(holder: GroceryListAdapter.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        var currentItem = groceryItemList[position]
        with(holder){
            binding.etProductName.setText(currentItem.productName)
            if (currentItem.quantity.isNullOrEmpty()){
                binding.etQuantity.setText("")
            } else {
                binding.etQuantity.setText(currentItem.quantity)
            }

            binding.etQuantity.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {
                    groceryItemList[position].quantity = p0.toString()
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return groceryItemList.size
    }


}