package com.example.android.datastructures

fun main() {
    recursive(10)
}

fun recursive(n: Int) {
    return if (n < 1) {
        println("Done")
    } else {
        println(n)
        recursive(n - 1)
    }
}