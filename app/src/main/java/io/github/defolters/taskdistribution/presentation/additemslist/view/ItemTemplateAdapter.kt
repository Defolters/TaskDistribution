package io.github.defolters.taskdistribution.presentation.additemslist.view


import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.data.remote.model.ItemTemplate
import io.github.defolters.taskdistribution.util.getLayoutInflater
import kotlinx.android.synthetic.main.item_item.view.*


class ItemTemplateAdapter : RecyclerView.Adapter<ItemTemplateAdapter.ViewHolder>(), Filterable {

    var dataset: MutableList<ItemTemplate> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var datasetFiltered: MutableList<ItemTemplate> = listOf<ItemTemplate>().toMutableList()
    var onItemClick: (ItemTemplate) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent.getLayoutInflater().inflate(
                R.layout.item_item_template,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return datasetFiltered.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datasetFiltered[position])
        holder.itemView.setOnClickListener { onItemClick(datasetFiltered[position]) }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()

                if (charString.isNotBlank()) {
                    val filteredList: MutableList<ItemTemplate> =
                        listOf<ItemTemplate>().toMutableList()
                    for (item in dataset) {

                        if (item.title.toLowerCase().startsWith(charString.toLowerCase())) {
                            filteredList.add(item)
                        }
                    }

                    datasetFiltered = filteredList
                } else {
                    datasetFiltered = dataset
                }

                val filterResults = FilterResults()
                filterResults.values = datasetFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                datasetFiltered = filterResults.values as MutableList<ItemTemplate>
                notifyDataSetChanged()
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(model: ItemTemplate) {
            itemView.tvItemName.text = model.title

        }
    }
}