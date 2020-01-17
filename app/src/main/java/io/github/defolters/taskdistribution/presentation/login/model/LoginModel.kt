package io.github.defolters.taskdistribution.presentation.login.model

data class LoginModel(var username: String, var password: String) {
    val isEnabled: Boolean
        get() = username.isNotBlank() && password.isNotBlank()
}