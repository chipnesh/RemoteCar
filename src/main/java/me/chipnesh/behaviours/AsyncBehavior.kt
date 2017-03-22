package me.chipnesh.behaviours

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlinx.coroutines.experimental.yield

abstract class AsyncBehavior {

    abstract fun action()
    abstract fun suppress()
    abstract fun takeControl(): Boolean

    private lateinit var job: Job

    fun run() {
        println("Behaviour [${javaClass.name}] started")

        job = launch(newSingleThreadContext(javaClass.name)) {
            while (isActive) {
                var run = false
                while (takeControl()) {
                    run = true
                    action()
                    yield();
                }
                if (run) suppress();
                yield()
            }
        }
    }

    fun stop() {
        job.cancel()
    }
}
