package io.github.defolters.taskdistribution.presentation.addorder

interface AddOrderContract {
    interface View {
        fun navigateToAddItems()
    }

    interface Presenter {
        fun addItem()
    }
}