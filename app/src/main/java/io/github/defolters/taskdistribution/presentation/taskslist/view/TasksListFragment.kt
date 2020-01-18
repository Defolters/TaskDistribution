package io.github.defolters.taskdistribution.presentation.taskslist.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.presentation.taskslist.TasksListContract
import io.github.defolters.taskdistribution.presentation.taskslist.model.TaskModel
import io.github.defolters.taskdistribution.presentation.taskslist.presenter.TasksListPresenter
import io.github.defolters.taskdistribution.util.navControl
import kotlinx.android.synthetic.main.fragment_tasks_list.*

/**
 * A simple [Fragment] subclass.
 */
class TasksListFragment : Fragment(), TasksListContract.View {

    private lateinit var presenter: TasksListContract.Presenter
    //    private lateinit var binding: FragmentOrdersListBinding
    private lateinit var adapter: TasksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = TasksListPresenter(this)

        adapter = TasksAdapter()

        val llm = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvTasks.layoutManager = llm
        rvTasks.adapter = adapter

        adapter.onItemClick = {
            navigateToTask()
        }

        presenter.getTasks()
    }

    override fun showTasks(tasks: List<TaskModel>) {
        adapter.dataset = tasks.toMutableList()
    }

    override fun navigateToTask() {
        navControl()?.navigate(R.id.action_tasksListFragment_to_taskDetailFragment)
    }
}
