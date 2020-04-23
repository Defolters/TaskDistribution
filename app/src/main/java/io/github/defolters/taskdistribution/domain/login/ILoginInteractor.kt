package io.github.defolters.taskdistribution.domain.login

import io.github.defolters.taskdistribution.presentation.login.model.LoginModel

interface ILoginInteractor {
    fun login(loginModel: LoginModel)
}