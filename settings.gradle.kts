rootProject.name = "spring-boot-rsocket-spike"

include("common")
include("producer")
include("consumer")

// Spring Snapshot and Milestone repositories required for Spring Boot 2.2.0.M4 version
pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/snapshot") }
        maven { url = uri("https://repo.spring.io/milestone") }
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "org.springframework.boot") {
                useModule("org.springframework.boot:spring-boot-gradle-plugin:${requested.version}")
            }
        }
    }
}
