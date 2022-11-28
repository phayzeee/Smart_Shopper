package com.smartshopper.smart_shopper.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.smartshopper.smart_shopper.database.AppDatabase
import com.smartshopper.smart_shopper.database.ProductEntities
import com.smartshopper.smart_shopper.databinding.FragmentGroceryListBinding
import com.smartshopper.smart_shopper.utils.Constant

class GroceryListFragment : Fragment() {

    lateinit var binding: FragmentGroceryListBinding
    lateinit var db: AppDatabase
    var product = ArrayList<ProductEntities>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGroceryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDb()
        initListener()
    }

    private fun initListener() = binding.run {
        addGroceryBtn.setOnClickListener {
            product.clear()
            db.Dao().getGrocery().mapIndexed { index, data ->
                product.add(
                    ProductEntities(
                        storeName = data.storeName,
                        productName = data.productName,
                        price = data.price
                    )
                )
            }
            db.Dao().insertProduct(product.toList())
        }
    }

    private fun setupDb() {
        db = Room.databaseBuilder(
            requireActivity(),
            AppDatabase::class.java, Constant.GROCERY
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()
    }
}