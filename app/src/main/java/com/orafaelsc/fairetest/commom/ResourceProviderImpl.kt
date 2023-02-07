package com.orafaelsc.fairetest.commom

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class ResourceProviderImpl(private val context: Context) : ResourceProvider {
    override fun getString(@StringRes id: Int, vararg params: Any): String {
        return if (params.isNotEmpty()) {
            context.getString(id, *params)
        } else {
            context.getString(id)
        }
    }

    override fun getStringQuantity(@PluralsRes id: Int, quantity: Int, vararg params: Any): String {
        return if (params.isNotEmpty()) {
            context.resources.getQuantityString(id, quantity, *params)
        } else {
            context.resources.getQuantityString(id, quantity)
        }
    }

    @ColorInt
    override fun getColor(@ColorRes id: Int): Int = ContextCompat.getColor(context, id)

    override fun getStringArray(id: Int): Array<String> = context.resources.getStringArray(id)

    override fun getDimensionPixelSize(@DimenRes textSizeBody: Int): Int = context.resources.getDimensionPixelSize(textSizeBody)
}
