import org.gradle.api.artifacts.dsl.DependencyHandler
import java.util.Locale

// Provides dependencies that can be used throughout the project build.gradle files

// https://github.com/JetBrains/kotlin/blob/master/ChangeLog.md
// https://github.com/JetBrains/kotlin/releases/latest
// https://plugins.jetbrains.com/plugin/6954-kotlin
// https://kotlinlang.org/docs/reference/whatsnew15.html
// https://kotlinlang.org/docs/releases.html#release-details
// TODO: Update corresponding buildSrc/build.gradle.kts value when updating this version!
private const val KOTLIN_VERSION = "1.7.20"
private const val KOTLIN_COROUTINES_VERSION = "1.6.4"

/**
 * Provides the source of truth for version/configuration information to any gradle build file (project and app module build.gradle.kts)
 */
object Config {
    // https://github.com/JLLeitschuh/ktlint-gradle/blob/master/CHANGELOG.md
    // https://github.com/JLLeitschuh/ktlint-gradle/releases
    const val KTLINT_GRADLE_VERSION = "11.0.0"

    // https://github.com/pinterest/ktlint/blob/master/CHANGELOG.md
    // https://github.com/pinterest/ktlint/releases
    const val KTLINT_VERSION = "0.43.2"

    // View how to execute the coverage and verifcations gradle tasks as well as how to view coverage reports in the local jacocoSetup.gradle file
    // http://www.jacoco.org/jacoco/trunk/doc/
    // https://github.com/jacoco/jacoco/releases
    // const val JACOCO_VERSION = "0.8.7" - Helper jacoco gradle files manage the jacoco plugin version due to issues reading this value inside groovy gradle files

    // Website info: https://detekt.github.io/detekt/index.html
    // Rules:
    //   https://detekt.github.io/detekt/comments.html
    //   https://detekt.github.io/detekt/complexity.html
    //   https://detekt.github.io/detekt/coroutines.html
    //   https://detekt.github.io/detekt/empty-blocks.html
    //   https://detekt.github.io/detekt/exceptions.html
    //   https://detekt.github.io/detekt/formatting.html
    //   https://detekt.github.io/detekt/naming.html
    //   https://detekt.github.io/detekt/performance.html
    //   https://detekt.github.io/detekt/style.html
    // Release info: https://github.com/detekt/detekt/releases
    const val DETEKT_VERSION = "1.19.0"

    /**
     * Called from root project buildscript block in the project root build.gradle.kts
     */
    object BuildScriptPlugins {
        // https://developer.android.com/studio/releases/gradle-plugin
        // TODO: Update corresponding buildSrc/build.gradle.kts value when updating this version!
        const val ANDROID_GRADLE = "com.android.tools.build:gradle:7.3.1"
        const val KOTLIN_GRADLE = "org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_VERSION"
    }

    /**
     * Called in non-root project modules via id()[org.gradle.kotlin.dsl.PluginDependenciesSpecScope.id]
     * or kotlin()[org.gradle.kotlin.dsl.kotlin] (the PluginDependenciesSpec.kotlin extension function) in a build.gradle.kts
     */
    object ApplyPlugins {
        const val ANDROID_LIBRARY = "com.android.library"
        const val KT_LINT = "org.jlleitschuh.gradle.ktlint"
        const val DETEKT = "io.gitlab.arturbosch.detekt"
        object Kotlin {
            const val ANDROID = "android"
        }
        const val MAVEN_PUBLISH = "maven-publish"
    }

    // What each version represents - https://medium.com/androiddevelopers/picking-your-compilesdkversion-minsdkversion-targetsdkversion-a098a0341ebd
    object AndroidSdkVersions {
        const val COMPILE_SDK = 33

        // https://developer.android.com/studio/releases/build-tools
        const val BUILD_TOOLS = "31.0.0"
        const val MIN_SDK = 24
        const val MIN_COMPILE_SDK = 29

        // https://developer.android.com/about/versions/12/behavior-changes-12
        const val TARGET_SDK = 31
    }

