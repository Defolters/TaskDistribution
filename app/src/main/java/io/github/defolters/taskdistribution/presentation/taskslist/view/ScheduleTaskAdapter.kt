package io.github.defolters.taskdistribution.presentation.taskslist.view


import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.data.remote.model.ScheduleTaskData
import io.github.defolters.taskdistribution.util.getLayoutInflater
import kotlinx.android.synthetic.main.item_schedule_task.view.*


class ScheduleTaskAdapter : RecyclerView.Adapter<ScheduleTaskAdapter.ViewHolder>() {

    var dataset: MutableList<ScheduleTaskData> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onItemClick: (ScheduleTaskData) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent.getLayoutInflater().inflate(
                R.layout.item_schedule_task,
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
        fun bind(model: ScheduleTaskData) {

//            itemView.cardTask.setCardBackgroundColor(Color.parseColor(model.bgColor))

            itemView.tvTask.text = model.title
            itemView.viewColor.setBackgroundColor(Color.parseColor(model.bgColor))
            itemView.tvDate.text = model.start
            itemView.tvTimeToComplete.text = model.end


        }
    }
}