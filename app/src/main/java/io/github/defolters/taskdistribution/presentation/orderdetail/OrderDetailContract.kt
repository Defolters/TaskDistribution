package io.github.defolters.taskdistribution.presentation.orderdetail

import io.github.defolters.taskdistribution.presentation.orderslist.model.OrderModel

interface OrderDetailContract {
    interface View {
        fun showOrder(order: OrderModel)
    }

    interface Presenter {
    }
}