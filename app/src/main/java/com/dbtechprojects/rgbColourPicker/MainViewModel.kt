package com.dbtechprojects.rgbColourPicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dbtechprojects.rgbColourPicker.models.RGBModel

class MainViewModel: ViewModel() {
    //Live data to be observed and reacted to in MainActivity
    private val _RGBvalue = MutableLiveData<RGBModel>()
    val RGBvalue: LiveData<RGBModel>
        get() = _RGBvalue


    // RGB Value Setter
    fun setRGBValue(value: RGBModel){_RGBvalue.postValue(value)}

}