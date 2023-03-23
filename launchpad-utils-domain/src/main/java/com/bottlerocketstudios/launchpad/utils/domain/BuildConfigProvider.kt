package com.bottlerocketstudios.launchpad.utils.domain

/** Abstracts values from the application generated BuildConfig to be accessible anywhere and improve testability */
interface BuildConfigProvider {
    /** True when this is a developer or internalRelease type build. ![isDebugOrInternalBuild] == [isProductionReleaseBuild] */
    val isDebugOrInternalBuild: Boolean

    /** True when this is the production release variant.  ![isProductionReleaseBuild] == [isDebugOrInternalBuild]  */
    val isProductionReleaseBuild: Boolean
}
