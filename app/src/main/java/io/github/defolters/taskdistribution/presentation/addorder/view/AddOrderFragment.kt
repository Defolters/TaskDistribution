package io.github.defolters.taskdistribution.presentation.addorder.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.data.remote.model.OrderJSON
import io.github.defolters.taskdistribution.databinding.FragmentAddOrderBinding
import io.github.defolters.taskdistribution.presentation.SharedViewModel
import io.github.defolters.taskdistribution.presentation.addorder.AddOrderContract
import io.github.defolters.taskdistribution.presentation.addorder.presenter.AddOrderPresenter
import io.github.defolters.taskdistribution.util.navControl
import kotlinx.android.synthetic.main.fragment_add_order.*

/**
 * A simple [Fragment] subclass.
 */
class AddOrderFragment : Fragment(), AddOrderContract.View {

    private lateinit var binding: FragmentAddOrderBinding
    private lateinit var presenter: AddOrderContract.Presenter
    private lateinit var adapter: ItemJsonTemplateAdapter
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

        adapter = ItemJsonTemplateAdapter()

        val llm = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvItems.layoutManager = llm
        rvItems.adapter = adapter

        adapter.onItemDelete = {
            //navigateToOrder()
            val newList = sharedViewModel.itemsData.value!!.toMutableList()
            newList.remove(it)
            sharedViewModel.itemsData.value = newList

        }

        ivDone.setOnClickListener {
            val name = etCustomerName.text.toString()
            val email = etCustomerEmail.text.toString()
            val items = sharedViewModel.itemsData.value

            if (name.isNotBlank() && email.isNotBlank() && items?.isNotEmpty() == true) {
                presenter.createOrder(
                    OrderJSON(
                        0,
                        name,
                        email,
                        items,
                        null
                    )
                )
            }
        }

        sharedViewModel.itemsData.observe(this, Observer { items ->
            items?.let {
                adapter.dataset = items.toMutableList()
                tvPrice.text = "Стоимость: ${items.sumByDouble { it.price }} P"
            }
        })
    }

    override fun navigateToAddItem() {
        navControl()?.navigate(R.id.action_addOrderFragment_to_itemsListFragment)
    }

    override fun onAddOrder() {
        navControl()?.popBackStack()
    }
}
