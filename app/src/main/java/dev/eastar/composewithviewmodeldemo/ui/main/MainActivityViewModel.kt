package dev.eastar.composewithviewmodeldemo.ui.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.eastar.composewithviewmodeldemo.domain.usecase.SayHelloUseCase
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val sayHelloUseCase: SayHelloUseCase
) : ViewModel() {
    fun sayHello() = sayHelloUseCase()
}

