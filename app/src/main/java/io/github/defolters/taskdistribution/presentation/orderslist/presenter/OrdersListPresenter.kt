package io.github.defolters.taskdistribution.presentation.orderslist.presenter

import android.util.Log
import io.github.defolters.taskdistribution.data.remote.Api
import io.github.defolters.taskdistribution.presentation.orderslist.OrdersListContract
import io.github.defolters.taskdistribution.presentation.orderslist.model.OrderModel
import io.github.defolters.taskdistribution.presentation.orderslist.model.OrderStatus
import io.github.defolters.taskdistribution.util.enqueue

class OrdersListPresenter(val ordersListView: OrdersListContract.View) :
    OrdersListContract.Presenter {

    override fun getOrders() {

        Api.apiService.getOrders()
            .enqueue(onSuccess = {
                it?.let { loginJsonModel ->

                    Log.d("OrdersListPresenter", loginJsonModel.toString())
                }
            }, onError = {
                Log.d("OrdersListPresenter", "error ${it.toString()}")
            })

        val list = mutableListOf(
            OrderModel("", 0, 0f, "", OrderStatus.IN_PROGRESS)
        )

        for (i in 0..10) {
            list.add(OrderModel("", 0, 0f, "", OrderStatus.DONE))
        }
        ordersListView.showOrders(list)
    }

}