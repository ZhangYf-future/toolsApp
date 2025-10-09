plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.zyf.viewlibrary"
    compileSdk = 36

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    afterEvaluate {
        publishing {
            publications {
                create<MavenPublication>("release") {
                    // 设置发布的 Group ID, Artifact ID 和 Version
                    groupId = "com.github.ZhangYf-future"
                    artifactId = project.name
                    version = "1.0.0"

                    // 告诉 Gradle 发布 AAR 文件
                    artifact("$buildDir/outputs/aar/${project.name}-release.aar")

                    // 也可以使用 components.release，但在 JitPack 上有时会有兼容性问题
                    // from(components.release)
                }
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.annotation.jvm)
}