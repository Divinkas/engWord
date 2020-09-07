package com.divinkas.app.words.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(spaceDp: Int, context: Context) : RecyclerView.ItemDecoration() {
    private var spaceLeft: Int = 0
    private var spaceTop: Int = 0
    private var spaceRight: Int = 0
    private var spaceBottom: Int = 0
    private var space = 0f

    private var mListener: StickyHeaderInterface? = null
    private var mStickyHeaderHeight: Int = 0

    constructor(spaceDp: Int, context: Context, listener: StickyHeaderInterface) : this(
        spaceDp,
        context
    ) {
        mListener = listener
    }

    init {
        this.space = (DumpCalc().convertDpToPixel(spaceDp.toFloat(), context).toInt()).toFloat()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (space == 0f) {
            outRect.left = spaceLeft
            outRect.right = spaceRight
            outRect.bottom = spaceBottom

            if (parent.getChildLayoutPosition(view) == 0)
                outRect.top = spaceTop
            else
                outRect.top = 0
        } else {
            outRect.left = space.toInt()
            outRect.right = space.toInt()
            outRect.bottom = space.toInt()

            if (parent.getChildLayoutPosition(view) == 0)
                outRect.top = space.toInt()
            else
                outRect.top = 0
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val topChild = parent.getChildAt(0) ?: return

        val topChildPosition = parent.getChildAdapterPosition(topChild)
        if (topChildPosition == RecyclerView.NO_POSITION)
            return

        if (mListener != null) {
            val headerPos = mListener!!.getHeaderPositionForItem(topChildPosition)
            val currentHeader = getHeaderViewForItem(headerPos, parent)
            fixLayoutSize(parent, currentHeader)
            val contactPoint = currentHeader.bottom
            val childInContact = getChildInContact(parent, contactPoint, headerPos)

            if (childInContact != null && mListener!!.isHeader(
                    parent.getChildAdapterPosition(
                        childInContact
                    )
                )
            ) {
                moveHeader(c, currentHeader, childInContact)
                return
            }
            drawHeader(c, currentHeader)
        }
    }

    private fun getHeaderViewForItem(headerPosition: Int, parent: RecyclerView): View {
        val layoutResId = mListener!!.getHeaderLayout(headerPosition)
        val header = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        mListener!!.bindHeaderData(header, headerPosition)
        return header
    }

    private fun drawHeader(c: Canvas, header: View) {
        c.save()
        c.translate(0f, 0f)
        header.draw(c)
        c.restore()
    }

    private fun moveHeader(c: Canvas, currentHeader: View, nextHeader: View) {
        c.save()
        c.translate(0f, (nextHeader.top - currentHeader.height).toFloat())
        currentHeader.draw(c)
        c.restore()
    }

    private fun getChildInContact(
        parent: RecyclerView,
        contactPoint: Int,
        currentHeaderPos: Int
    ): View? {
        var childInContact: View? = null
        for (i in 0 until parent.childCount) {
            var heightTolerance = 0
            val child = parent.getChildAt(i)

            if (currentHeaderPos != i) {
                val isChildHeader = mListener!!.isHeader(parent.getChildAdapterPosition(child))
                if (isChildHeader)
                    heightTolerance = mStickyHeaderHeight - child.height
            }

            val childBottomPosition: Int
            childBottomPosition = if (child.top > 0)
                child.bottom + heightTolerance
            else
                child.bottom

            if (childBottomPosition > contactPoint)
                if (child.top <= contactPoint) {
                    childInContact = child
                    break
                }
        }
        return childInContact
    }

    private fun fixLayoutSize(parent: ViewGroup, view: View) {
        val widthSpec = View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY)
        val heightSpec =
            View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.UNSPECIFIED)

        val childWidthSpec =
            ViewGroup.getChildMeasureSpec(
                widthSpec,
                parent.paddingLeft + parent.paddingRight,
                view.layoutParams.width
            )
        val childHeightSpec =
            ViewGroup.getChildMeasureSpec(
                heightSpec,
                parent.paddingTop + parent.paddingBottom,
                view.layoutParams.height
            )

        view.measure(childWidthSpec, childHeightSpec)
        mStickyHeaderHeight = view.measuredHeight

        view.layout(0, 0, view.measuredWidth, mStickyHeaderHeight)
    }
}