package io.github.defolters.taskdistribution.domain.login

import android.util.Log
import io.github.defolters.taskdistribution.data.remote.Api
import io.github.defolters.taskdistribution.data.remote.model.UserJsonModel
import io.github.defolters.taskdistribution.data.remote.model.UserType
import io.github.defolters.taskdistribution.presentation.login.model.LoginModel
import retrofit2.HttpException
import retrofit2.await

class LoginInteractor : ILoginInteractor {

    override suspend fun login(loginModel: LoginModel): UserType? {
        return try {
            val loginJsonModel = Api.apiService.login(
                UserJsonModel(
                    loginModel.username,
                    loginModel.password
                )
            ).await()
            loginJsonModel.userType
        } catch (ex: HttpException) {
            Log.d("Login Presenter", "error ${ex.toString()}")
            null
        }
    }
}