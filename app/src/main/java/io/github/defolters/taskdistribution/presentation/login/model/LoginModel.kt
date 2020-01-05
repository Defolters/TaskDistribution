package io.github.defolters.taskdistribution.presentation.login.model

data class LoginModel(var username: String, var password: String, var isEnabled: Boolean = false)