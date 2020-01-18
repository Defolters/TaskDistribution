package io.github.defolters.taskdistribution.presentation.addorder.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.databinding.FragmentAddOrderBinding
import io.github.defolters.taskdistribution.presentation.addorder.AddOrderContract
import io.github.defolters.taskdistribution.presentation.addorder.model.ItemModel
import io.github.defolters.taskdistribution.presentation.addorder.presenter.AddOrderPresenter
import io.github.defolters.taskdistribution.util.navControl
import kotlinx.android.synthetic.main.fragment_add_order.*

/**
 * A simple [Fragment] subclass.
 */
class AddOrderFragment : Fragment(), AddOrderContract.View {

    private lateinit var binding: FragmentAddOrderBinding
    private lateinit var presenter: AddOrderContract.Presenter
    private lateinit var adapter: ItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_order,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = AddOrderPresenter(this)

        cvAddItem.setOnClickListener {
            navigateToAddItem()
        }

        adapter = ItemsAdapter()

        val llm = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvItems.layoutManager = llm
        rvItems.adapter = adapter

        adapter.onItemClick = {
            //navigateToOrder()
        }

        adapter.dataset = mutableListOf(ItemModel(""), ItemModel(""), ItemModel(""))

//        presenter.get()
    }

    override fun navigateToAddItem() {
        navControl()?.navigate(R.id.action_addOrderFragment_to_itemsListFragment)
    }
}
