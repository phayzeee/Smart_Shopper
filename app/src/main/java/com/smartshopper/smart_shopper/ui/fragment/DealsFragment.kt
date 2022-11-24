package com.smartshopper.smart_shopper.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartshopper.smart_shopper.R
import com.smartshopper.smart_shopper.databinding.FragmentDealsBinding

class DealsFragment : Fragment() {
    lateinit var binding : FragmentDealsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDealsBinding.inflate(inflater,container,false)
        return binding.root
    }

}