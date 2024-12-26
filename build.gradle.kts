// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}

subprojects {
    // Optionally, you can apply the plugin here if you want to apply it to all sub-projects/modules
    if (project.name != "buildSrc") {
        plugins.withId("com.android.application") {
            // Ensuring the correct version is applied to subprojects
            pluginManager.withPlugin("com.android.application") {
                project.extensions.findByType<com.android.build.gradle.AppExtension>()?.apply {
                    compileSdkVersion(33) // Update as per your requirements
                    defaultConfig.targetSdk = 33
                    // Any other configurations you need to apply for all sub-projects
                }
            }
        }
    }
}
