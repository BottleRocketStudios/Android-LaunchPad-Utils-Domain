plugins {
    id(Config.ApplyPlugins.ANDROID_LIBRARY)
    kotlin(Config.ApplyPlugins.Kotlin.ANDROID)
    id(Config.ApplyPlugins.MAVEN_PUBLISH)
}

android {
    compileSdk = Config.AndroidSdkVersions.COMPILE_SDK
    buildToolsVersion = Config.AndroidSdkVersions.BUILD_TOOLS
    namespace = "com.bottlerocketstudios.launchpad.utils.domain"

    defaultConfig {
        minSdk = Config.AndroidSdkVersions.MIN_SDK
        targetSdk = Config.AndroidSdkVersions.TARGET_SDK
        aarMetadata {
            minCompileSdk = Config.AndroidSdkVersions.MIN_COMPILE_SDK
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                groupId = "com.github.BottleRocketStudios"
                artifactId = "Android-LaunchPad-Utils-Domain"
                version = "0.0.2"

                afterEvaluate {
                    from(components["release"])
                }
            }
        }
    }
}

dependencies {
    kotlinDependencies()
    coroutineCoreDependency()
    encryptedPrefsDependencies()
    timberDependencies()
    firebaseAnalyticsDependencies()
}
