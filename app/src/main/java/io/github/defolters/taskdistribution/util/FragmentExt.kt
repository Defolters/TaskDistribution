package io.github.defolters.taskdistribution.util

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import io.github.defolters.taskdistribution.R

fun Fragment.navControl(): NavController? {
    return try {
        activity?.findNavController(R.id.nav_host_fragment)
    } catch (e: Exception) {
        null
    }
}