package com.hisham.ctintegrationsample.searchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem

abstract class AbsSearchViewModel(private val absSearchUseCase: AbsSearchUseCase): ViewModel() {

    private val mutableLiveData = MutableLiveData<List<SearchListItem>>()
    val liveData: LiveData<List<SearchListItem>> = mutableLiveData

    fun fetchList() {
        mutableLiveData.postValue(absSearchUseCase.fetch())
    }

    fun update(value: SearchListItem) {
        absSearchUseCase.updateValue(value)
    }
}