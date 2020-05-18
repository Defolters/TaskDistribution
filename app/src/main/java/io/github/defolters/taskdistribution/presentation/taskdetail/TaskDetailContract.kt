package io.github.defolters.taskdistribution.presentation.taskdetail

import io.github.defolters.taskdistribution.data.remote.model.Item
import io.github.defolters.taskdistribution.data.remote.model.Task

interface TaskDetailContract {
    interface View {
        fun showTask(task: Task)
        fun showItem(item: Item)
    }

    interface Presenter {
        fun getTaskDetail(taskId: Int)
        fun getItemDetail(itemId: Int)
    }
}