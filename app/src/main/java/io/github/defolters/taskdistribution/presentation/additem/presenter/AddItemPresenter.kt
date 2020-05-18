package io.github.defolters.taskdistribution.presentation.additem.presenter

import android.util.Log
import com.google.gson.Gson
import io.github.defolters.taskdistribution.data.remote.Api
import io.github.defolters.taskdistribution.data.remote.model.TaskTemplateFilterJSON
import io.github.defolters.taskdistribution.presentation.additem.AddItemContract
import io.github.defolters.taskdistribution.util.enqueue


class AddItemPresenter(private val addItemView: AddItemContract.View) : AddItemContract.Presenter {
    override fun getTaskTemplates(filterJSON: TaskTemplateFilterJSON) {
        // TODO: INTERACTOR
        val gson = Gson()
        val filter = gson.toJson(filterJSON)
        Api.apiService.getTaskTemplates(filter)
            .enqueue(onSuccess = {
                it?.let { items ->
                    addItemView.showTaskTemplates(items)
                    Log.d("AddItemPresenter", items.toString())
                }
            }, onError = {
                Log.d("AddItemPresenter", "error ${it.toString()}")
            })
    }
}