package io.github.defolters.taskdistribution.presentation.login.presenter

import android.os.Handler
import android.os.Looper
import android.util.Log
import io.github.defolters.taskdistribution.data.remote.model.UserType
import io.github.defolters.taskdistribution.domain.login.ILoginInteractor
import io.github.defolters.taskdistribution.domain.login.LoginInteractor
import io.github.defolters.taskdistribution.presentation.login.LoginContract
import io.github.defolters.taskdistribution.presentation.login.model.LoginModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginPresenter(val loginView: LoginContract.View) : LoginContract.Presenter {


    private var loginInteractor: ILoginInteractor = LoginInteractor()

    override fun login(loginModel: LoginModel) {
        Log.d("LoginPresenter", "login ")

        GlobalScope.launch {
            val userType = loginInteractor.login(loginModel)
            loginView.navigateAsSeller()
            when (userType) {
                UserType.SELLER -> {
                }
                UserType.WORKER -> {
                }
                else -> {
                    Handler(Looper.getMainLooper()).post {
                        //code that runs in main
                        loginView.showToast("error login")
                    }
                }
            }
        }
    }
}