package io.github.defolters.taskdistribution.presentation.itemdetail.view


import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.data.remote.model.Task
import io.github.defolters.taskdistribution.data.remote.model.TaskStatus
import io.github.defolters.taskdistribution.util.getLayoutInflater
import kotlinx.android.synthetic.main.item_task.view.*


class TasksAdapter : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    var dataset: MutableList<Task> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onItemClick: (Task) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent.getLayoutInflater().inflate(
                R.layout.item_task,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
        holder.itemView.setOnClickListener { onItemClick(dataset[position]) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(model: Task) {

            itemView.tvTask.text = model.title
            itemView.tvDate.text = model.lastStatusUpdate
            itemView.tvTimeToComplete.text = "${model.timeToComplete}h"

            when (model.status) {
                TaskStatus.NEW -> itemView.ivStatus.setImageResource(R.drawable.ic_signs)
                TaskStatus.IN_WORK -> itemView.ivStatus.setImageResource(R.drawable.ic_time_black)
                TaskStatus.DONE -> itemView.ivStatus.setImageResource(R.drawable.ic_done_black)
            }
        }
    }
}