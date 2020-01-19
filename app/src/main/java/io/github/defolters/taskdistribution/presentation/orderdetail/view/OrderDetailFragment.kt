package io.github.defolters.taskdistribution.presentation.orderdetail.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.presentation.addorder.model.ItemModel
import io.github.defolters.taskdistribution.presentation.addorder.view.ItemsAdapter
import io.github.defolters.taskdistribution.presentation.orderdetail.OrderDetailContract
import io.github.defolters.taskdistribution.presentation.orderdetail.presenter.OrderDetailPresenter
import io.github.defolters.taskdistribution.presentation.orderslist.model.OrderModel
import kotlinx.android.synthetic.main.fragment_order_detail.*

/**
 * A simple [Fragment] subclass.
 */
class OrderDetailFragment : Fragment(), OrderDetailContract.View {

    //    private lateinit var binding: FragmentAddOrderBinding
    private lateinit var presenter: OrderDetailContract.Presenter
    private lateinit var adapter: ItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = OrderDetailPresenter(this)

//        cvAddItem.setOnClickListener {
//            navigateToAddItem()
//        }

        adapter = ItemsAdapter()

        val llm = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvItems.layoutManager = llm
        rvItems.adapter = adapter

        adapter.onItemClick = {
            //navigateToOrder()
        }

        adapter.dataset = mutableListOf(
            ItemModel("Item", "Item", 150f, null),
            ItemModel("Item", "Item", 150f, null),
            ItemModel("Item", "Item", 150f, null)
        )
    }

    override fun showOrder(order: OrderModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
