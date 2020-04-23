package io.github.defolters.taskdistribution.presentation.addorder.model

data class ItemModel(
    var id: String,
    var name: String,
    var price: Float,
    val additionalInfo: HashMap<String, String>?
)