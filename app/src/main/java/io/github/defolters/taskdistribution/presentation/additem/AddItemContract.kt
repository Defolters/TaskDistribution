package io.github.defolters.taskdistribution.presentation.additem

import io.github.defolters.taskdistribution.data.remote.model.TaskTemplate
import io.github.defolters.taskdistribution.data.remote.model.TaskTemplateFilterJSON

interface AddItemContract {
    interface View {
        fun navigateToAddItem()
        fun showTaskTemplates(tasks: List<TaskTemplate>)
    }

    interface Presenter {
        fun getTaskTemplates(filterJSON: TaskTemplateFilterJSON)
    }
}