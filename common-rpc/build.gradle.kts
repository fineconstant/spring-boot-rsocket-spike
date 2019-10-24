import com.google.protobuf.gradle.*

dependencies {
    api("com.google.protobuf", "protobuf-java", "3.10.0")
    api("io.rsocket.rpc", "rsocket-rpc-core", "0.2.19")
}

plugins {
    id("com.google.protobuf") version "0.8.10"
}

protobuf {
    // Configure the protoc executable
    protoc {
        // Download from repositories
        artifact = "com.google.protobuf:protoc:3.10.0"
    }

    plugins {
        id("rsocketRpcJava") {
            artifact = "io.rsocket.rpc:rsocket-rpc-protobuf:0.2.19"
        }
    }
    generateProtoTasks {
        all().forEach {
            // Recompile protos when build.gradle has been changed, because
            // it's possible the version of protoc has been changed.
            it.inputs.file("${rootProject.projectDir}/build.gradle.kts")
            it.plugins {
                // Apply the "rsocketRpcJava" plugin whose spec is defined above, without options.
                id("rsocketRpcJava")
            }
        }
    }
}

// If you use Intellij add this so it can find the generated classes
idea {
    module {
        sourceDirs.add(file("src/main/proto"))
        sourceDirs.add(file("src/generated/main/java"))
        sourceDirs.add(file("src/generated/main/rsocketRpc"))
        sourceDirs.add(file("src/generated/main/rsocketRpcJava"))
        sourceDirs.add(file("src/generated/main/rsocketRpcKotlin"))

        generatedSourceDirs.add(file("src/generated/main/java"))
        generatedSourceDirs.add(file("src/generated/main/rsocketRpc"))
        generatedSourceDirs.add(file("src/generated/main/rsocketJava"))
        generatedSourceDirs.add(file("src/generated/main/rsocketRpcKotlin"))
    }
}

tasks {
    // Clean generated code
    clean {
        delete(protobuf.protobuf.generatedFilesBaseDir)
    }
}
