package io.github.defolters.taskdistribution.presentation.addorder

interface AddOrderContract {
    interface View {
        fun navigateToAddItem()
    }

    interface Presenter {
        fun addItem()
    }
}