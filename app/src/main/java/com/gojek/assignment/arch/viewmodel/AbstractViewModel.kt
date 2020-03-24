package com.gojek.assignment.arch.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class AbstractViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}