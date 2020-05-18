package io.github.defolters.taskdistribution.presentation.taskdetail.presenter

import android.util.Log
import io.github.defolters.taskdistribution.data.remote.Api
import io.github.defolters.taskdistribution.presentation.taskdetail.TaskDetailContract
import io.github.defolters.taskdistribution.util.enqueue

class TaskDetailPresenter(private val taskDetailView: TaskDetailContract.View) :
    TaskDetailContract.Presenter {

    override fun getTaskDetail(taskId: Int) {
        // TODO: INTERACTOR
        Api.apiService.getTask(taskId)
            .enqueue(onSuccess = {
                it?.let { task ->
                    taskDetailView.showTask(task)
                    Log.d("TaskDetailPresenter", task.toString())
                }
            }, onError = {
                Log.d("TaskDetailPresenter", "error ${it.toString()}")
            })
    }

    override fun getItemDetail(itemId: Int) {
        // TODO: INTERACTOR
        Api.apiService.getItem(itemId)
            .enqueue(onSuccess = {
                it?.let { task ->
                    taskDetailView.showItem(task)
                    Log.d("TaskDetailPresenter", task.toString())
                }
            }, onError = {
                Log.d("TaskDetailPresenter", "error ${it.toString()}")
            })
    }
}