package com.smartshopper.smart_shopper.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.smartshopper.smart_shopper.R
import com.smartshopper.smart_shopper.databinding.FragmentCreateProfileBinding
import com.smartshopper.smart_shopper.ui.activity.MainActivity
import com.smartshopper.smart_shopper.utils.SingleTon
import kotlinx.coroutines.flow.combine

class CreateProfileFragment : Fragment() {
lateinit var binding : FragmentCreateProfileBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateProfileBinding.inflate(inflater,container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }
    private fun initListener(){
        binding.submitBtn.setOnClickListener {
            (activity as MainActivity).showName()
            SingleTon.isSetupAcc = true
            findNavController().navigate(R.id.action_createProfileFragment_to_productFragment)
        }
    }

}