package com.noble.activity.artifactcards.deck

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.noble.activity.artifactcards.ArtifactRepository

class ArtifactDeckViewModelFactory(
    private val repository: ArtifactRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = ArtifactDeckViewModel(
        repository
    ) as T
}