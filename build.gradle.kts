import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

allprojects {
    group = "com.kduda.spring-boot.rsocket.spike"
    version = "1.0-SNAPSHOT"

    repositories {
        jcenter()
        mavenLocal()
    }
}

subprojects {
    apply(plugin = "kotlin")

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))
    }

    java.sourceCompatibility = JavaVersion.VERSION_1_8

    val developmentOnly by configurations.creating
    configurations {
        runtimeClasspath {
            extendsFrom(developmentOnly)
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            apiVersion = "1.3"
            languageVersion = "1.3"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }


}

plugins {
    kotlin("jvm") version "1.3.21"
}
