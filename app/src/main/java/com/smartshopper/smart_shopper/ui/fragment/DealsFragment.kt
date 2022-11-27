package com.smartshopper.smart_shopper.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.smartshopper.smart_shopper.database.AppDatabase
import com.smartshopper.smart_shopper.database.DealsEntities
import com.smartshopper.smart_shopper.databinding.FragmentDealsBinding
import com.smartshopper.smart_shopper.utils.Constant
import com.smartshopper.smart_shopper.utils.Utils


class DealsFragment : Fragment() {
    lateinit var binding: FragmentDealsBinding
    lateinit var db: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDealsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDb()
        initListener()
        setupSpinner()
    }

    private fun initListener() = binding.run {
        binding.addGroceryBtn.setOnClickListener {
            if(validation()){
                val data = DealsEntities(
                    storeName = spinner.selectedItem.toString(),
                    product = productNameTv.text.toString(),
                    deal = dealTv.text.toString(),
                    duration = durationTv.text.toString()
                )
                db.Dao().insertDeal(data)

                storeNameTv.setText("")
                productNameTv.setText("")
                dealTv.setText("")
                durationTv.setText("")

                Utils.successToast(requireActivity(), "Successfully added")
            }

        }

        addMoreBtn.setOnClickListener {
            storeNameTv.setText("")
            productNameTv.setText("")
            dealTv.setText("")
            durationTv.setText("")
        }
    }

    private fun setupDb() {
        db = Room.databaseBuilder(
            requireActivity(),
            AppDatabase::class.java, Constant.GROCERY
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()
    }

    private fun setupSpinner() {

        //setup storeNAme
        val store = ArrayList<String>()
        store.clear()
        store.add("Select Store")
        db.Dao().getDeals().mapIndexed { index, dealsEntities ->
            store.add(dealsEntities.storeName.toString())
        }
        val dataAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, store)

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = dataAdapter


        //setup product Name
        val product = ArrayList<String>()
        product.clear()
        product.add("Select Product")
        db.Dao().getDeals().mapIndexed { index, dealsEntities ->
            product.add(dealsEntities.product.toString())
        }
        val dataAdapter1: ArrayAdapter<String> =
            ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, product)

        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner1.adapter = dataAdapter1
    }

    private fun validation(): Boolean = binding.run {
        return when {
            spinner.selectedItem.equals("Select Store")-> {
                Utils.errorToast(requireActivity(), "Store Name must not be empty")
                false
            }
            spinner.selectedItem.equals("Select Product") -> {
                Utils.errorToast(requireActivity(), "Product Name must not be empty")
                false
            }
            dealTv.text.isEmpty() -> {
                Utils.errorToast(requireActivity(), "Deal must not be empty")
                false
            }
            durationTv.text.isEmpty() -> {
                Utils.errorToast(requireActivity(), "Deal duration must not be empty")
                false
            }
            else -> {
                true
            }
        }
    }
}