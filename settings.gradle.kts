rootProject.name = "spring-boot-rsocket-spike"

include("common")
include("common-rpc")
include("producer")
include("consumer")

// Spring Snapshot and Milestone repositories required for Spring Boot 2.2.0.M4 version
pluginManagement {
    repositories {
        mavenLocal()
        maven { url = uri("https://repo.spring.io/snapshot") }
        maven { url = uri("https://repo.spring.io/milestone") }
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "org.springframework.boot") {
                useModule("org.springframework.boot:spring-boot-gradle-plugin:${requested.version}")
            }
            if (requested.id.id == "com.google.protobuf") {
                useModule("com.google.protobuf:protobuf-gradle-plugin:${requested.version}")
            }
        }
    }
}
