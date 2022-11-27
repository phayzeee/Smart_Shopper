package com.smartshopper.smart_shopper.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.smartshopper.smart_shopper.R
import com.smartshopper.smart_shopper.database.AppDatabase
import com.smartshopper.smart_shopper.database.UserEntities
import com.smartshopper.smart_shopper.databinding.FragmentCreateProfileBinding
import com.smartshopper.smart_shopper.ui.activity.MainActivity
import com.smartshopper.smart_shopper.utils.Constant
import com.smartshopper.smart_shopper.utils.SingleTon
import com.smartshopper.smart_shopper.utils.Utils
import kotlinx.coroutines.flow.combine

class CreateProfileFragment : Fragment() {
    lateinit var binding: FragmentCreateProfileBinding
    private lateinit var navController: NavController
    lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDb()
        initListener()
    }

    private fun setupDb() {
        db = Room.databaseBuilder(
            requireActivity(),
            AppDatabase::class.java, Constant.GROCERY
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()
    }

    private fun initListener() = binding.run {
        binding.submitBtn.setOnClickListener {
            if (validation()) {
                SingleTon.isSetupAcc = true
                db.Dao().adduser(
                    UserEntities(
                        1,
                        name = nameTv.text.toString(),
                        state = stateTv.text.toString(),
                        code = codeTv.text.toString()
                    )
                ).apply {
                    findNavController().navigate(R.id.action_createProfileFragment_to_productFragment)
                    (activity as MainActivity).showName()
                }
            }
        }
    }

    private fun validation(): Boolean = binding.run {
        return when {
            nameTv.text.isEmpty() -> {
                Utils.errorToast(requireActivity(), "Name must not be empty")
                false
            }
            stateTv.text.isEmpty() -> {
                Utils.errorToast(requireActivity(), "State must not be empty")
                false
            }
            codeTv.text.isEmpty() -> {
                Utils.errorToast(requireActivity(), "Code must not be empty")
                false
            }
            else -> {
                true
            }
        }
    }

}