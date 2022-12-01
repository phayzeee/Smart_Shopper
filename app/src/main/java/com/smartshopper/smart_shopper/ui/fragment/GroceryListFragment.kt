package com.smartshopper.smart_shopper.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.smartshopper.smart_shopper.R
import com.smartshopper.smart_shopper.adapter.FinalizeAdapter
import com.smartshopper.smart_shopper.adapter.GroceryListAdapter
import com.smartshopper.smart_shopper.adapter.SuggestionAdapter
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
//        groceryButton()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initListener() = binding.run {


        addGroceryBtn.setOnClickListener {
            if (db.Dao().getGrocery().isNotEmpty()) {
                finalizeBtnSetup()
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
//            groceryButton()
        }

        showSuggestionBtn.setOnClickListener {
            if (db.Dao().getGrocery().isNotEmpty()) {
//                findNavController().navigate(R.id.buyAgainFragment)
//                (activity as MainActivity).selectProductTab(2)
                addGroceryProduct()
//                db.Dao().getGrocery().mapIndexed { index, groceryEntities ->
//                    db.Dao().deleteGrocery(groceryEntities)
//                }
//                setupAdapter()
                //    Utils.successToast(requireActivity(), "Added Successfully")
            } else {
                Utils.errorToast(requireActivity(), "No Grocery Found")
            }
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

        val data = ArrayList<GroceryEntities>()
        data.clear()

        data.addAll(db.Dao().getGrocery())
        db.Dao().getProducts().mapIndexed { index, item ->
            data.add(
                GroceryEntities(
                    storeName = item.storeName,
                    productName = item.productName,
                    price = item.price,
                    quantity = item.quantity
                )
            )
        }
        grocery.clear()
        product.clear()
        grocery.addAll(data.distinct())

        for (i in 0 until data.size) {
            for (j in i until data.size) {
                if (data[i].productName == data[j].productName) {
                    if (data[i].price?.replace("$", "")?.toFloat()!! > data[j].price?.replace("$", "")?.toFloat()!!)
                    {
                        grocery.remove(data[i])
                    }
                    else if (data[i].price?.replace("$", "")?.toFloat()!! < data[j].price?.replace("$", "")?.toFloat()!!){
                        grocery.remove(data[j])
                    }
                }
            }
        }
        val adapter = SuggestionAdapter(requireActivity())
        adapter.updateData(grocery)
        binding.rvFilterProd.adapter = adapter
        binding.rvFilterProd.isVisible = true
    }

    private fun finalizeBtnSetup(){
            val data = groceryListAdapter.groceryItemList
            grocery.clear()
            product.clear()
            grocery.addAll(data.distinct())

            for (i in 0 until data.size) {
                for (j in i until data.size) {
                    if (data[i].productName == data[j].productName) {
                        if (data[i].price?.replace("$", "")?.toFloat()!! > data[j].price?.replace("$", "")?.toFloat()!!)
                        {
                            grocery.remove(data[i])
                        }
                    }
                }
            }


        val adapter = FinalizeAdapter(requireActivity())
        adapter.updateData(grocery)
        binding.rvFinalizeBtn.adapter = adapter
        binding.rvFinalizeBtn.isVisible = true
    }



}