package dev.eastar.composewithviewmodeldemo.domain.usecase

import dev.eastar.composewithviewmodeldemo.domain.repository.HelloRepository
import javax.inject.Inject

class SayHelloUseCase @Inject constructor(
    private val repository: HelloRepository
) {
    operator fun invoke(): String {
        return repository.getHello()
    }
}
