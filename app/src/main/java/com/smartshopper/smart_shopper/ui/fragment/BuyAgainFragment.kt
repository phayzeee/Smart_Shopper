package com.smartshopper.smart_shopper.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smartshopper.smart_shopper.adapter.DealAdapter
import com.smartshopper.smart_shopper.adapter.PreviousGroceryAdapter
import com.smartshopper.smart_shopper.databinding.FragmentBuyAgainBinding
import com.smartshopper.smart_shopper.model.GroceryDate

class BuyAgainFragment: Fragment() {

    lateinit var binding : FragmentBuyAgainBinding
    lateinit var dateAdapter: PreviousGroceryAdapter
    var dateList = ArrayList<GroceryDate>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuyAgainBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        setupAdapter()
        setData()
    }

    private fun initListener() = binding.run{
        addGroceryBtn.setOnClickListener {
            llBuyAgain.visibility = View.GONE
            llPreviousList.visibility = View.VISIBLE
        }
    }

    private fun setupAdapter() {
        dateAdapter = PreviousGroceryAdapter(requireActivity())
        binding.rvPreviousGrocery.adapter = dateAdapter
    }

    private fun setData(){
        dateList.clear()
        dateList.add(GroceryDate("8 Aug 2022"))
        dateList.add(GroceryDate("16 Aug 2022"))
        dateList.add(GroceryDate("24 Aug 2022"))
        dateList.add(GroceryDate("8 June 2022"))
        dateList.add(GroceryDate("8 May 2022"))
        dateList.add(GroceryDate("8 Aug 2022"))
        dateList.add(GroceryDate("8 Aug 2022"))
    }

}