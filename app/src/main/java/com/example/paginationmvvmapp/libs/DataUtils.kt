package com.example.paginationmvvmapp.libs

import android.content.Intent
import com.google.gson.reflect.TypeToken

@Suppress("UNCHECKED_CAST")
inline fun <reified T : Any> Any.checkItemsAre() =
    if (this is List<*>) {
        val list = this
        if (list.all { it is T })
            this as List<T>
        else null
    } else null

@Suppress("UNCHECKED_CAST")
inline fun <reified T : Any> List<*>.checkItemsAre() =
    if (all { it is T })
        this as List<T>
    else null

