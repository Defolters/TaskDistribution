package io.github.defolters.taskdistribution.presentation.orderslist

interface OrdersListContract {
    interface View {
    }

    interface Presenter {
        fun getOrders()
        // listen username and password fields to activate button
    }
}