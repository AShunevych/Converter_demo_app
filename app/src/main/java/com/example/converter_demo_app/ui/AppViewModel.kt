package com.example.converter_demo_app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {
    private val selected = MutableLiveData<Int>()
    private val postNumber = MutableLiveData<String?>()
    fun setPostNumber(textNumber: String?) {
        postNumber.value = textNumber
    }

    val postedNumber: LiveData<String?>
        get() = postNumber

    fun select(pos: Int) {
        selected.value = pos
    }

    fun getSelected(): LiveData<Int> {
        return selected
    }
}
