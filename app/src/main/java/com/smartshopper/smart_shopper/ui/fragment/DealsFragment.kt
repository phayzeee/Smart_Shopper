package com.smartshopper.smart_shopper.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.smartshopper.smart_shopper.database.AppDatabase
import com.smartshopper.smart_shopper.database.DealsEntities
import com.smartshopper.smart_shopper.databinding.FragmentDealsBinding
import com.smartshopper.smart_shopper.model.Deals
import com.smartshopper.smart_shopper.model.ProductDeal
import com.smartshopper.smart_shopper.utils.Constant
import com.smartshopper.smart_shopper.utils.Utils


class DealsFragment : Fragment() {
    lateinit var binding: FragmentDealsBinding
    lateinit var db: AppDatabase
    var dealsList = ArrayList<Deals>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDealsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        setupDb()
        initListener()
        setupSpinner()
    }

    private fun initListener() = binding.run {
        binding.addGroceryBtn.setOnClickListener {
            if(validation()){
                val data = DealsEntities(
                    storeName = spinner.selectedItem.toString(),
                    product = spinner1.selectedItem.toString(),
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
        dealsList.mapIndexed { index, dealsEntities ->
            store.add(dealsEntities.storeName.toString())
        }
        val dataAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, store)

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = dataAdapter


        binding.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                setupProductSp(store[position])
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {}
        }

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


    private fun setData(){
        dealsList.clear()
        val product1 = arrayListOf(
            ProductDeal(" 4 Coca-Cola Soda Pop Classic", "$15.6","2 Dec - 5 Dec"),
            ProductDeal("1 Lobster & 2Crab", "$25.6","1 Dec - 10 Dec")
        )
        dealsList.add(Deals("Albertons", product1))
        val product2= arrayListOf(
            ProductDeal("10 Dinner Rolls", "$5.49","1 jan 23 - 10 jan 23"),
            ProductDeal("2 Bread", "$3","1 jan 23 - 20 jan 23"),
            ProductDeal("3 Vanilma  Cupcakes", "$8","1 Dec - 10 Dec")
        )
        dealsList.add(Deals("Safeway", product2))
        val product3= arrayListOf(
            ProductDeal("LEGO TOY Complete set", "$50.1","1 Dec - 10 Dec"),
        )
        dealsList.add(Deals("Target", product3))
        val product4 = arrayListOf(
            ProductDeal("Mazola Corn Oil 2 cane", "$7","2 Dec - 4 Dec"),
            ProductDeal("Dave's Killer Bread and 3 packet eggs", "$10","5 Dec - 30 Dec"),
            ProductDeal("Sevan Cupcakes 28 oz", "$2","1 feb 23 - 2 feb 23")
        )
        dealsList.add(Deals("Walmart", product4))

        val product5 = arrayListOf(
            ProductDeal("Mazola Corn Oil 2 cane", "$7","2 Dec - 4 Dec"),
            ProductDeal("Dave's Killer Bread and 3 packet eggs", "$10","5 Dec - 30 Dec"),
            ProductDeal("Sevan Cupcakes 28 oz", "$2","1 feb 23 - 2 feb 23")
        )
        dealsList.add(Deals("Bashas", product5))

        val product6 = arrayListOf(
            ProductDeal("Mazola Corn Oil 2 cane", "$7","2 Dec - 4 Dec"),
            ProductDeal("Dave's Killer Bread and 3 packet eggs", "$10","5 Dec - 30 Dec"),
            ProductDeal("Sevan Cupcakes 28 oz", "$2","1 feb 23 - 2 feb 23")
        )
        dealsList.add(Deals("Giant Eagle", product6))

        val product7 = arrayListOf(
            ProductDeal("Mazola Corn Oil 2 cane", "$7","2 Dec - 4 Dec"),
            ProductDeal("Dave's Killer Bread and 3 packet eggs", "$10","5 Dec - 30 Dec"),
            ProductDeal("Sevan Cupcakes 28 oz", "$2","1 feb 23 - 2 feb 23")
        )
        dealsList.add(Deals("Aldi", product7))

        val product8 = arrayListOf(
            ProductDeal("Mazola Corn Oil 2 cane", "$7","2 Dec - 4 Dec"),
            ProductDeal("Dave's Killer Bread and 3 packet eggs", "$10","5 Dec - 30 Dec"),
            ProductDeal("Sevan Cupcakes 28 oz", "$2","1 feb 23 - 2 feb 23")
        )
        dealsList.add(Deals("Whole Foods", product8))

        val product9 = arrayListOf(
            ProductDeal("Mazola Corn Oil 2 cane", "$7","2 Dec - 4 Dec"),
            ProductDeal("Dave's Killer Bread and 3 packet eggs", "$10","5 Dec - 30 Dec"),
            ProductDeal("Sevan Cupcakes 28 oz", "$2","1 feb 23 - 2 feb 23")
        )
        dealsList.add(Deals("Trader Joes", product9))

    }

    private fun setupProductSp(store: String){
        //setup product Name
        val productList = ArrayList<ProductDeal>()
        val product = ArrayList<String>()
        product.clear()
        productList.clear()
        product.add("Select Product")
        dealsList.mapIndexed { index, dealsEntities ->
            if(store == dealsEntities.storeName){
                productList.addAll(dealsEntities.product)
                dealsEntities.product.mapIndexed { index, productDeal ->
                    product.add(productDeal.name.toString())
                }
            }
        }
        val dataAdapter1: ArrayAdapter<String> =
            ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, product)

        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner1.adapter = dataAdapter1


        binding.spinner1.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                productList.mapIndexed { index, productDeal ->
                    if(productDeal.name==product[position]){
                        binding.dealTv.setText(productDeal.deal)
                        binding.durationTv.setText(productDeal.duration)
                    }
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {}
        }
    }
}