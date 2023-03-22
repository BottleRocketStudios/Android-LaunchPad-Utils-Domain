package com.bottlerocketstudios.launchpad.utils.domain.analytics

/** Used in [AnalyticsInterface.logEvent]. Please see the commented code at the bottom of the file to understand its use. */
interface AnalyticsEvent : BaseAnalyticsEvent {
    val eventName: String
    val params: Map<String, Any>?

    override fun createAnalyticsEvent(): AnalyticsEvent = CustomAnalyticsEvent(eventName, params)
}
