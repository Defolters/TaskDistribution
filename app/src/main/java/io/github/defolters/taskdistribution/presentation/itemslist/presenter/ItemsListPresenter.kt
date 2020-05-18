package io.github.defolters.taskdistribution.presentation.itemslist.presenter

import android.util.Log
import io.github.defolters.taskdistribution.data.remote.Api
import io.github.defolters.taskdistribution.presentation.itemslist.ItemsListContract
import io.github.defolters.taskdistribution.util.enqueue

class ItemsListPresenter(private val itemsListView: ItemsListContract.View) :
    ItemsListContract.Presenter {

    override fun getItemTemplates() {
        // TODO: INTERACTOR
        Api.apiService.getItemTemplates()
            .enqueue(onSuccess = {
                it?.let { items ->
                    //                    ordersListView.showOrders(orders)
                    itemsListView.showItemTemplates(items)
                    Log.d("OrderDetailPresenter", items.toString())
                }
            }, onError = {
                Log.d("OrderDetailPresenter", "error ${it.toString()}")
            })
    }
}