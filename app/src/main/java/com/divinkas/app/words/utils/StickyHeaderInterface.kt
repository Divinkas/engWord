package com.divinkas.app.words.utils

import android.view.View

interface StickyHeaderInterface {
    fun isHeader(itemPosition: Int): Boolean
    fun getHeaderLayout(headerPosition: Int): Int
    fun getHeaderPositionForItem(itemPosition: Int): Int
    fun bindHeaderData(header: View, headerPosition: Int)
}