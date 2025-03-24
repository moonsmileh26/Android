package com.example.android.datastructures

interface Stack<T : Any> {
    fun pop(): T?
    fun push(element: T)
    fun top(): T?
    val count: Int

    val isEmpty: Boolean
        get() = count == 0
}