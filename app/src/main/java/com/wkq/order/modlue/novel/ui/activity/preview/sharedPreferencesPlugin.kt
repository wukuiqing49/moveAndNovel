package com.wkq.order.modlue.novel.ui.activity.preview

import android.content.Context
import android.content.SharedPreferences
import com.wkq.order.application.OrderApplication


val Context.defaultSharedPreferences get() = sharedPreferences("share_data")

fun Context.sharedPreferences(name: String): SharedPreferences = getSharedPreferences(name, Context.MODE_PRIVATE)
fun SharedPreferences.editor(editorBuilder: SharedPreferences.Editor.() -> Unit) = edit().apply(editorBuilder).apply()

fun defaultSharedPreferences(): SharedPreferences = OrderApplication.getContext().defaultSharedPreferences