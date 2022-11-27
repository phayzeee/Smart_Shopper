package com.smartshopper.smart_shopper.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.smartshopper.smart_shopper.adapter.DealAdapter
import com.smartshopper.smart_shopper.adapter.ProductAdapter
import com.smartshopper.smart_shopper.database.AppDatabase
import com.smartshopper.smart_shopper.database.DealsEntities
import com.smartshopper.smart_shopper.database.ProductEntities
import com.smartshopper.smart_shopper.databinding.FragmentAllProductBinding
import com.smartshopper.smart_shopper.model.Deals
import com.smartshopper.smart_shopper.model.products
import com.smartshopper.smart_shopper.utils.Constant

class AllProductFragment : Fragment() {

    lateinit var binding: FragmentAllProductBinding
    private lateinit var productAdapter: ProductAdapter
    lateinit var dealAdapter: DealAdapter
    var product = ArrayList<products>()
    var deals = ArrayList<Deals>()
    lateinit var db: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDb()
        initListener()
        setData()
    }

    private fun setupDb() {
        db = Room.databaseBuilder(
            requireActivity(),
            AppDatabase::class.java, Constant.GROCERY
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()
    }

    private fun initListener() = binding.run {
        storedProductBtn.setOnClickListener {
            productAdapter = ProductAdapter(requireActivity())
            productAdapter.updateData(db.Dao().getProducts() as ArrayList<ProductEntities>)
            rv.adapter = productAdapter
        }
        storedDealsBtn.setOnClickListener {
            dealAdapter = DealAdapter(requireActivity())
            dealAdapter.updateData(db.Dao().getDeals() as ArrayList<DealsEntities>)
            rv.adapter = dealAdapter
        }
    }

    private fun setData() {
        product.clear()
        product.add(products("Imtiaz", "Polo Shirts", "1200"))
        product.add(products("Chase", "Niki Shoes", "10000"))

        deals.clear()
        deals.add(Deals("Imtiaz", "Polo Shirts", "2 shirts in 1500", "1 Dec - 10 Dec"))
        deals.add(Deals("Chase", "Niki Shoes", "3 shoes in 15000", "1 Dec - 10 Dec"))
    }
}