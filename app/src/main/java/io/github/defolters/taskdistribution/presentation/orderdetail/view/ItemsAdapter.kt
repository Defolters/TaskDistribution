package io.github.defolters.taskdistribution.presentation.orderdetail.view


import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.data.remote.model.Item
import io.github.defolters.taskdistribution.util.getLayoutInflater
import kotlinx.android.synthetic.main.item_item.view.*


class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    var dataset: MutableList<Item> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onItemClick: (Item) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent.getLayoutInflater().inflate(
                R.layout.item_item,
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
        fun bind(model: Item) {
            itemView.tvItemName.text = model.title
            itemView.tvPrice.text = model.price.toString()

            when (model.isReady) {
                false -> itemView.ivStatus.setImageResource(R.drawable.ic_time_black)
                true -> itemView.ivStatus.setImageResource(R.drawable.ic_done_black)
            }

        }
    }
}