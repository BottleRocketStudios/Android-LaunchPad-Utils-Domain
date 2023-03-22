// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Config.BuildScriptPlugins.ANDROID_GRADLE)
        classpath(Config.BuildScriptPlugins.KOTLIN_GRADLE)
    }
}

plugins {
    id(Config.ApplyPlugins.KT_LINT) version Config.KTLINT_GRADLE_VERSION
    id(Config.ApplyPlugins.MAVEN_PUBLISH)
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

subprojects {
    apply(plugin = Config.ApplyPlugins.KT_LINT)

    // See README.md for more info on ktlint as well as https://github.com/JLLeitschuh/ktlint-gradle#configuration
    ktlint {
        version.set(Config.KTLINT_VERSION)
        debug.set(true) // useful for debugging
        verbose.set(true) // useful for debugging
        android.set(true)
        outputToConsole.set(true)
        ignoreFailures.set(false)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}