package io.github.defolters.taskdistribution.presentation.addorder.presenter

import android.util.Log
import io.github.defolters.taskdistribution.data.remote.Api
import io.github.defolters.taskdistribution.data.remote.model.OrderJSON
import io.github.defolters.taskdistribution.presentation.addorder.AddOrderContract
import io.github.defolters.taskdistribution.util.enqueue

class AddOrderPresenter(val addOrderView: AddOrderContract.View) : AddOrderContract.Presenter {
    override fun addItem() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createOrder(orderJSON: OrderJSON) {
        // TODO: INTERACTOR
        Log.d("AddOrderPresenter", orderJSON.toString())
        Api.apiService.createOrder(orderJSON)
            .enqueue(onSuccess = {
                it?.let { items ->
                    addOrderView.onAddOrder()
                    Log.d("AddOrderPresenter", items.toString())
                }
            }, onError = {
                Log.d("AddOrderPresenter", "error ${it.toString()}")
            })
    }
}