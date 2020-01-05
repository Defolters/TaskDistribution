package io.github.defolters.taskdistribution.presentation.orderslist.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.databinding.FragmentOrdersListBinding
import io.github.defolters.taskdistribution.presentation.orderslist.OrdersListContract
import io.github.defolters.taskdistribution.presentation.orderslist.presenter.OrdersListPresenter

/**
 * A simple [Fragment] subclass.
 */
class OrdersListFragment : Fragment(), OrdersListContract.View {

    private lateinit var presenter: OrdersListContract.Presenter
    private lateinit var binding: FragmentOrdersListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_orders_list,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = OrdersListPresenter(this)
    }
}
