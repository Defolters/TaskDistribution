package io.github.defolters.taskdistribution.presentation.taskslist.presenter

import android.util.Log
import io.github.defolters.taskdistribution.data.remote.Api
import io.github.defolters.taskdistribution.presentation.taskslist.TasksListContract
import io.github.defolters.taskdistribution.util.enqueue
import io.paperdb.Paper

class TasksListPresenter(private val tasksListView: TasksListContract.View) :
    TasksListContract.Presenter {

    override fun getTasks(workerTypeId: Int) {
        // TODO: INTERACTOR
        Api.apiService.getWorkerSchedule()
            .enqueue(onSuccess = {
                it?.let { schedule ->
                    val tasks = schedule.tasks.filter { it.resourceId == workerTypeId }
                    tasksListView.showSchedule(tasks)
                    Log.d("TasksListPresenter", tasks.toString())
                }
            }, onError = {
                Log.d("TasksListPresenter", "error ${it.toString()}")
            })
    }

    override fun logout() {
        Paper.book().delete("TOKEN")
        Paper.book().delete("WORKER_TYPE_ID")
        Paper.book().delete("USER_TYPE")
        tasksListView.navigateToLogin()
    }
}