package com.udacity.shoestore.screens.inventory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeInventoryViewModel: ViewModel() {

    // Shoe Inventory List
    private val _shoes = MutableLiveData<MutableList<Shoe>>()

    val shoes : LiveData<MutableList<Shoe>>
        get() = _shoes

    // Save Complete
    private val _saveComplete = MutableLiveData<Boolean>()

    val saveComplete : LiveData<Boolean>
        get() = _saveComplete

    init {
        _shoes.value = mutableListOf<Shoe>()
        _saveComplete.value = false
    }

    fun addShoeToInventory(newShoe: Shoe) {
        _shoes.value?.add(newShoe)
        _shoes.value = _shoes.value

        _saveComplete.value = true
    }

    fun onSaveCompleteHandled() {
        _saveComplete.value = false
    }

}