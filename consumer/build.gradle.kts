plugins {
    id("org.springframework.boot") version "2.2.0.M4"
    kotlin("plugin.spring") version "1.3.21"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
}

// Spring Snapshot and Milestone repositories required for Spring Boot 2.2.0.M4 version
repositories {
    maven { url = uri("https://repo.spring.io/snapshot") }
    maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
    implementation(project(":common"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-rsocket") {
        exclude( module = "spring-boot-starter-tomcat")
    }
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    compile("org.springframework.boot:spring-boot-starter-actuator")
    compile("io.github.microutils:kotlin-logging:1.6.26")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // use Unit 5
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        exclude(group = "junit", module = "junit")
    }

    testImplementation("io.projectreactor:reactor-test")
}
