package io.github.defolters.taskdistribution.presentation.addorder.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.defolters.taskdistribution.R
import io.github.defolters.taskdistribution.presentation.addorder.AddOrderContract

/**
 * A simple [Fragment] subclass.
 */
class AddOrderFragment : Fragment(), AddOrderContract.View {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_order, container, false)
    }

    override fun navigateToAddItems() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
