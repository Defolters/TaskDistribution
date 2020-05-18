package io.github.defolters.taskdistribution.presentation.orderslist.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.data.remote.model.Order
import io.github.defolters.taskdistribution.databinding.FragmentOrdersListBinding
import io.github.defolters.taskdistribution.presentation.EdgeDecorator
import io.github.defolters.taskdistribution.presentation.SharedViewModel
import io.github.defolters.taskdistribution.presentation.orderslist.OrdersListContract
import io.github.defolters.taskdistribution.presentation.orderslist.presenter.OrdersListPresenter
import io.github.defolters.taskdistribution.util.navControl
import kotlinx.android.synthetic.main.fragment_orders_list.*

/**
 * A simple [Fragment] subclass.
 */
class OrdersListFragment : Fragment(), OrdersListContract.View {

    private lateinit var presenter: OrdersListContract.Presenter
    private lateinit var binding: FragmentOrdersListBinding
    private lateinit var adapter: OrdersAdapter
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.run {
            sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)
        }
    }

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

        cvAddOrder.setOnClickListener {
            sharedViewModel.itemsData.value = null
            navigateToAddOrder()
        }

        adapter = OrdersAdapter()

        val llm = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvOrders.layoutManager = llm
        rvOrders.adapter = adapter
        rvOrders.addItemDecoration(EdgeDecorator(0, 400))

        adapter.onItemClick = {
            navigateToOrder(it)
        }

        presenter.getOrders()

        tvLogout.setOnClickListener {
            presenter.logout()
        }
    }

    override fun showOrders(orders: List<Order>) {
        adapter.dataset = orders.toMutableList()
    }

    override fun navigateToAddOrder() {
        navControl()?.navigate(R.id.action_ordersListFragment_to_addOrderFragment)
    }

    override fun navigateToOrder(order: Order) {
        val bundle = Bundle().apply {
            putParcelable("ORDER", order)
        }
        navControl()?.navigate(R.id.action_ordersListFragment_to_orderDetailFragment, bundle)
    }

    override fun navigateToLogin() {
        navControl()?.navigate(R.id.action_ordersListFragment_to_loginFragment)
    }
}
