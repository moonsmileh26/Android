package com.example.android.datastructures

interface Queue<T> {
    val count: Int
    val isEmpty: Boolean

    fun top(): T?
    fun enqueue(element: T): Boolean
    fun dequeue(): T?
}