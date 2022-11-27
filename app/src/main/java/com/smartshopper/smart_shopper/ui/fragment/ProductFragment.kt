package com.smartshopper.smart_shopper.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.smartshopper.smart_shopper.database.AppDatabase
import com.smartshopper.smart_shopper.database.ProductEntities
import com.smartshopper.smart_shopper.databinding.FragmentProductBinding
import com.smartshopper.smart_shopper.utils.Constant
import com.smartshopper.smart_shopper.utils.Utils

class ProductFragment : Fragment() {

    lateinit var binding: FragmentProductBinding
    lateinit var db: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDb()
        initListener()
    }

    private fun initListener() = binding.run {
        addGroceryBtn.setOnClickListener {
            if (validation()) {
                val data = ProductEntities(
                    storeName = storeNameTv.text.toString(),
                    productName = productNameTv.text.toString(),
                    price = priceTv.text.toString()
                )
                db.Dao().insertProduct(data)
                storeNameTv.setText("")
                productNameTv.setText("")
                priceTv.setText("")
                Utils.successToast(requireActivity(),"Successfully added")
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

    private fun validation(): Boolean = binding.run {
        return when {
            storeNameTv.text.isEmpty() -> {
                Utils.errorToast(requireActivity(), "Store Name must not be empty")
                false
            }
            productNameTv.text.isEmpty() -> {
                Utils.errorToast(requireActivity(), "Product Name must not be empty")
                false
            }
            priceTv.text.isEmpty() -> {
                Utils.errorToast(requireActivity(), "Price must not be empty")
                false
            }
            else -> {
                true
            }
        }
    }

}