package com.bottlerocketstudios.launchpad.utils.domain.analytics

/** Data holder for generic events logged in various analytics SDKs. */
data class CustomAnalyticsEvent(
    override val eventName: String,
    override val params: Map<String, Any>?
) : AnalyticsEvent
