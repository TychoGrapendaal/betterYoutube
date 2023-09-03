plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.betteryoutube"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.betteryoutube"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packaging {
        resources.excludes.add("META-INF/DEPENDENCIES")
        resources.excludes.add("META-INF/io.netty.versions.properties")
        resources.excludes.add("META-INF/INDEX.LIST")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")

    implementation("com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava")
//    implementation("io.github.bonigarcia:webdrivermanager:5.5.3")


    implementation("org.seleniumhq.selenium:selenium-android-driver:2.39.0")
    testImplementation("io.appium:java-client:8.5.1")


//    implementation("com.google.android.gms:play-services-vision:20.1.3")
//    implementation("com.google.android.gms:play-services-auth:20.7.0")
//    implementation("com.google.api-client:google-api-client:1.32.1")
//    implementation("com.google.api-client:google-api-client-android:1.31.3")
//    implementation("com.google.apis:google-api-services-youtube:v3-rev20210915-1.32.1")
}