package com.common.logging

import timber.log.Timber

class TimberReleaseTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        // implement this method according to logging requirements in release builds
    }
}