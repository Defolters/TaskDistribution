package io.github.defolters.taskdistribution.presentation.login.presenter

import io.github.defolters.taskdistribution.domain.login.ILoginInteractor
import io.github.defolters.taskdistribution.domain.login.LoginInteractor
import io.github.defolters.taskdistribution.presentation.login.LoginContract
import io.github.defolters.taskdistribution.presentation.login.model.LoginModel

class LoginPresenter(val loginView: LoginContract.View) : LoginContract.Presenter {


    private var loginInteractor: ILoginInteractor = LoginInteractor()

    override fun login(loginModel: LoginModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}