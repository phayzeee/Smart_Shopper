package com.smartshopper.smart_shopper.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.smartshopper.smart_shopper.R
import com.smartshopper.smart_shopper.database.AppDatabase
import com.smartshopper.smart_shopper.database.ProductEntities
import com.smartshopper.smart_shopper.databinding.FragmentGroceryListBinding
import com.smartshopper.smart_shopper.utils.Constant
import com.smartshopper.smart_shopper.utils.Utils

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

        addGroceryBtn.isEnabled = true
        addGroceryBtn.alpha = 1f

        etProductName.setText(db.Dao().getGrocery()[0].productName)
        etQuantity.setText(db.Dao().getGrocery()[0].price)

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
            Utils.successToast(requireActivity(), "Added Successfully")
            findNavController().navigate(R.id.allProductFragment)
        }

        llAddAnotherProduct.setOnClickListener{
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
            findNavController().navigate(R.id.productFragment)
        }

        llEraseProduct.setOnClickListener {
            product.clear()
            etProductName.setText("")
            etQuantity.setText("")
            addGroceryBtn.isEnabled = false
            addGroceryBtn.alpha = 0.1f
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