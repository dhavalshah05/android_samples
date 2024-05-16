package com.common.logging

import timber.log.Timber

class TimberDebugTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String? {
        val stackElement = Throwable().stackTrace
            .first {
                !it.className.contains("Timber") && !it.className.contains(MyLogger.javaClass.simpleName)
            }
        return super.createStackElementTag(stackElement)
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, tag, "[${Thread.currentThread().name}] $message", t)
    }
}