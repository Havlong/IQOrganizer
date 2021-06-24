package ru.pnzgu.iqorganizer.ui.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GroupViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Групповые мероприятия находятся в разработке"
    }
    val text: LiveData<String> = _text
}