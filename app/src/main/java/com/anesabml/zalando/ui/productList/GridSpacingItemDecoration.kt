package com.anesabml.zalando.ui.productList

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class GridSpacingItemDecoration(private val spacing: Float) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.layoutManager is StaggeredGridLayoutManager) {
            val layoutManager = parent.layoutManager as StaggeredGridLayoutManager
            val spanCount = layoutManager.spanCount
            val position = parent.getChildAdapterPosition(view)
            val column = position % spanCount

            outRect.left = (spacing - column * spacing / spanCount).toInt()
            outRect.right = ((column + 1) * spacing / spanCount).toInt()
        }
    }
}
