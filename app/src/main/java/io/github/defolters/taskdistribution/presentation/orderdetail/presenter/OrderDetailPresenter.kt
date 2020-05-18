package io.github.defolters.taskdistribution.presentation.orderdetail.presenter

import android.util.Log
import io.github.defolters.taskdistribution.data.remote.Api
import io.github.defolters.taskdistribution.presentation.orderdetail.OrderDetailContract
import io.github.defolters.taskdistribution.util.enqueue

class OrderDetailPresenter(private val orderDetailView: OrderDetailContract.View) :
    OrderDetailContract.Presenter {

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

    override fun getItems(orderId: Int) {
        // TODO: INTERACTOR
        Api.apiService.getItems(orderId)
            .enqueue(onSuccess = {
                it?.let { items ->
                    //                    ordersListView.showOrders(orders)
                    orderDetailView.showItems(items)
                    Log.d("OrderDetailPresenter", items.toString())
                }
            }, onError = {
                Log.d("OrderDetailPresenter", "error ${it.toString()}")
            })
    }
}