    // Gradle versions plugin configuration: https://github.com/ben-manes/gradle-versions-plugin#revisions
    fun isNonStable(version: String): Boolean {
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase(Locale.ENGLISH).contains(it) }
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        val isStable = stableKeyword || regex.matches(version)
        return isStable.not()
    }
}

/**
 * Dependency Version definitions with links to source (if open source)/release notes. Defines the version in one place for multiple dependencies that use the same version.
 * Use [DependencyHandler] extension functions below that provide logical groupings of dependencies including appropriate configuration accessors.
 */
private object Libraries {
    //// AndroidX
    // All androidx dependencies version table: https://developer.android.com/jetpack/androidx/versions#version-table
    // https://developer.android.com/jetpack/androidx/releases/core
    // https://developer.android.com/kotlin/ktx#core-packages
    // https://developer.android.com/jetpack/androidx/releases/
    // https://developer.android.com/kotlin/ktx

    // https://developer.android.com/jetpack/androidx/releases/core
    const val CORE_KTX = "androidx.core:core-ktx:1.9.0"

    //// Kotlin
    const val KOTLIN_STDLIB_JDK7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$KOTLIN_VERSION"
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:$KOTLIN_VERSION"

    //// Coroutines + Flow
    // https://kotlinlang.org/docs/reference/coroutines/flow.html
    // https://github.com/Kotlin/kotlinx.coroutines/blob/master/CHANGES.md
    // https://github.com/Kotlin/kotlinx.coroutines/releases

    const val KOTLINX_COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$KOTLIN_COROUTINES_VERSION"
    const val KOTLINX_COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$KOTLIN_COROUTINES_VERSION"

    //// Koin
    // https://github.com/InsertKoinIO/koin/blob/master/CHANGELOG.md
    // https://github.com/InsertKoinIO/koin/tags
    const val KOIN_ANDROID = "io.insert-koin:koin-android:3.2.2"

    //// Core Library Desugaring
    // https://developer.android.com/studio/write/java8-support#library-desugaring
    // https://developer.android.com/studio/write/java8-support-table
    // https://github.com/google/desugar_jdk_libs
    // https://github.com/google/desugar_jdk_libs/blob/master/VERSION.txt
    private const val DESUGAR_VERSION = "1.1.5"
    const val CORE_LIBRARY_DESUGARING = "com.android.tools:desugar_jdk_libs:$DESUGAR_VERSION"

    //// Retrofit
    // javadoc: https://square.github.io/retrofit/2.x/retrofit/
    // https://github.com/square/retrofit/blob/master/CHANGELOG.md
    // https://github.com/square/retrofit/releases
    private const val RETROFIT_VERSION = "2.9.0"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
    const val RETROFIT_SCALARS_CONVERTER = "com.squareup.retrofit2:converter-scalars:$RETROFIT_VERSION"
    const val RETROFIT_MOSHI_CONVERTER = "com.squareup.retrofit2:converter-moshi:$RETROFIT_VERSION"

    //// Moshi
    // https://github.com/square/moshi/blob/master/CHANGELOG.md
    // https://github.com/square/moshi/releases
    private const val MOSHI_VERSION = "1.14.0"

    // Note: DO NOT USE moshi-kotlin as it uses reflection via `KotlinJsonAdapterFactory`.
    // Instead, rely on moshi and the ksp `moshi-kotlin-codegen` dependency AND annotate relevant classes with @JsonClass(generateAdapter = true)
    const val MOSHI = "com.squareup.moshi:moshi:$MOSHI_VERSION"
    const val MOSHI_KOTLIN_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:$MOSHI_VERSION"

    //// Utility
    // https://github.com/BottleRocketStudios/Android-CustomLintRules/releases
    const val BR_CUSTOM_ANDROID_LINT_RULES = "com.github.BottleRocketStudios:Android-CustomLintRules:1.0.0"

