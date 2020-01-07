package io.github.defolters.taskdistribution.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


/**
 * Model class for a Task.
 *
 * @param id          id of the task
 */
@Entity(tableName = "tasks")
data class TaskDBModel @JvmOverloads constructor(
    @PrimaryKey @ColumnInfo(name = "id") var id: String = UUID.randomUUID().toString()
)
