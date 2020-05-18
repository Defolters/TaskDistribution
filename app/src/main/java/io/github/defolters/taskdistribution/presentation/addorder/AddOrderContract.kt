package io.github.defolters.taskdistribution.presentation.addorder

import io.github.defolters.taskdistribution.data.remote.model.OrderJSON

interface AddOrderContract {
    interface View {
        fun navigateToAddItem()
        fun onAddOrder()
    }

    interface Presenter {
        fun addItem()
        fun createOrder(orderJSON: OrderJSON)
    }
}