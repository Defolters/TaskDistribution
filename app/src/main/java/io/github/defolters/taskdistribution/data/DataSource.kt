package io.github.defolters.taskdistribution.data

interface DataSource {
    fun getTasks()
    fun getTask(id: String)
    fun getOrders()
    fun getOrder(id: String)
    fun addOrder()
    fun getItems()
}