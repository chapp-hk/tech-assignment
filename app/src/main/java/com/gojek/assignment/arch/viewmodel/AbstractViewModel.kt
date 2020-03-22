package com.gojek.assignment.arch.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class AbstractViewModel<A : Any> : ViewModel() {

    private val _action = MutableLiveData<A>()
    val action: LiveData<A> = _action

    protected val compositeDisposable = CompositeDisposable()

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    protected fun setAction(action: A) {
        _action.postValue(action)
    }
}