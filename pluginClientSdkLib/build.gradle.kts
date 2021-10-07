/*
 * android-plugin-client-sdk-for-locale
 * https://github.com/twofortyfouram/android-monorepo
 * Copyright (C) 2008–2018 two forty four a.m. LLC
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
    id("com.android.library")
    kotlin("android")
    `maven-publish`
    id("twofortyfouram.maven-conventions")
}
apply(from = "../scripts.gradle")

val isTestOrchestrator = run {
    val isUseTestOrchestrator: String by project
    isUseTestOrchestrator.toBoolean()
}

group = "com.twofortyfouram"
version = run {
    val libraryVersionName: String by project
    libraryVersionName
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.supportv4)

    implementation(libs.jcip)
    implementation(libs.androidx.annotation)
    implementation(projects.annotationLib)
    implementation(projects.assertionLib)
    implementation(projects.spackleLib)
    implementation(projects.pluginApiLib)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.espresso.intents)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(projects.testLib)

    if (isTestOrchestrator) {
        androidTestUtil(libs.androidx.test.orchestrator) {
            artifact {
                type = "apk"
            }
        }
    }
}

android {
    // Need MockPackageManager
    useLibrary("android.test.mock")

    resourcePrefix = "com_twofortyfouram_locale_sdk_client_"
}

//android.libraryVariants.all { variant ->
//    task("${variant.name}Javadoc", type: Javadoc) {
//        description "Generates Javadoc for $variant.name."
//        def androidHome = System.getenv('ANDROID_HOME')
//        source = variant.javaCompile.source
//        classpath += project.files(android.getBootClasspath().join(File.pathSeparator))
//        classpath += variant.javaCompile.classpath
//        classpath += variant.javaCompile.outputs.files
//        options.linksOffline("https://developer.android.com/reference/", "${androidHome}/docs/reference")
//        options.links("http://jcip.net.s3-website-us-east-1.amazonaws.com/annotations/doc/", 'https://twofortyfouram.github.io/android-annotation/', 'https://twofortyfouram.github.io/android-plugin-api-for-locale/')
//        exclude "**/R.java"
//        exclude "com/twofortyfouram/locale/sdk/client/internal/**"
//    }
//}
//
//// Java8 fails the build because of an error with Javadoc involving formatting. Prevent this (for Travis CI)
//if (JavaVersion.current().isJava8Compatible()) {
//    allprojects {
//        tasks.withType(Javadoc) {
//            options.addStringOption("Xdoclint:none", "-quiet")
//        }
//    }
//}
//
//// Generates the JavaDoc as a JAR for uploading an artifact
//android.libraryVariants.all { variant ->
//    task("${variant.name}JavadocJar", type: Jar, dependsOn: "${variant.name}Javadoc") {
//        classifier = "javadoc"
//        from tasks["${variant.name}Javadoc"].destinationDir
//    }
//
//    project.artifacts.add("archives", tasks["${variant.name}JavadocJar"]);
//}
//
//
//// Generates the source as a JAR for uploading an artifact
//android.libraryVariants.all { variant ->
//    task("${variant.name}SourceJar", type: Jar) {
//        classifier = "sources"
//        from variant.javaCompile.source
//    }
//
//    project.artifacts.add("archives", tasks["${variant.name}SourceJar"]);
//}

publishing {
    publications {
        publications.withType<MavenPublication>().all {
            artifactId = com.twofortyfouram.MavenName.remap(name, "android-plugin-client-sdk-for-locale")
        }
    }
}