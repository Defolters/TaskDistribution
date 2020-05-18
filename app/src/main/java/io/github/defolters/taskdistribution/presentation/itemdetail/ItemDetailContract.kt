package io.github.defolters.taskdistribution.presentation.itemdetail

import io.github.defolters.taskdistribution.data.remote.model.Item
import io.github.defolters.taskdistribution.data.remote.model.Task

interface ItemDetailContract {
    interface View {
        fun showItem(item: Item)
        fun showTasks(tasks: List<Task>)
    }

    interface Presenter {
        fun getOrder(id: Int)
        fun getTasks(itemId: Int)
    }
}