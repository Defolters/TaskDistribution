package io.github.defolters.taskdistribution.presentation.taskslist.presenter

import android.util.Log
import io.github.defolters.taskdistribution.data.remote.Api
import io.github.defolters.taskdistribution.data.remote.model.ScheduleTaskData
import io.github.defolters.taskdistribution.data.remote.model.TaskJSON
import io.github.defolters.taskdistribution.data.remote.model.TaskStatus
import io.github.defolters.taskdistribution.presentation.taskslist.TasksListContract
import io.github.defolters.taskdistribution.util.enqueue
import io.paperdb.Paper
import java.text.DateFormat
import java.text.SimpleDateFormat

class TasksListPresenter(private val tasksListView: TasksListContract.View) :
    TasksListContract.Presenter {

    override fun getTasks() {
        val workerTypeId = Paper.book().read<Int>("WORKER_TYPE_ID")

        // TODO: INTERACTOR
        Api.apiService.getWorkerSchedule()
            .enqueue(onSuccess = {
                it?.let { schedule ->
                    val tasks = schedule.tasks.filter { it.resourceId == workerTypeId }

                    if (tasks.isNotEmpty()) {

                        val first = tasks.first()

                        val pattern = "yyyy-MM-dd HH:mm:ss"
                        val df: DateFormat = SimpleDateFormat(pattern)

                        val firstTime = df.parse(first.start).time

                        val isLessTime =
                            schedule.tasks.any { (it.itemId == first.itemId) && (df.parse(it.start).time < firstTime) }

                        if (!isLessTime) first.isCanChangeStatus = true

                    }
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

    override fun changeStatus(scheduleTaskData: ScheduleTaskData) {
        val newStatus = when (scheduleTaskData.taskStatus) {
            TaskStatus.NEW -> TaskStatus.IN_WORK
            TaskStatus.IN_WORK -> TaskStatus.DONE
            else -> return
        }
        Api.apiService.updateTaskStatus(TaskJSON(scheduleTaskData.taskId, newStatus))
            .enqueue(onSuccess = {
                it?.let { task ->
                    Log.d("TasksListPresenter", task.toString())
                    getTasks()
                }
            }, onError = {
                Log.d("TasksListPresenter", "error ${it.toString()}")
            })
    }
}