package io.github.defolters.taskdistribution.data.remote.model

enum class UserType {
    ADMIN, SELLER, WORKER
}

enum class TaskStatus {
    NEW, IN_WORK, DONE
}

data class UserJsonModel(val email: String, val password: String)
data class LoginJsonModel(val token: String, val userType: UserType, val workerTypeId: Int?)


data class OrderJSON(
    val id: Int,
    val customerName: String,
    val customerEmail: String,
    val items: List<ItemJSON>,
    val ids: List<Int>?
)

data class ItemJSON(
    val itemTemplateId: Int,
    val info: String,
    val price: Double,
    val taskTemplatesIds: List<Int>?
)

data class TaskTemplateFilterJSON(
    val itemTemplateId: Int?,
    val isAdditional: Boolean?
)

data class TaskJSON(val id: Int, val taskStatus: TaskStatus)

data class Order(
    val id: Int,
    val customerName: String,
    val customerEmail: String,
    val price: Double,
    val createdAt: String,
    val isReady: Boolean
)

data class Item(
    val id: Int,
    val orderId: Int,
    val title: String,
    val info: String,
    val price: Double,
    val isReady: Boolean,
    val color: String
)

data class Task(
    val id: Int,
    val itemId: Int,
    val taskDependencyId: Int?,
    val workerTypeId: Int,
    val title: String,
    val timeToComplete: Int,
    val isAdditional: Boolean,
    val status: TaskStatus,
    val lastStatusUpdate: String
)

data class ItemTemplate(
    val id: Int,
    val title: String
)


data class TaskTemplate(
    val id: Int,
    val title: String,
    val itemTemplateId: Int,
    val taskTemplateDependencyId: Int?,
    val workerTypeId: Int,
    val timeToComplete: Int,
    val isAdditional: Boolean //val type = enumerationByName("type", 10, Type::class.java)
)