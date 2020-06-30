package com.hisham.ctintegrationsample.searchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hisham.ctintegrationsample.searchlist.data.SearchListItem

abstract class AbsSearchViewModel<T : SearchListItem>(private val absSearchUseCase: AbsSearchUseCase<T>): ViewModel() {

    private val mutableLiveData = MutableLiveData<List<T>>()
    val liveData: LiveData<List<T>> = mutableLiveData

    fun fetchList() {
        mutableLiveData.postValue(absSearchUseCase.fetch())
    }

    fun update(value: T) {
        absSearchUseCase.updateValue(value)
    }
}