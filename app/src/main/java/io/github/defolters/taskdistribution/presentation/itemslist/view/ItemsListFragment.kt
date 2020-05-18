package io.github.defolters.taskdistribution.presentation.itemslist.view


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.data.remote.model.ItemTemplate
import io.github.defolters.taskdistribution.presentation.SharedViewModel
import io.github.defolters.taskdistribution.presentation.additem.view.AddItemFragment
import io.github.defolters.taskdistribution.presentation.itemslist.ItemsListContract
import io.github.defolters.taskdistribution.presentation.itemslist.presenter.ItemsListPresenter
import kotlinx.android.synthetic.main.fragment_items_list.*

/**
 * A simple [Fragment] subclass.
 */
class ItemsListFragment : Fragment(), ItemsListContract.View {

    //    private lateinit var binding: FragmentItemsListBinding
    private lateinit var presenter: ItemsListContract.Presenter
    private lateinit var adapter: ItemTemplateAdapter
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
        return inflater.inflate(R.layout.fragment_items_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = ItemsListPresenter(this)

        adapter = ItemTemplateAdapter()

        val llm = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvItems.layoutManager = llm
        rvItems.adapter = adapter

        adapter.onItemClick = {
            toAddItem(it)
        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                adapter.filter.filter(p0)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        presenter.getItemTemplates()
    }

    override fun showItemTemplates(items: List<ItemTemplate>) {
        adapter.dataset = items.toMutableList()
        adapter.datasetFiltered = items.toMutableList()
    }

    override fun toAddItem(itemTemplate: ItemTemplate) {
        val bundle = Bundle().apply {
            putInt("ITEM_TEMPLATE_ID", itemTemplate.id)
        }
        AddItemFragment.newInstance(bundle).show(fragmentManager!!, "")
//        navControl()?.navigate(R.id.action_itemDetailFragment_to_addItemFragment, bundle)
    }
}
