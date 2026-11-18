plugins {
    alias(libs.plugins.android.application)
    // AGP 9 trae Kotlin integrado: no hace falta kotlin-android
    alias(libs.plugins.kotlin.compose)
    // Serialización para las rutas de Navigation 3
    alias(libs.plugins.kotlin.serialization)
    // Documentación del código (KDoc) en HTML. La 2.2.0 es la primera compatible con AGP 9
    id("org.jetbrains.dokka") version "2.2.0"
}

android {
    // Debe coincidir con el package del código (MainActivity está en com.example.imcapp)
    namespace = "com.example.imcapp"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.imcapp"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)

    // Navigation 3
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.kotlinx.serialization.core)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

// Documentación del código (KDoc) en HTML con Dokka: .\gradlew :app:dokkaGenerate
// Se genera en docs/kdoc/ y se publica con GitHub Pages.
dokka {
    moduleName.set("IMCApp")
    dokkaPublications.html {
        outputDirectory.set(rootProject.layout.projectDirectory.dir("docs/kdoc"))
        // Oculta lo heredado de Android (ComponentActivity...) para que se vea solo el código propio
        suppressInheritedMembers.set(true)
    }
    dokkaSourceSets.configureEach {
        documentedVisibilities.set(
            setOf(
                org.jetbrains.dokka.gradle.engine.parameters.VisibilityModifier.Public,
                org.jetbrains.dokka.gradle.engine.parameters.VisibilityModifier.Protected,
                org.jetbrains.dokka.gradle.engine.parameters.VisibilityModifier.Internal,
                org.jetbrains.dokka.gradle.engine.parameters.VisibilityModifier.Private
            )
        )
        // Los ejercicios de clase de los módulos 1 a 4 no forman parte de la app
        perPackageOption {
            matchingRegex.set("com\\.example\\.imcapp\\.composables\\.modulo.*")
            suppress.set(true)
        }
    }
}
