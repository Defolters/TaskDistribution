package io.github.defolters.taskdistribution.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.defolters.taskdistribution.data.remote.model.ItemJSON

class SharedViewModel : ViewModel() {

    val itemsData: MutableLiveData<List<ItemJSON>> by lazy {
        MutableLiveData<List<ItemJSON>>()
    }

}