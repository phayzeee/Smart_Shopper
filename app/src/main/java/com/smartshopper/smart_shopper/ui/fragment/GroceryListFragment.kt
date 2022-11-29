package com.smartshopper.smart_shopper.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.smartshopper.smart_shopper.R
import com.smartshopper.smart_shopper.adapter.GroceryListAdapter
import com.smartshopper.smart_shopper.database.AppDatabase
import com.smartshopper.smart_shopper.database.GroceryEntities
import com.smartshopper.smart_shopper.database.ProductEntities
import com.smartshopper.smart_shopper.databinding.FragmentGroceryListBinding
import com.smartshopper.smart_shopper.ui.activity.MainActivity
import com.smartshopper.smart_shopper.utils.Constant
import com.smartshopper.smart_shopper.utils.Utils

class GroceryListFragment : Fragment() {

    lateinit var binding: FragmentGroceryListBinding
    lateinit var db: AppDatabase
    lateinit var groceryListAdapter: GroceryListAdapter
    var product = ArrayList<ProductEntities>()
    var grocery = ArrayList<GroceryEntities>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGroceryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDb()
        setupAdapter()
        initListener()
        groceryButton()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initListener() = binding.run {


        addGroceryBtn.setOnClickListener {
            if (db.Dao().getGrocery().isNotEmpty()) {
                addGroceryProduct()
                db.Dao().getGrocery().mapIndexed { index, groceryEntities ->
                    db.Dao().deleteGrocery(groceryEntities)
                }
                setupAdapter()
            } else {
                Utils.errorToast(requireActivity(), "No Grocery Found")
            }
        }

        llAddAnotherProduct.setOnClickListener {
            findNavController().navigate(R.id.productFragment)
            (activity as MainActivity).selectProductTab(0)

        }

        llEraseProduct.setOnClickListener {
            product.clear()
            if (db.Dao().getGrocery().isNotEmpty()) {
                db.Dao().deleteGrocery(db.Dao().getGrocery()[db.Dao().getGrocery().lastIndex])
                setupAdapter()
            } else {
                Utils.errorToast(requireActivity(), "No Grocery Found")
            }
            groceryButton()
        }
    }

    private fun setupDb() {
        db = Room.databaseBuilder(
            requireActivity(),
            AppDatabase::class.java, Constant.GROCERY
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()
    }

    private fun setupAdapter() {
        groceryListAdapter = GroceryListAdapter(requireActivity())
        groceryListAdapter.updateData(db.Dao().getGrocery() as ArrayList<GroceryEntities>)
        binding.rvGroceryList.adapter = groceryListAdapter
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun addGroceryProduct() {
        val data = groceryListAdapter.groceryItemList
        grocery.clear()


        for (i in 0 until data.size) {
            for (j in 0 until data.size) {
                if (data[i].productName == data[j].productName) {
                    if (data[i].price?.replace("$", "")?.toFloat()!! <= data[j].price?.replace(
                            "$",
                            ""
                        )
                            ?.toFloat()!!
                    ) {
                        grocery.add(data[i])
                    }
                } else if (data[i].productName != data[j].productName) {
                    grocery.add(data[i])
                }
            }
        }


        val filter = grocery.distinct()
        filter.mapIndexed { index, data ->
            product.add(
                ProductEntities(
                    storeName = data.storeName,
                    productName = data.productName,
                    price = data.price,
                    quantity = data.quantity
                )
            )
        }
        db.Dao().insertProduct(product.toList())

        findNavController().navigate(R.id.allProductFragment)
        (activity as MainActivity).selectProductTab(4)
    }

    private fun groceryButton(){
        if(groceryListAdapter.groceryItemList.isNullOrEmpty()){
            binding.addGroceryBtn.isEnabled = false
            binding.addGroceryBtn.alpha = 0.2f
        } else {
            binding.addGroceryBtn.isEnabled = true
            binding.addGroceryBtn.alpha = 1f
        }
    }
}