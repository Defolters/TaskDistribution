package io.github.defolters.taskdistribution.presentation.taskslist.view


import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.presentation.taskslist.model.TaskModel
import io.github.defolters.taskdistribution.util.getLayoutInflater


class TasksAdapter : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    var dataset: MutableList<TaskModel> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onItemClick: (TaskModel) -> Unit = {}

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
        fun bind(model: TaskModel) {

//            itemView.

//            itemView.tvTitle.text = model.title
//            itemView.tvDescription.text = model.description
//            itemView.ivBackground.setBackgroundResource(model.assignmentBackground)
//            itemView.ivCardImage.setImageResource(model.assignmentImage)
//            itemView.tvDate.text =
//                "Добавлено ${model.meta?.createdAt?.getDayNumber()} ${model.meta?.createdAt?.getMonthName()}"
        }
    }
}