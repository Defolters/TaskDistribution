package io.github.defolters.taskdistribution.presentation.itemslist.view


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
import io.github.defolters.taskdistribution.presentation.itemslist.ItemsListContract
import io.github.defolters.taskdistribution.presentation.itemslist.presenter.ItemsListPresenter
import kotlinx.android.synthetic.main.fragment_items_list.*

/**
 * A simple [Fragment] subclass.
 */
class ItemsListFragment : Fragment(), ItemsListContract.View {

    //    private lateinit var binding: FragmentItemsListBinding
    private lateinit var presenter: ItemsListContract.Presenter
    private lateinit var adapter: ItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_items_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = ItemsListPresenter(this)

//        cvAddItem.setOnClickListener {
//            navigateToAddItem()
//        }

        adapter = ItemsAdapter()

        val llm = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvItems.layoutManager = llm
        rvItems.adapter = adapter

        adapter.onItemClick = {
            //            fragmentManager?.popBackStack()
        }

        adapter.dataset = mutableListOf(
            ItemModel("Item", "Item", 150f, null),
            ItemModel("Item", "Item", 150f, null)
        )
    }
}
