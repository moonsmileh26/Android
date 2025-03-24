package com.example.android.datastructures

class StackImpl<T : Any> : Stack<T> {
    private var stack = arrayListOf<T>()

    override val count: Int
        get() = stack.size

    override fun top(): T? {
        return stack.lastOrNull()
    }

    override fun pop(): T? {
        return stack.removeLastOrNull()
    }

    override fun push(element: T) {
        stack.add(element)
    }
}

fun String.checkParentheses(): Boolean {
    val stack = StackImpl<Char>()
    for (char in this) {
        when (char) {
            '(' -> stack.push(char)
            ')' -> if (stack.isEmpty) return false else stack.pop()
        }
    }
    return stack.isEmpty
}

fun main() {
    println("((()()())()))".checkParentheses())
}