package io.github.defolters.taskdistribution.presentation.orderdetail.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.data.remote.model.Item
import io.github.defolters.taskdistribution.data.remote.model.Order
import io.github.defolters.taskdistribution.databinding.FragmentOrderDetailBinding
import io.github.defolters.taskdistribution.presentation.orderdetail.OrderDetailContract
import io.github.defolters.taskdistribution.presentation.orderdetail.presenter.OrderDetailPresenter
import io.github.defolters.taskdistribution.util.navControl
import kotlinx.android.synthetic.main.fragment_order_detail.*

/**
 * A simple [Fragment] subclass.
 */
class OrderDetailFragment : Fragment(), OrderDetailContract.View {

    private lateinit var binding: FragmentOrderDetailBinding
    private lateinit var presenter: OrderDetailContract.Presenter
    private lateinit var adapter: ItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_order_detail,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = OrderDetailPresenter(this)

        arguments?.getParcelable<Order>("ORDER")?.let { order ->
            showOrder(order)
            presenter.getItems(order.id)
        }

        adapter =
            ItemsAdapter()

        val llm = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvItems.layoutManager = llm
        rvItems.adapter = adapter

        adapter.onItemClick = {
            navigateToItem(it)
        }

    }

    override fun showOrder(order: Order) {
        binding.order = order
    }

    override fun showItems(items: List<Item>) {
        adapter.dataset = items.toMutableList()
    }

    override fun navigateToItem(item: Item) {
        val bundle = Bundle().apply {
            putParcelable("ITEM", item)
        }
        navControl()?.navigate(R.id.action_orderDetailFragment_to_itemDetailFragment, bundle)
    }
}
