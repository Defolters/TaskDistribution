package io.github.defolters.taskdistribution.data

import io.github.defolters.taskdistribution.data.local.LocalDataSource
import io.github.defolters.taskdistribution.data.remote.RemoteDataSource

class Repository(
    val remoteDataSource: RemoteDataSource,
    val localDataSource: LocalDataSource
) : DataSource {
    override fun getTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTask(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getOrders() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getOrder(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addOrder() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItems() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private var INSTANCE: Repository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ): Repository {
            return INSTANCE ?: Repository(remoteDataSource, localDataSource)
                .apply { INSTANCE = this }
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }
}