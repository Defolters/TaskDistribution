package io.github.defolters.taskdistribution.presentation.orderdetail

import io.github.defolters.taskdistribution.data.remote.model.Item
import io.github.defolters.taskdistribution.data.remote.model.Order

interface OrderDetailContract {
    interface View {
        fun showOrder(order: Order)
        fun showItems(items: List<Item>)
        fun navigateToItem(item: Item)
    }

    interface Presenter {
        fun getOrder(id: Int)
        fun getItems(orderId: Int)
    }
}