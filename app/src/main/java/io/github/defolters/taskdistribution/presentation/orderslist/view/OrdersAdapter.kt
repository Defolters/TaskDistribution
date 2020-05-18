package io.github.defolters.taskdistribution.presentation.orderslist.view


import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.data.remote.model.Order
import io.github.defolters.taskdistribution.util.getLayoutInflater
import kotlinx.android.synthetic.main.item_order.view.*


class OrdersAdapter : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    var dataset: MutableList<Order> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onItemClick: (Order) -> Unit = {}

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
        fun bind(model: Order) {

            itemView.tvOrder.text = "Order ${model.id}"
            itemView.tvPrice.text = "${model.price}Р"
            itemView.tvDate.text = model.createdAt

            when (model.isReady) {
                false -> itemView.ivStatus.setImageResource(R.drawable.ic_time_black)
                true -> itemView.ivStatus.setImageResource(R.drawable.ic_done_black)
            }

        }
    }
}