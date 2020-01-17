package io.github.defolters.taskdistribution.presentation.login.presenter

import android.util.Log
import io.github.defolters.taskdistribution.domain.login.ILoginInteractor
import io.github.defolters.taskdistribution.domain.login.LoginInteractor
import io.github.defolters.taskdistribution.presentation.login.LoginContract
import io.github.defolters.taskdistribution.presentation.login.model.LoginModel

class LoginPresenter(val loginView: LoginContract.View) : LoginContract.Presenter {


    private var loginInteractor: ILoginInteractor = LoginInteractor()

    override fun login(loginModel: LoginModel) {
        Log.d("LoginPresenter", "login ")

        if (loginModel.username.contains("1")) {
            loginView.navigateAsSeller()
        } else {
            loginView.navigateAsWorker()
        }

    }
}