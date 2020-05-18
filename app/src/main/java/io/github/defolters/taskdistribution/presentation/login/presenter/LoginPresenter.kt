package io.github.defolters.taskdistribution.presentation.login.presenter

import android.os.Handler
import android.os.Looper
import io.github.defolters.taskdistribution.data.remote.model.UserType
import io.github.defolters.taskdistribution.domain.login.ILoginInteractor
import io.github.defolters.taskdistribution.domain.login.LoginInteractor
import io.github.defolters.taskdistribution.presentation.login.LoginContract
import io.github.defolters.taskdistribution.presentation.login.model.LoginModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginPresenter(private val loginView: LoginContract.View) : LoginContract.Presenter {


    private var loginInteractor: ILoginInteractor = LoginInteractor()

    override fun login(loginModel: LoginModel) {
        GlobalScope.launch {
            handleUserType(loginInteractor.login(loginModel))
        }
    }

    override fun handleUserType(userType: UserType?) {
        when (userType) {
            UserType.SELLER -> {
                loginView.navigateAsSeller()
            }
            UserType.WORKER -> {
                loginView.navigateAsWorker()
            }
            else -> {
                Handler(Looper.getMainLooper()).post {
                    loginView.showToast("error login")
                }
            }
        }
    }
}