//plugins {
////    id 'java'
//    id 'org.jetbrains.kotlin.jvm' version '2.1.20'
//}
////
//group = 'net.scapeemulator'
//version = '1.0-SNAPSHOT'
//
//repositories {
//    mavenCentral()
//}
//
//dependencies {
//
////    testImplementation "junit:junit:4.11"
//}
//plugins {
//    id 'buildlogic.java-conventions'
//    id 'org.jetbrains.kotlin.jvm'
//}
plugins {
    kotlin("jvm")
}

description = "ScapeEmulator Cache Library"

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.3")
}
