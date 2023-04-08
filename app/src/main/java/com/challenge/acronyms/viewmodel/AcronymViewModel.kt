package com.challenge.acronyms.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.acronyms.model.AcronymResponseItem
import com.challenge.acronyms.model.Lf
import com.challenge.acronyms.model.Var
import com.challenge.acronyms.rest.AcronymRepository
import com.challenge.acronyms.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AcronymViewModel @Inject constructor(
    private val acronymRepository: AcronymRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    private val _meaning: MutableLiveData<UIState<List<AcronymResponseItem>>> = MutableLiveData(UIState.LOADING)
    val meaning: MutableLiveData<UIState<List<AcronymResponseItem>>> get() = _meaning

    fun getMeaning(acronym: String) {
        viewModelScope.launch(ioDispatcher) {
            acronymRepository.getMeaning(acronym).collect() {
                _meaning.postValue(it)
            }
        }
    }

}