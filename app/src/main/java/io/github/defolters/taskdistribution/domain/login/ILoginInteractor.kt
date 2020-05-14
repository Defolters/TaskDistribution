package io.github.defolters.taskdistribution.domain.login

import io.github.defolters.taskdistribution.data.remote.model.UserType
import io.github.defolters.taskdistribution.presentation.login.model.LoginModel

interface ILoginInteractor {
    suspend fun login(loginModel: LoginModel): UserType?
}