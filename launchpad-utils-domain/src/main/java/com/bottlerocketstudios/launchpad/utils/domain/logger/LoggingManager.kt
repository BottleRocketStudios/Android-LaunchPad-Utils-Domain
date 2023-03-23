package com.bottlerocketstudios.launchpad.utils.domain.logger

/** Wrapper for Logging utility. Implement either the TimberLogger or Android Logger in DI. */
interface LoggingManager {
    /** If using AndroidLogger, note [timberArgs] will be ignored. */
    fun v(
        tag: String? = null,
        message: String?,
        vararg timberArgs: Any? = emptyArray(),
        t: Throwable? = null
    )

    fun d(
        tag: String? = null,
        message: String?,
        vararg timberArgs: Any? = emptyArray(),
        t: Throwable? = null
    )

    fun i(
        tag: String? = null,
        message: String?,
        vararg timberArgs: Any? = emptyArray(),
        t: Throwable? = null
    )

    fun w(
        tag: String? = null,
        message: String?,
        vararg timberArgs: Any? = emptyArray(),
        t: Throwable? = null
    )

    fun e(
        tag: String? = null,
        message: String?,
        vararg timberArgs: Any? = emptyArray(),
        t: Throwable? = null
    )

    fun wtf(
        tag: String? = null,
        message: String?,
        vararg timberArgs: Any? = emptyArray(),
        t: Throwable? = null
    )
}
