package com.orafaelsc.fairetest.commom

interface ResourceProvider {
    fun getString(id: Int, vararg params: Any): String
    fun getStringQuantity(id: Int, quantity: Int, vararg params: Any): String
    fun getColor(id: Int): Int
    fun getStringArray(id: Int): Array<String>
    fun getDimensionPixelSize(textSizeBody: Int): Int
}
