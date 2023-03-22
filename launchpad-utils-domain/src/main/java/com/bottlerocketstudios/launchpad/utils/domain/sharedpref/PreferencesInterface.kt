package com.bottlerocketstudios.launchpad.utils.domain.sharedpref

/**
 * Interface isn't meant to be extended directly.
 * Instead, use this interface and [SecurePreferencesImpl] as an example of how to use the delegates:
 * [BooleanPreferenceDelegate], [FloatPreferenceDelegate], [ListPreferenceDelegate],
 * [MapPreferenceDelegate], [StringPreferenceDelegate], [StringSetPreferenceDelegate],
 * [IntPreferenceDelegate], and [LongPreferenceDelegate]
 */
interface PreferencesInterface {
    var boolVar: Boolean
    var stringVar: String
    var listVar: List<Int>
    var mapVar: Map<String, String>
    var intVar: Int
    var floatVar: Float
    var longVar: Long
    var stringSetVar: Set<String>
}
