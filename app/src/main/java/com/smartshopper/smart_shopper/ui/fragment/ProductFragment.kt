package com.smartshopper.smart_shopper.ui.fragment

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.smartshopper.smart_shopper.database.AppDatabase
import com.smartshopper.smart_shopper.database.GroceryEntities
import com.smartshopper.smart_shopper.database.ProductEntities
import com.smartshopper.smart_shopper.databinding.FragmentProductBinding
import com.smartshopper.smart_shopper.model.Product
import com.smartshopper.smart_shopper.model.Store
import com.smartshopper.smart_shopper.ui.activity.MainActivity
import com.smartshopper.smart_shopper.utils.Constant
import com.smartshopper.smart_shopper.utils.Utils


class ProductFragment : Fragment() {

    lateinit var binding: FragmentProductBinding
    lateinit var db: AppDatabase
    var storeList = ArrayList<Store>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  setData()
        setupDb()
       // setupSpinner()
        initListener()
    }

    private fun initListener() = binding.run {
        addGroceryBtn.setOnClickListener {
            if (validation()) {
                val data = GroceryEntities(
                    storeName = storeNameTv.text.toString(),
                    productName = productNameTv.text.toString(),
                    price = "$" + priceTv.text.toString()
                )
                db.Dao().insertGrocery(data)
//                setupSpinner()
//                setupProductSp("")
                storeNameTv.setText("")
                productNameTv.setText("")
                priceTv.setText("")

                Utils.successToast(requireActivity(), "Added to Grocery List")
                findNavController().navigate(com.smartshopper.smart_shopper.R.id.groceryListFragment)
                (activity as MainActivity).selectProductTab(3)
            }
        }

        addMoreBtn.setOnClickListener {
//            setupSpinner()
//            setupProductSp("")
            if(validation()){
                val prodData = ProductEntities(
                    storeName = storeNameTv.text.toString(),
                    productName = productNameTv.text.toString(),
                    price = "$" + priceTv.text.toString()
                )
                db.Dao().insertProduct(prodData)
            }
            storeNameTv.setText("")
            productNameTv.setText("")
            priceTv.setText("")
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
            storeNameSp.selectedItem == "Select Store" -> {
                Utils.errorToast(requireActivity(), "Store Name must not be empty")
                false
            }
            productNameSp.selectedItem == "Select Product" -> {
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


    private fun setData() {
        storeList.clear()
        val product1 = arrayListOf(
            Product("Rhodes Yeast Dinner Rolls 36 Count", "$4.49"),
            Product("Signature SELECT Drinking Water - 24-16.9 Fl", "$3.50"),
            Product("Coca-Cola Soda Pop Classic", "$5.6"),
            Product("Lobster & Crab", "$15.6")
        )
        storeList.add(Store("Albertons", product1))
        val product2= arrayListOf(
            Product("Dinner Rolls", "$1.49"),
            Product("Bread", "$2"),
            Product("Vanila Cupcakes", "$5")
        )
        storeList.add(Store("Safeway", product2))
        val product3= arrayListOf(
            Product("LEGO TOY", "$20.1"),
            Product("Lobster & Crab", "$10.6"),
            Product("Vanila Cupcakes", "$8")
        )
        storeList.add(Store("Target", product3))
        val product4= arrayListOf(
            Product("LEGO TOY", "$20.1"),
            Product("Warmies Elephant Heatable Stuffed Animal", "$29.1")
        )
        storeList.add(Store("Walmart", product4))
        val product5 = arrayListOf(
            Product("Mazola Corn Oil", "$5"),
            Product("Dave's Killer Bread", "$6"),
            Product("Sevan Cupcakes 28 oz", "$10")
        )
        storeList.add(Store("Bashas", product5))

        val product6 = arrayListOf(
            Product("Mazola Corn Oil", "$5"),
            Product("Dave's Killer Bread", "$6"),
            Product("Sevan Cupcakes 28 oz", "$10")
        )
        storeList.add(Store("Giant Eagle", product6))

        val product7 = arrayListOf(
            Product("Mazola Corn Oil", "$5"),
            Product("Dave's Killer Bread", "$6"),
            Product("Sevan Cupcakes 28 oz", "$10")
        )
        storeList.add(Store("Aldi", product7))

        val product8 = arrayListOf(
            Product("Mazola Corn Oil", "$5"),
            Product("Dave's Killer Bread", "$6"),
            Product("Sevan Cupcakes 28 oz", "$10")
        )
        storeList.add(Store("Whole Foods", product8))

        val product9 = arrayListOf(
            Product("Mazola Corn Oil", "$5"),
            Product("Dave's Killer Bread", "$6"),
            Product("Sevan Cupcakes 28 oz", "$10")
        )
        storeList.add(Store("Trader Joes", product9))
    }

    private fun setupSpinner() {

        //setup storeNAme
        val store = ArrayList<String>()
        store.clear()
        store.add("Select Store")
        storeList.mapIndexed { index, stores ->
            store.add(stores.storeName.toString())
        }
        val dataAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireActivity(), R.layout.simple_spinner_item, store)

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.storeNameSp.adapter = dataAdapter


        binding.storeNameSp.onItemSelectedListener = object :
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


    private fun setupProductSp(storeName: String) {
        //setup product Name
        val productList = ArrayList<Product>()
        val product = ArrayList<String>()
        productList.clear()
        product.clear()
        product.add("Select Product")

        storeList.mapIndexed { index, store ->
            if (storeName == store.storeName) {
                productList.addAll(store.productName)
                store.productName.mapIndexed { index, products ->
                    product.add(products.name.toString())
                }
            }
        }

        val dataAdapter1: ArrayAdapter<String> =
            ArrayAdapter<String>(requireActivity(), R.layout.simple_spinner_item, product)

        dataAdapter1.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.productNameSp.adapter = dataAdapter1


        binding.productNameSp.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>?,
                    selectedItemView: View?,
                    position: Int,
                    id: Long
                ) {
                    productList.mapIndexed { index, products ->
                        if (products.name == product[position]) {
                            binding.priceTv.setText(products.price)
                        }
                    }
                }

                override fun onNothingSelected(parentView: AdapterView<*>?) {}
            }
    }

}