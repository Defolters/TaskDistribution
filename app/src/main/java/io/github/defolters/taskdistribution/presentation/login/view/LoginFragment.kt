package io.github.defolters.taskdistribution.presentation.login.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.data.remote.model.UserType
import io.github.defolters.taskdistribution.databinding.FragmentLoginBinding
import io.github.defolters.taskdistribution.presentation.login.LoginContract
import io.github.defolters.taskdistribution.presentation.login.model.LoginModel
import io.github.defolters.taskdistribution.presentation.login.presenter.LoginPresenter
import io.github.defolters.taskdistribution.util.hideKeyboard
import io.github.defolters.taskdistribution.util.navControl
import io.paperdb.Paper
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment(), LoginContract.View {

    private lateinit var presenter: LoginContract.Presenter
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = LoginPresenter(this)

        login_button.setOnClickListener {
            binding.loginModel?.let { loginModel ->
                it.hideKeyboard()
                if (loginModel.isEnabled) presenter.login(loginModel)
            }

        }
        setLoginModel(LoginModel("", ""))

        val token = Paper.book().read<String>("TOKEN")
        val userType = Paper.book().read<UserType>("USER_TYPE")
        if (token != null) presenter.handleUserType(userType)
    }

    override fun navigateAsSeller() {
        navControl()?.navigate(R.id.action_loginFragment_to_ordersListFragment)
    }

    override fun navigateAsWorker() {
        navControl()?.navigate(R.id.action_loginFragment_to_tasksListFragment)
    }

    override fun setLoginModel(loginModel: LoginModel) {
        binding.loginModel = loginModel
    }

    override fun showToast(string: String) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }
}
