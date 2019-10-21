import com.google.protobuf.gradle.*
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
        compile("com.google.protobuf", "protobuf-java", "3.10.0")
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
    id("com.gradle.build-scan") version "2.1"
    kotlin("jvm") version "1.3.50"
    id("com.google.protobuf") version "0.8.10"
}

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
}

protobuf {
    // Configure the protoc executable
    protoc {
        // Download from repositories
        artifact = "com.google.protobuf:protoc:3.10.0"
    }

    plugins {
        id("rsocketRpc") {
            artifact = "io.rsocket.rpc:rsocket-rpc-protobuf:0.2.19"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                // Apply the "rsocketRpc" plugin whose spec is defined above, without options.
                id("rsocketRpc")
            }
        }
    }


}

//sourceSets{
//    create("sample"){
//        proto {
//            srcDir("src/sample/protobuf")
//        }
//    }
//}

/*
// If you use Intellij add this so it can find the generated classes
idea {
  module {
	sourceDirs += file("src/main/proto")
	sourceDirs += file("src/generated/main/java")
	sourceDirs += file("src/generated/main/rsocketRpc")

	generatedSourceDirs += file('src/generated/main/java')
	generatedSourceDirs += file('src/generated/main/rsocketRpc')
  }
}

// clean generated code
clean {
  delete 'src/generated/main'
}
 */
