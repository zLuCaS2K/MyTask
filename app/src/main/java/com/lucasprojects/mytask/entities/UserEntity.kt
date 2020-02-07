package com.lucasprojects.mytask.entities

/** Classe entidade de usuário */
data class UserEntity(val id: Int, var name: String, var email: String, var password: String = "")