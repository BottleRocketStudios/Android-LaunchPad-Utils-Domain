package com.bottlerocketstudios.launchpad.utils.domain.analytics

/** Base Analytics interface to be implemented by interfaces for specific SDKs like [FirebaseAnalyticsInterface].*/
interface AnalyticsInterface {

    /** The goal is to create classes for each user property that gets logged */
    fun setUserProperty(userProperty: UserProperty)
    fun logEvent(event: AnalyticsEvent)
}

class NewScreenLoadedEvent : AnalyticsEvent {
    override val eventName: String
        get() = "new_screen"
    override val params: Map<String, Any>
        get() = mapOf("hello" to "hello")
}

class UserPreferredLanguageProperty(override val propertyValue: String) : UserProperty {
    override val propertyName: String
        get() = "preferred_language"
}
