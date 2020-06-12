package io.github.defolters.taskdistribution.presentation.taskslist

import io.github.defolters.taskdistribution.data.remote.model.ScheduleTaskData

interface TasksListContract {
    interface View {
        fun navigateToTask(task: ScheduleTaskData)
        fun navigateToLogin()
        fun showSchedule(tasks: List<ScheduleTaskData>)
    }

    interface Presenter {
        fun getTasks()
        fun logout()
        fun changeStatus(scheduleTaskData: ScheduleTaskData)
    }
}