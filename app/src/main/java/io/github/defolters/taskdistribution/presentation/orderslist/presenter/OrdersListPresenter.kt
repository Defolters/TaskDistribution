package io.github.defolters.taskdistribution.presentation.orderslist.presenter

import io.github.defolters.taskdistribution.presentation.orderslist.OrdersListContract
import io.github.defolters.taskdistribution.presentation.orderslist.model.OrderModel

class OrdersListPresenter(val ordersListView: OrdersListContract.View) :
    OrdersListContract.Presenter {

    override fun getOrders() {
        val list = mutableListOf(OrderModel(0, 0f, ""))
        for (i in 0..10) {
            list.add(OrderModel(0, 0f, ""))
        }
        ordersListView.showOrders(list)
    }

}