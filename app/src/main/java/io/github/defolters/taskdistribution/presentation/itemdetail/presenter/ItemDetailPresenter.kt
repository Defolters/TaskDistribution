package io.github.defolters.taskdistribution.presentation.itemdetail.presenter

import android.util.Log
import io.github.defolters.taskdistribution.data.remote.Api
import io.github.defolters.taskdistribution.presentation.itemdetail.ItemDetailContract
import io.github.defolters.taskdistribution.util.enqueue

class ItemDetailPresenter(private val itemDetailView: ItemDetailContract.View) :
    ItemDetailContract.Presenter {

    override fun getOrder(id: Int) {
        // TODO: INTERACTOR
//        Api.apiService.getOrders()
//            .enqueue(onSuccess = {
//                it?.let { orders ->
//                    ordersListView.showOrders(orders)
//                    Log.d("OrdersListPresenter", orders.toString())
//                }
//            }, onError = {
//                Log.d("OrdersListPresenter", "error ${it.toString()}")
//            })
    }


    override fun getTasks(itemId: Int) {
        // TODO: INTERACTOR
        Api.apiService.getTasks(itemId)
            .enqueue(onSuccess = {
                it?.let { items ->
                    itemDetailView.showTasks(items)
                    Log.d("ItemDetailPresenter", items.toString())
                }
            }, onError = {
                Log.d("ItemDetailPresenter", "error ${it.toString()}")
            })
    }
}