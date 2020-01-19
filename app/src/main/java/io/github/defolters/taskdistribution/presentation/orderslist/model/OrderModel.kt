package io.github.defolters.taskdistribution.presentation.orderslist.model

data class OrderModel(
    val id: String,
    var number: Int,
    var price: Float,
    var date: String,
    val status: OrderStatus
)

enum class OrderStatus { IN_PROGRESS, DONE }