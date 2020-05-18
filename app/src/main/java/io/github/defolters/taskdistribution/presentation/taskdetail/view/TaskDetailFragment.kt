package io.github.defolters.taskdistribution.presentation.taskdetail.view


import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.data.remote.model.Item
import io.github.defolters.taskdistribution.data.remote.model.ScheduleTaskData
import io.github.defolters.taskdistribution.data.remote.model.Task
import io.github.defolters.taskdistribution.data.remote.model.TaskStatus
import io.github.defolters.taskdistribution.presentation.taskdetail.TaskDetailContract
import io.github.defolters.taskdistribution.presentation.taskdetail.presenter.TaskDetailPresenter
import kotlinx.android.synthetic.main.fragment_task_detail.*

/**
 * A simple [Fragment] subclass.
 */
class TaskDetailFragment : BottomSheetDialogFragment(), TaskDetailContract.View {

    private lateinit var presenter: TaskDetailContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = TaskDetailPresenter(this)

        arguments?.getParcelable<ScheduleTaskData>("SCHEDULE_TASK")?.let { task ->

            tvStartDate.text = task.start
            tvEndDate.text = task.end
            presenter.getTaskDetail(task.taskId)
        }


        tvNewButton.setOnClickListener { }
        tvInProgressButton.setOnClickListener { }
        tvDoneButton.setOnClickListener { }
    }

    override fun showTask(task: Task) {
        tvTitle.text = task.title

        when (task.status) {
            TaskStatus.NEW -> tvNewButton.visibility = View.VISIBLE
            TaskStatus.IN_WORK -> tvInProgressButton.visibility = View.VISIBLE
            TaskStatus.DONE -> tvDoneButton.visibility = View.VISIBLE
        }

        presenter.getItemDetail(task.itemId)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener { dialog ->
                (dialog as? BottomSheetDialog)
                    ?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
                    ?.let {
                        BottomSheetBehavior.from(it).apply {
                            state = BottomSheetBehavior.STATE_EXPANDED
                            skipCollapsed = true
                            isHideable = true
                        }
                    }
            }
        }
    }

    override fun showItem(item: Item) {
        tvItemTitle.text = item.title
        tvItemInfo.text = item.info
    }

    companion object {

        @JvmStatic
        fun newInstance(bundle: Bundle) = TaskDetailFragment().apply {
            arguments = bundle
        }
    }
}