    // https://github.com/JakeWharton/timber/blob/master/CHANGELOG.md
    // https://github.com/JakeWharton/timber/releases
    const val TIMBER = "com.jakewharton.timber:timber:5.0.1"

    // Security Crypto
    // Used for EncryptedSharedPrefs
    // https://developer.android.com/jetpack/androidx/releases/security
    const val SECURITY_CRYPTO = "androidx.security:security-crypto:1.0.0"

    // Firebase Analytics
    const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx:21.2.0"
}

/**
 * test and/or androidTest specific dependencies.
 * Use [DependencyHandler] extension functions below that provide logical groupings of dependencies including appropriate configuration accessors.
 */
private object TestLibraries {
    // https://github.com/junit-team/junit4/releases
    // https://github.com/junit-team/junit4/blob/master/doc/ReleaseNotes4.13.md
    const val JUNIT = "junit:junit:4.13.2"

    // main site: https://google.github.io/truth/
    // comparison to other assertion libs: https://google.github.io/truth/comparison
    // benefits: https://google.github.io/truth/benefits
    // javadocs: https://truth.dev/api/1.0/
    // https://github.com/google/truth/releases
    const val TRUTH = "com.google.truth:truth:1.1.3"

    // https://github.com/mockito/mockito-kotlin/wiki/Mocking-and-verifying
    // https://github.com/mockito/mockito-kotlin/releases
    const val MOCKITO_KOTLIN = "org.mockito.kotlin:mockito-kotlin:4.0.0"
}

//// Dependency Groups - to be used inside dependencies {} block instead of declaring all necessary lines for a particular dependency
//// See DependencyHandlerUtils.kt to define DependencyHandler extension functions for types not handled (ex: compileOnly).
//// More info in BEST_PRACTICES.md -> Build section
fun DependencyHandler.kotlinDependencies() {
    implementation(Libraries.KOTLIN_STDLIB_JDK7)
    implementation(Libraries.KOTLIN_REFLECT)
}

fun DependencyHandler.coroutineDependencies() {
    implementation(Libraries.KOTLINX_COROUTINES_CORE)
    implementation(Libraries.KOTLINX_COROUTINES_ANDROID)
}

fun DependencyHandler.coroutineCoreDependency() {
    implementation(Libraries.KOTLINX_COROUTINES_CORE)
}

fun DependencyHandler.koinDependencies() {
    implementation(Libraries.KOIN_ANDROID)
}

fun DependencyHandler.coreLibraryDesugaringDependencies() {
    coreLibraryDesugaring(Libraries.CORE_LIBRARY_DESUGARING)
}

fun DependencyHandler.retrofitDependencies() {
    implementation(Libraries.RETROFIT)
    implementation(Libraries.RETROFIT_SCALARS_CONVERTER)
    implementation(Libraries.RETROFIT_MOSHI_CONVERTER)
}

fun DependencyHandler.moshiDependencies() {
    api(Libraries.MOSHI)
    ksp(Libraries.MOSHI_KOTLIN_CODEGEN)
}

fun DependencyHandler.coreKtxDependencies() {
    implementation(Libraries.CORE_KTX)
}

fun DependencyHandler.timberDependencies() {
    implementation(Libraries.TIMBER)
}

fun DependencyHandler.brCustomAndroidLintRules() {
    implementation(Libraries.BR_CUSTOM_ANDROID_LINT_RULES)
}

fun DependencyHandler.encryptedPrefsDependencies() {
    implementation(Libraries.SECURITY_CRYPTO)
}

fun DependencyHandler.firebaseAnalyticsDependencies() {
    implementation(Libraries.FIREBASE_ANALYTICS)
}

// Test specific dependency groups
fun DependencyHandler.junitDependencies() {
    testImplementation(TestLibraries.JUNIT)
}

fun DependencyHandler.mockitoKotlinDependencies() {
    testImplementation(TestLibraries.MOCKITO_KOTLIN)
}

fun DependencyHandler.truthDependencies() {
    testImplementation(TestLibraries.TRUTH)
}
