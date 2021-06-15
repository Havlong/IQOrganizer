package ru.pnzgu.iqorganizer.ui.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GroupViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Группа"
    }
    val text: LiveData<String> = _text
}