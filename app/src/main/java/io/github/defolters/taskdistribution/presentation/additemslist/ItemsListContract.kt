package io.github.defolters.taskdistribution.presentation.additemslist

import io.github.defolters.taskdistribution.data.remote.model.ItemTemplate

interface ItemsListContract {
    interface View {
        fun showItemTemplates(items: List<ItemTemplate>)
        fun toAddItem(itemTemplate: ItemTemplate)
    }

    interface Presenter {
        fun getItemTemplates()
    }
}