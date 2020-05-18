package io.github.defolters.taskdistribution.presentation.addorder.view


import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.data.remote.model.ItemJSON
import io.github.defolters.taskdistribution.util.getLayoutInflater
import kotlinx.android.synthetic.main.item_itemjson_template.view.*


class ItemJsonTemplateAdapter : RecyclerView.Adapter<ItemJsonTemplateAdapter.ViewHolder>() {

    var dataset: MutableList<ItemJSON> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onItemDelete: (ItemJSON) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent.getLayoutInflater().inflate(
                R.layout.item_itemjson_template,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = dataset[position]
        holder.itemView.tvItemName.text = model.info // TODO: add title!!
        holder.itemView.ivDelete.setOnClickListener {
            onItemDelete(model)
        }
        holder.itemView.tvPrice.text = "${model.price}P"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(model: ItemJSON) {


        }
    }
}