package io.github.defolters.taskdistribution.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.defolters.taskdistribution.data.local.model.TaskDBModel

/**
 * Data Access Object for the tasks table.
 */
@Dao
interface TasksDao {
    /**
     * Select all tasks from the tasks table.
     *
     * @return all tasks.
     */
    @Query("SELECT * FROM Tasks")
    fun getTasks(): List<TaskDBModel>
}