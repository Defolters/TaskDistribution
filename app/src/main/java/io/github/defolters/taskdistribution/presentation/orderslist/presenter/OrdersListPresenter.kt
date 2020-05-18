package io.github.defolters.taskdistribution.presentation.orderslist.presenter

import android.util.Log
import io.github.defolters.taskdistribution.data.remote.Api
import io.github.defolters.taskdistribution.presentation.orderslist.OrdersListContract
import io.github.defolters.taskdistribution.util.enqueue
import io.paperdb.Paper

class OrdersListPresenter(private val ordersListView: OrdersListContract.View) :
    OrdersListContract.Presenter {

    override fun getOrders() {

        // TODO: INTERACTOR
        Api.apiService.getOrders()
            .enqueue(onSuccess = {
                it?.let { orders ->
                    ordersListView.showOrders(orders)
                    Log.d("OrdersListPresenter", orders.toString())
                }
            }, onError = {
                Log.d("OrdersListPresenter", "error ${it.toString()}")
            })
    }

    override fun logout() {
        Paper.book().delete("TOKEN")
        Paper.book().delete("WORKER_TYPE_ID")
        Paper.book().delete("USER_TYPE")
        ordersListView.navigateToLogin()
    }
}