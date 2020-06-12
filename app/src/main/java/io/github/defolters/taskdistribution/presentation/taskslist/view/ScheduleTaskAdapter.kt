package io.github.defolters.taskdistribution.presentation.taskslist.view


import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.data.remote.model.ScheduleTaskData
import io.github.defolters.taskdistribution.data.remote.model.TaskStatus
import io.github.defolters.taskdistribution.util.getLayoutInflater
import kotlinx.android.synthetic.main.item_schedule_task.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat


class ScheduleTaskAdapter : RecyclerView.Adapter<ScheduleTaskAdapter.ViewHolder>() {

    var dataset: MutableList<ScheduleTaskData> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onItemClick: (ScheduleTaskData) -> Unit = {}
    var onChangeStatusClick: (ScheduleTaskData) -> Unit = {}

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
        holder.bind(dataset[position], position)
        holder.itemView.setOnClickListener { onItemClick(dataset[position]) }
        holder.itemView.tvButton.setOnClickListener { onChangeStatusClick(dataset[position]) }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(model: ScheduleTaskData, position: Int) {

//            itemView.cardTask.setCardBackgroundColor(Color.parseColor(model.bgColor))

            val pattern = "yyyy-MM-dd HH:mm:ss"
            val df: DateFormat = SimpleDateFormat(pattern)

            val date = df.parse(model.start)
//            val cal = Calendar.getInstance()
//            cal.time = date

            val newPattern = "dd.MM HH:mm"
            val newdf: DateFormat = SimpleDateFormat(newPattern)
            val str = newdf.format(date)

            itemView.tvTask.text = model.title
            itemView.viewColor.setBackgroundColor(Color.parseColor(model.bgColor))
//            itemView.tvDate.text = model.start
//            itemView.tvTimeToComplete.text = model.end
            itemView.tvDateAndMonth.text =
                str.split(" ")[0]
            itemView.tvTime.text =
                str.split(" ")[1]


            when {
                (model.taskStatus == TaskStatus.NEW) && (model.isCanChangeStatus) -> {
                    itemView.viewDivider.visibility = View.VISIBLE
                    itemView.tvButton.visibility = View.VISIBLE
                    itemView.tvButton.text = itemView.resources.getString(R.string.set_in_work)
                }
                (model.taskStatus == TaskStatus.IN_WORK) && (model.isCanChangeStatus) -> {
                    itemView.viewDivider.visibility = View.VISIBLE
                    itemView.tvButton.visibility = View.VISIBLE
                    itemView.tvButton.text = itemView.resources.getString(R.string.set_done)
                }
                else -> {
                    itemView.viewDivider.visibility = View.GONE
                    itemView.tvButton.visibility = View.GONE
                }
            }

        }
    }
}