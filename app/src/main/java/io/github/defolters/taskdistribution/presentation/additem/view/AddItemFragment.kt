package io.github.defolters.taskdistribution.presentation.additem.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.data.remote.model.ItemJSON
import io.github.defolters.taskdistribution.data.remote.model.TaskTemplate
import io.github.defolters.taskdistribution.data.remote.model.TaskTemplateFilterJSON
import io.github.defolters.taskdistribution.presentation.SharedViewModel
import io.github.defolters.taskdistribution.presentation.additem.AddItemContract
import io.github.defolters.taskdistribution.presentation.additem.presenter.AddItemPresenter
import kotlinx.android.synthetic.main.fragment_add_item.*


class AddItemFragment : BottomSheetDialogFragment(), AddItemContract.View {

    private var adapter = TaskTemplatesAdapter()
    private lateinit var presenter: AddItemContract.Presenter
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
        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = AddItemPresenter(this)

        val id = arguments?.getInt("ITEM_TEMPLATE_ID")!!

        arguments?.getString("ITEM_TEMPLATE_TITLE")?.let { title ->
            tvTitle.text = title
        }

        ivClose.setOnClickListener {
            dismiss()
        }

        tvAdd.setOnClickListener {
            val info = etInfo.text.toString()
            val priceStr = etPrice.text.toString()
            val price = if (priceStr.isNotBlank()) priceStr.toDouble() else .0

            if (info.isNotBlank() && price > 0) {
                val list = sharedViewModel.itemsData.value?.toMutableList() ?: mutableListOf()
                list.add(
                    ItemJSON(
                        id,
                        info, price,
                        adapter.dataset.filter { it.isChecked }.map { it.taskTemplate.id }
                    )
                )
                sharedViewModel.itemsData.value = list
                Toast.makeText(context, "Предмет добавлен", Toast.LENGTH_SHORT).show();
                dismiss()
            }
        }

        val llm = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvTaskTemplates.layoutManager = llm
        rvTaskTemplates.adapter = adapter

        presenter.getTaskTemplates(TaskTemplateFilterJSON(id, true))
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener { dialog ->
                (dialog as? BottomSheetDialog)
                    ?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
                    ?.let {
                        BottomSheetBehavior.from(it).apply {
                            state = BottomSheetBehavior.STATE_EXPANDED
                            skipCollapsed = true
                            isHideable = true
                        }
                    }
            }
        }
    }

    override fun navigateToAddItem() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTaskTemplates(tasks: List<TaskTemplate>) {
        if (tasks.isEmpty()) tvAdditional.visibility = View.GONE
        adapter.dataset =
            tasks.map { TaskTemplatesAdapter.CheckedItem(it.title, false, it) }.toMutableList()
    }

    companion object {

        @JvmStatic
        fun newInstance(bundle: Bundle) = AddItemFragment().apply {
            arguments = bundle
        }
    }
}