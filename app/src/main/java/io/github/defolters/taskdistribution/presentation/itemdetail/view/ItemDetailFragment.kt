package io.github.defolters.taskdistribution.presentation.itemdetail.view


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
import io.github.defolters.taskdistribution.data.remote.model.Task
import io.github.defolters.taskdistribution.databinding.FragmentItemDetailBinding
import io.github.defolters.taskdistribution.presentation.itemdetail.ItemDetailContract
import io.github.defolters.taskdistribution.presentation.itemdetail.presenter.ItemDetailPresenter
import kotlinx.android.synthetic.main.fragment_order_detail.*

/**
 * A simple [Fragment] subclass.
 */
class ItemDetailFragment : Fragment(), ItemDetailContract.View {

    private lateinit var binding: FragmentItemDetailBinding
    private lateinit var presenter: ItemDetailPresenter
    private lateinit var adapter: TasksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_item_detail,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = ItemDetailPresenter(this)

        arguments?.getParcelable<Item>("ITEM")?.let { item ->
            showItem(item)
            presenter.getTasks(item.id)
        }

        adapter =
            TasksAdapter()

        val llm = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvItems.layoutManager = llm
        rvItems.adapter = adapter

        adapter.onItemClick = {
            //navigateToItemDetail()
        }

    }

    override fun showItem(item: Item) {
        binding.item = item
    }

    override fun showTasks(tasks: List<Task>) {
        adapter.dataset = tasks.toMutableList()
    }
}
