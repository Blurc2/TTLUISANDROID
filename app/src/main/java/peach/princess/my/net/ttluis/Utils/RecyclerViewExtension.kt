package peach.princess.my.net.ttluis.Utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Extension functions for RecyclerView
 *
 * @author dD
 */


/**
 * Apply settings for show the item list in horizontal
 * @param[hasFixedSize] true if adapter changes cannot affect the size of the RecyclerView.
 * @param[reverseLayout] Used to reverse item traversal and layout order.
 * @param[stackFromEnd] true to pin the view's content to the bottom edge, false to pin the
 *                      view's content to the top edge
 * @param[pIsNestedScrollingEnabled] true to enable nested scrolling dispatch from this view, false otherwise
 * @param[pIsMotionEventSplittingEnabled] true to allow MotionEvents to be split and dispatched to multiple
 *              child views. false to only allow one child view to be the target of any MotionEvent received by this ViewGroup.
 */
fun RecyclerView.initColumn(hasFixedSize: Boolean = true,
                            reverseLayout: Boolean = false,
                            stackFromEnd: Boolean = false,
                            spacingItem: Int = 0,
                            pIsNestedScrollingEnabled: Boolean = true,
                            pIsMotionEventSplittingEnabled: Boolean = true) {

    setHasFixedSize(hasFixedSize)
    addItemDecoration(SpacesItemDecoration(spacingItem, 3))
    isNestedScrollingEnabled = pIsNestedScrollingEnabled
    isMotionEventSplittingEnabled = pIsMotionEventSplittingEnabled
    layoutManager = getLinearLayoutVertical(context, reverseLayout, stackFromEnd)
}

/**
 * Apply settings for show the item list in vertical
 * @param[hasFixedSize] true if adapter changes cannot affect the size of the RecyclerView.
 * @param[reverseLayout] Used to reverse item traversal and layout order.
 * @param[stackFromEnd] true to pin the view's content to the bottom edge, false to pin the
 *                      view's content to the top edge
 * @param[pIsNestedScrollingEnabled] true to enable nested scrolling dispatch from this view, false otherwise
 * @param[pIsMotionEventSplittingEnabled] true to allow MotionEvents to be split and dispatched to multiple
 *              child views. false to only allow one child view to be the target of any MotionEvent received by this ViewGroup.
 */
fun RecyclerView.initRow(hasFixedSize: Boolean = true,
                         reverseLayout: Boolean = false,
                         stackFromEnd: Boolean = false,
                         spacingItem: Int = 0,
                         pIsNestedScrollingEnabled: Boolean = true,
                         pIsMotionEventSplittingEnabled: Boolean = true,
                         type: Int=1) {

    setHasFixedSize(hasFixedSize)
    isNestedScrollingEnabled = pIsNestedScrollingEnabled
    addItemDecoration(SpacesItemDecoration(spacingItem, type))
    isMotionEventSplittingEnabled = pIsMotionEventSplittingEnabled
    layoutManager = getLinearLayoutHorizontal(context, reverseLayout, stackFromEnd)
}

/**
 * Apply settings for show the item list in grid
 * @param[hasFixedSize] true if adapter changes cannot affect the size of the RecyclerView.
 * @param[numOfColumns] number of columns.
 * @param[spacingItem] space between items.
 * @param[pIsNestedScrollingEnabled] true to enable nested scrolling dispatch from this view, false otherwise
 */
fun RecyclerView.initGrid(hasFixedSize: Boolean = true,
                          numOfColumns: Int = 2,
                          spacingItem: Int = 0,
                          pIsNestedScrollingEnabled: Boolean = true) {

    setHasFixedSize(hasFixedSize)
    isNestedScrollingEnabled = pIsNestedScrollingEnabled
    addItemDecoration(SpacesItemDecoration(spacingItem, 2))
    layoutManager = GridLayoutManager(context, numOfColumns)
}

/**
 * Apply settings for show the item list in grid
 * @param[hasFixedSize] true if adapter changes cannot affect the size of the RecyclerView.
 * @param spanCount The number of columns or rows in the grid
 * @param orientation Layout orientation.
 * @param[cacheSize] Number of views to cache offscreen before returning them to the general recycled view pool
 */
fun RecyclerView.initGridSpan(hasFixedSize: Boolean = true,
                              spanCount: Int = 2,
                              orientation: Int = RecyclerView.VERTICAL,
                              cacheSize: Int = 0) {

    setHasFixedSize(hasFixedSize)
    layoutManager = GridLayoutManager(context, spanCount, orientation, false)
    if (cacheSize > 0) {
        setItemViewCacheSize(cacheSize)//
        isDrawingCacheEnabled = true
        drawingCacheQuality = android.view.View.DRAWING_CACHE_QUALITY_HIGH
    }
}

private fun getLinearLayoutHorizontal(context: Context, reverseLayout: Boolean, stackFromEnd: Boolean) =
        object : LinearLayoutManager(context) {
            override fun requestChildRectangleOnScreen(parent: RecyclerView, child: View, rect: Rect, immediate: Boolean): Boolean {
                return false
            }
        }.apply {
            this.reverseLayout = reverseLayout
            this.stackFromEnd = stackFromEnd
        }

private fun getLinearLayoutVertical(context: Context, reverseLayout: Boolean, stackFromEnd: Boolean) =
        object : LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        {
            override fun requestChildRectangleOnScreen(parent: RecyclerView, child: View, rect: Rect, immediate: Boolean): Boolean {
                return false
            }
        }.apply {
            this.reverseLayout = reverseLayout
            this.stackFromEnd = stackFromEnd
        }


class SpacesItemDecoration(private val mSpace: Int, private val type: Int) : RecyclerView.ItemDecoration() {


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        when (type) {
            1 -> {
                outRect?.bottom = mSpace
            }
            2 ->{
                outRect?.left = mSpace
                outRect?.right = mSpace
                outRect?.bottom=mSpace
            }
            3->{
                outRect?.left=mSpace
                outRect?.right=mSpace
            }
            4->{
                outRect?.bottom=mSpace
            }
            else ->{
                outRect?.left = mSpace
                outRect?.right = mSpace
                outRect?.bottom=mSpace
            }

        }

        // Add top margin only for the first item to avoid double space between items
        //if (parent?.getChildAdapterPosition(view) == 0)
        //  outRect?.top = mSpace
    }

}