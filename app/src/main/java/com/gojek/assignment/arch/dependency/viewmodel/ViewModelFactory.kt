package com.gojek.assignment.arch.dependency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject
constructor(private val creators: MutableMap<Class<out ViewModel>, Provider<ViewModel>>)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return getCreator(modelClass).get() as? T
            ?: throw createException(modelClass)
    }

    private fun <T : ViewModel> getCreator(modelClass: Class<T>): Provider<ViewModel> {
        return creators[modelClass]
            ?: creators.entries.firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
            ?: throw createException(modelClass)
    }

    private fun <T : ViewModel> createException(modelClass: Class<T>): IllegalArgumentException {
        return IllegalArgumentException("unknown model class $modelClass")
    }
}