package io.github.defolters.taskdistribution.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


/**
 * Model class for a Item.
 *
 * @param id          id of the item
 */
@Entity(tableName = "items")
data class ItemDBModel @JvmOverloads constructor(
    @PrimaryKey @ColumnInfo(name = "id") var id: String = UUID.randomUUID().toString()
)
