package io.github.defolters.taskdistribution.presentation.taskslist

import io.github.defolters.taskdistribution.presentation.taskslist.model.TaskModel

interface TasksListContract {
    interface View {
        fun navigateToTask()
        fun showTasks(tasks: List<TaskModel>)
    }

    interface Presenter {
        fun getTasks()
    }
}