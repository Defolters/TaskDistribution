package io.github.defolters.taskdistribution.presentation

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class EdgeDecorator(private val topPadding: Int?, private val bottomPadding: Int?) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemCount = state.itemCount

        val itemPosition = parent.getChildAdapterPosition(view)

        // no position, leave it alone
        if (itemPosition == RecyclerView.NO_POSITION) {
            return
        }

        if (itemPosition == 0) {
            outRect.set(
                view.paddingLeft,
                topPadding ?: view.paddingTop,
                view.paddingRight,
                view.paddingBottom
            )
        } else if (itemCount > 0 && itemPosition == itemCount - 1) {
            outRect.set(
                view.paddingLeft,
                view.paddingTop,
                view.paddingRight,
                bottomPadding ?: view.paddingBottom
            )
        } else {
            outRect.set(view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom)
        }
    }
}