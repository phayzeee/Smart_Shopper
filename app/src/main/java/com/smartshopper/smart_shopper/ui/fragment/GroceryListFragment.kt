package com.smartshopper.smart_shopper.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smartshopper.smart_shopper.databinding.FragmentGroceryListBinding

class GroceryListFragment: Fragment() {

    lateinit var binding : FragmentGroceryListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGroceryListBinding.inflate(inflater,container, false)
        return binding.root
    }

    fun initListener(){

    }
}