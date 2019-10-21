plugins {
    id("org.springframework.boot") version "2.2.0.RELEASE"
    kotlin("plugin.spring") version "1.3.50"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
}

dependencies {
    // internal dependencies
    implementation(project(":common"))

    implementation("org.springframework.boot:spring-boot-starter-rsocket")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    compile("org.springframework.boot:spring-boot-starter-actuator")
    compile("io.github.microutils:kotlin-logging:1.7.6")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // use Unit 5
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        exclude(group = "junit", module = "junit")
    }

    testImplementation("io.projectreactor:reactor-test")
}

//protobuf {
//    protoc {
//        artifact = 'com.google.protobuf:protoc:3.7.1'
//    }
//    plugins {
//        rsocketRpc {
//            artifact = 'io.rsocket.rpc:rsocket-rpc-protobuf:0.2.5'
//        }
//    }
//    generateProtoTasks {
//        all()*.plugins {
//            rsocketRpc {}
//        }
//    }
//}
//
//// If you use Intellij add this so it can find the generated classes
//idea {
//    module {
//        sourceDirs += file("src/main/proto")
//        sourceDirs += file("src/generated/main/java")
//        sourceDirs += file("src/generated/main/rsocketRpc")
//
//        generatedSourceDirs += file('src/generated/main/java')
//        generatedSourceDirs += file('src/generated/main/rsocketRpc')
//    }
//}
//
//// clean generated code
//clean {
//    delete 'src/generated/main'
//}

