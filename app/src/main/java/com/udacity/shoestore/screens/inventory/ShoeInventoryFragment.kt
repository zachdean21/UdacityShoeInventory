package com.udacity.shoestore.screens.inventory

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.DataBinderMapperImpl
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailBinding
import com.udacity.shoestore.databinding.ShoeInventoryFragmentBinding
import com.udacity.shoestore.models.Shoe

class ShoeInventoryFragment :  Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding : ShoeInventoryFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.shoe_inventory_fragment, container, false)

        setHasOptionsMenu(true)

        val shoeInventoryViewModel: ShoeInventoryViewModel by activityViewModels()

        binding.shoeDetailAddButton.setOnClickListener {
            findNavController().navigate(ShoeInventoryFragmentDirections.actionShoeInventoryFragmentToShoeDetailFragment())
        }

        shoeInventoryViewModel.shoes.observe(viewLifecycleOwner, Observer { shoes ->

            binding.inventoryLinearLayout.removeAllViews()

            shoes.forEach { shoe ->
                displayShoe(inflater, binding, shoe)
            }
        })

        return binding.root

    }

    private fun displayShoe(inflater: LayoutInflater, binding: ShoeInventoryFragmentBinding, shoe: Shoe) {
        val shoeDetailBinding: ShoeDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.shoe_detail, binding.inventoryLinearLayout, false
        )

        shoeDetailBinding.shoeName.text = shoe.name
        shoeDetailBinding.shoeSize.text = shoe.size.toString()
        shoeDetailBinding.shoeCompany.text = shoe.company
        shoeDetailBinding.shoeDescription.text = shoe.description

        binding.inventoryLinearLayout.addView(shoeDetailBinding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shoe_inventory_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.shoe_inventory_menu_logout -> findNavController().navigate(ShoeInventoryFragmentDirections.actionShoeInventoryFragmentToLoginFragment())
        }
        return super.onOptionsItemSelected(item)
    }
}