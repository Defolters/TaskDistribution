package io.github.defolters.taskdistribution.presentation.taskslist.presenter

import io.github.defolters.taskdistribution.presentation.taskslist.TasksListContract
import io.github.defolters.taskdistribution.presentation.taskslist.model.TaskModel

class TasksListPresenter(val tasksListView: TasksListContract.View) : TasksListContract.Presenter {

    override fun getTasks() {
        val list = mutableListOf(TaskModel("", 0))
        for (i in 0..10) {
            list.add(TaskModel("", 0))
        }
        tasksListView.showTasks(list)
    }
}