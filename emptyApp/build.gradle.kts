/*
 * https://github.com/twofortyfouram/android-monorepo
 * Copyright (C) 2008–2020 two forty four a.m. LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = run {
        val androidCompileSdkVersion: String by project
        androidCompileSdkVersion.toInt()
    }
    
    defaultConfig {
        applicationId = "com.twofortyfouram.empty"
        minSdk = 1
        targetSdk = run {
            val androidTargetSdkVersion: String by project
            androidTargetSdkVersion.toInt()
        }
        versionCode = 1
        versionName = "1"
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = "1.8"
        allWarningsAsErrors = true
    }

    signingConfigs {
        getByName("debug").apply {
            storeFile = File("${rootProject.projectDir}/tools/debug.keystore")
        }
    }

    buildTypes {
        getByName("debug").apply {
            isTestCoverageEnabled = run {
                val isCoverageEnabled: String by project
                isCoverageEnabled.toBoolean()
            }

            signingConfig = signingConfigs.getByName("debug")

            applicationIdSuffix = ".debug"
        }
    }
}

// This app exists just to provide an APK for Firebase Test Lab, so it should not be tested
fulladleModuleConfig {
    enabled.set(false)
}