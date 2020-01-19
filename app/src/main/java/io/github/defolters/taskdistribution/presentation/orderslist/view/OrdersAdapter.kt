package io.github.defolters.taskdistribution.presentation.orderslist.view


import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.presentation.orderslist.model.OrderModel
import io.github.defolters.taskdistribution.presentation.orderslist.model.OrderStatus
import io.github.defolters.taskdistribution.util.getLayoutInflater
import kotlinx.android.synthetic.main.item_order.view.*


class OrdersAdapter : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    var dataset: MutableList<OrderModel> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onItemClick: (OrderModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent.getLayoutInflater().inflate(
                R.layout.item_order,
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
        fun bind(model: OrderModel) {

            when (model.status) {
                OrderStatus.IN_PROGRESS -> itemView.ivStatus.setImageResource(R.drawable.ic_time_black)
                OrderStatus.DONE -> itemView.ivStatus.setImageResource(R.drawable.ic_done_black)
            }

        }
    }
}