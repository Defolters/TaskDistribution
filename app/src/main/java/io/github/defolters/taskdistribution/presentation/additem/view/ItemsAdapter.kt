package io.github.defolters.taskdistribution.presentation.additem.view


import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.data.remote.model.TaskTemplate
import io.github.defolters.taskdistribution.util.getLayoutInflater
import kotlinx.android.synthetic.main.item_task_template.view.*


class TaskTemplatesAdapter : RecyclerView.Adapter<TaskTemplatesAdapter.ViewHolder>() {

    var dataset: MutableList<CheckedItem> = listOf<CheckedItem>().toMutableList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent.getLayoutInflater().inflate(R.layout.item_task_template, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val checkedItem = dataset[position]

        holder.itemView.tvTitle.text = checkedItem.value
        holder.itemView.checkBox.isChecked = checkedItem.isChecked

        holder.itemView.setOnClickListener {
            holder.itemView.checkBox.isChecked = !checkedItem.isChecked
            checkedItem.isChecked = !checkedItem.isChecked
            notifyItemChanged(position)
        }

        holder.itemView.checkBox.setOnClickListener {
            holder.itemView.checkBox.isChecked = !checkedItem.isChecked
            checkedItem.isChecked = !checkedItem.isChecked
            notifyItemChanged(position)
        }
    }

    data class CheckedItem(
        val value: String,
        var isChecked: Boolean,
        val taskTemplate: TaskTemplate
    )

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(model: CheckedItem) {


        }
    }
}