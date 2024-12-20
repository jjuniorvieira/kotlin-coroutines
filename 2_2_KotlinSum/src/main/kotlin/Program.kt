package com.knowledgespike

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.system.measureTimeMillis


val LIMIT = 500_000_000
val SEQUENTIAL_THRESHOLD = LIMIT / 19

suspend fun compute(array: IntArray, start: Int, end: Int): Long = coroutineScope {
    // println("low: $low, high: $high  on ${Thread.currentThread()}")

    if (end - start <= SEQUENTIAL_THRESHOLD) {
        (start until end)
            .asSequence()
            .map { array[it] }
            .sum().toLong()
    } else {
        val mid = (start + end) / 2
        val left = async(Dispatchers.Default) { compute(array, start, mid) }
        val right = async(Dispatchers.Default) { compute(array, mid, end) }
        left.await() + right.await()
    }
}

fun main() = runBlocking {

    val list = mutableListOf<Int>()

    var limit = LIMIT

    for (i in 0 until LIMIT)
        list.add(1)

    var result: Long

    val integers = list.toIntArray()
    val size = integers.size

    result = 0L
    measureTimeMillis {
        result = compute(integers, 0, size)
    }

    println("Start")
    val time: Long = measureTimeMillis {
        result = compute(integers, 0, size)
    }

    print("$result in ${time}ms")

}
