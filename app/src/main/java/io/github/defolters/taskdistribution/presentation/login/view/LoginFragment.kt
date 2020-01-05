package io.github.defolters.taskdistribution.presentation.login.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.databinding.FragmentLoginBinding
import io.github.defolters.taskdistribution.presentation.login.LoginContract
import io.github.defolters.taskdistribution.presentation.login.model.LoginModel
import io.github.defolters.taskdistribution.presentation.login.presenter.LoginPresenter
import io.github.defolters.taskdistribution.util.navControl
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

        }
    }

    override fun navigateAsSeller() {
        navControl()?.navigate(R.id.ordersListFragment)
    }

    override fun navigateAsWorker() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLoginModel(loginModel: LoginModel) {
        binding.loginModel = loginModel
    }
}
