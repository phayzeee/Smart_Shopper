package com.smartshopper.smart_shopper.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.smartshopper.smart_shopper.R
import com.smartshopper.smart_shopper.adapter.PreviousGroceryAdapter
import com.smartshopper.smart_shopper.database.AppDatabase
import com.smartshopper.smart_shopper.database.ProductEntities
import com.smartshopper.smart_shopper.databinding.FragmentBuyAgainBinding
import com.smartshopper.smart_shopper.model.GroceryDate
import com.smartshopper.smart_shopper.utils.Constant
import com.smartshopper.smart_shopper.utils.Utils

class BuyAgainFragment : Fragment() {

    lateinit var binding: FragmentBuyAgainBinding
    lateinit var db: AppDatabase
    lateinit var dateAdapter: PreviousGroceryAdapter
    var product = ArrayList<ProductEntities>()
    var dateList = ArrayList<GroceryDate>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuyAgainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDb()
        setData()
        setupAdapter()
        initListener()
    }

    private fun initListener() = binding.run {
        tvHeadingDate.text = ""
        etProductName.setText("")
        etQuantity.setText("")
        dateAdapter.setOnDateClickListener { groceryDate, i ->
            llBuyAgain.visibility = View.VISIBLE
            llPreviousList.visibility = View.GONE

            when (i) {
                0 -> {
                    tvHeadingDate.text = "8 Aug 2022"
                    etProductName.setText("Parsley")
                    etQuantity.setText("$3")
                }
                1 -> {
                    tvHeadingDate.text = "20 Sep 2022"
                    etProductName.setText("Eggs")
                    etQuantity.setText("$4")
                }
                2 -> {
                    tvHeadingDate.text = "3 Oct 2022"
                    etProductName.setText("Meat")
                    etQuantity.setText("$6")
                }
                3 -> {
                    tvHeadingDate.text = "15 Nov 2022"
                    etProductName.setText("Milk")
                    etQuantity.setText("$12")
                }
            }
        }

        addGroceryBtn.setOnClickListener {
            product.clear()
            product.add(
                ProductEntities(
                    storeName = "Rooftop Mart",
                    productName = etProductName.text.toString(),
                    price = etQuantity.text.toString()
                )
            )
            db.Dao().insertProduct(product.toList())
            Utils.successToast(requireActivity(), "Added Successfully")
            findNavController().navigate(R.id.allProductFragment)
        }
    }

    private fun setupAdapter() {
        dateAdapter = PreviousGroceryAdapter(requireActivity())
        dateAdapter.updateData(dateList)
        binding.rvPreviousGrocery.adapter = dateAdapter
    }

    private fun setData() {
        dateList.clear()
        dateList.add(GroceryDate("8 Aug 2022"))
        dateList.add(GroceryDate("20 Sep 2022"))
        dateList.add(GroceryDate("3 Oct 2022"))
        dateList.add(GroceryDate("15 Nov 2022"))
    }

    private fun setupDb() {
        db = Room.databaseBuilder(
            requireActivity(),
            AppDatabase::class.java, Constant.GROCERY
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()
    }
}