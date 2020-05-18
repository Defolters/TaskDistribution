package io.github.defolters.taskdistribution.data.remote.interfaces

import io.github.defolters.taskdistribution.data.remote.model.*
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    // LOGIN
    @POST("users/loginMobile")
    fun login(@Body user: UserJsonModel): Call<LoginJsonModel>

    // ORDERS
    @POST("orders")
    fun createOrder(@Body order: OrderJSON): Call<Order>

    @GET("orders")
    fun getOrders(): Call<List<Order>>

    @GET("orders/{id}")
    fun getOrder(@Path("id") id: Int): Call<Order>

    // ITEMS
    @GET("items")
    fun getItems(@Query("orderId") orderId: Int?): Call<List<Item>>

    @GET("items/{id}")
    fun getItem(@Path("id") id: Int): Call<Item>

    // TASKS
    @GET("tasks")
    fun getTasks(@Query("idemId") idemId: Int?): Call<List<Task>>

    @GET("tasks/{id}")
    fun getTask(@Path("id") id: Int): Call<Task>

    @PUT("tasks")
    fun updateTaskStatus(@Body task: TaskJSON): Call<Task>

    // ITEM TEMPLATES
    @GET("item-templates")
    fun getItemTemplates(): Call<List<ItemTemplate>>

    // TASK TEMPLATES
    @GET("task-templates")
    fun getTaskTemplates(@Query("filter") filter: String?): Call<List<TaskTemplate>>

    // SCHEDULE
    @GET("schedule")
    fun getWorkerSchedule(): Call<ScheduleData>
}