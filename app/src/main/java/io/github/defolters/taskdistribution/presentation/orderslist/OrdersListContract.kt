package io.github.defolters.taskdistribution.presentation.orderslist

import io.github.defolters.taskdistribution.data.remote.model.Order

interface OrdersListContract {
    interface View {
        fun navigateToAddOrder()
        fun navigateToOrder(order: Order)
        fun navigateToLogin()
        fun showOrders(orders: List<Order>)
    }

    interface Presenter {
        fun getOrders()
        fun logout()
    }
}