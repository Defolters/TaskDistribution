package io.github.defolters.taskdistribution.presentation.login

import io.github.defolters.taskdistribution.data.remote.model.UserType
import io.github.defolters.taskdistribution.presentation.login.model.LoginModel

interface LoginContract {

    interface View {
        fun navigateAsSeller()
        fun navigateAsWorker()
        fun setLoginModel(loginModel: LoginModel)
        fun showToast(string: String)
    }

    interface Presenter {
        fun login(loginModel: LoginModel)
        fun handleUserType(userType: UserType?)
    }
}