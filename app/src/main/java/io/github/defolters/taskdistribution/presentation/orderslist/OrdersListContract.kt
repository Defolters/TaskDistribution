package io.github.defolters.taskdistribution.presentation.orderslist

import io.github.defolters.taskdistribution.presentation.orderslist.model.OrderModel

interface OrdersListContract {
    interface View {
        fun navigateToAddOrder()
        fun navigateToOrder()
        fun showOrders(orders: List<OrderModel>)
    }

    interface Presenter {
        fun getOrders()
        // listen username and password fields to activate button
    }
}