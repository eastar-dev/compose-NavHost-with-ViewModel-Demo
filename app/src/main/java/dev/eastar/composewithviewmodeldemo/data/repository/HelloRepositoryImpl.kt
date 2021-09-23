package dev.eastar.composewithviewmodeldemo.data.repository

import dev.eastar.composewithviewmodeldemo.domain.repository.HelloRepository

class HelloRepositoryImpl : HelloRepository {
    override fun getHello() = "Hello"
}
