package com.knowledgespike

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import kotlin.concurrent.thread

suspend fun main() {

    GlobalScope.launch {
        delay(1000)
        println("World")
    }

    print("Hello, ")
    delay(1500)
}

fun old_main() {

    thread {
        sleep(1000)
        println("World")
    }

    print("Hello, ")
    Thread.sleep(1500)
}

