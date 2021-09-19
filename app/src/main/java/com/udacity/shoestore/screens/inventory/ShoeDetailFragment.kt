package com.udacity.shoestore.screens.inventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailBinding
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding : ShoeDetailFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.shoe_detail_fragment, container, false)

        val shoeInventoryViewModel: ShoeInventoryViewModel by activityViewModels()

        binding.shoeDetailCancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.newShoe = Shoe("", 0.0, "", "")

        binding.shoeInventoryViewModel = shoeInventoryViewModel

        shoeInventoryViewModel.saveComplete.observe(viewLifecycleOwner, Observer { saveComplete ->
            if(saveComplete) {
                shoeInventoryViewModel.onSaveCompleteHandled()
                findNavController().navigateUp()
            }
        })

        return binding.root

    }
}