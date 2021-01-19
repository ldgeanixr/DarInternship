package com.example.queuestack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

fun main() {
    var obj = MyStack()
    obj.push(1)
    obj.push(2)
    obj.push(3)
    var param_2 = obj.pop()
    var param_3 = obj.top()
    var param_4 = obj.pop()
//    var param_5 = obj.pop()
    var param_6 = obj.empty()
}

class MyStack() {

    /** Initialize your data structure here. */
    var queue: Queue<Int> = LinkedList()

    /** Push element x onto stack. */
    fun push(x: Int) {
        queue.add(x)
    }

    /** Removes the element on top of the stack and returns that element. */
    fun pop(): Int {
        for (i in 1 until queue.size){
            queue.add(queue.poll())
        }

        return queue.poll()
    }

    /** Get the top element. */
    fun top(): Int {
        for (i in 1 until queue.size){
            queue.add(queue.poll())
        }
        var res = queue.peek()
        queue.add(queue.poll())
        return res
    }

    /** Returns whether the stack is empty. */
    fun empty(): Boolean {
        return queue.isEmpty()
    }

}



class MyQueue() {

    /** Initialize your data structure here. */
    var stack1: Stack<Int> = Stack()
    var stack2: Stack<Int> = Stack()


    /** Push element x to the back of queue. */
    fun push(x: Int) {
        stack1.push(x)
    }

    /** Removes the element from in front of queue and returns that element. */
    fun pop(): Int {
        while(stack1.isNotEmpty()){
            stack2.push(stack1.pop())
        }

        var res = stack2.pop()

        while(stack2.isNotEmpty()){
            stack1.push(stack2.pop())
        }

        return res
    }

    /** Get the front element. */
    fun peek(): Int {
        while(stack1.isNotEmpty()){
            stack2.push(stack1.pop())
        }

        var res = stack2.peek()

        while(stack2.isNotEmpty()){
            stack1.push(stack2.pop())
        }

        return res
    }

    /** Returns whether the queue is empty. */
    fun empty(): Boolean {
        return stack1.isEmpty()
    }

}

/**
 * Your MyQueue object will be instantiated and called as such:
 * var obj = MyQueue()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.peek()
 * var param_4 = obj.empty()
 */