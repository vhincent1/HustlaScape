plugins {
    kotlin("jvm")
}

dependencies {
    api(libs.io.netty.netty.buffer)
    api(libs.io.netty.netty.codec)
    api(libs.org.javassist.javassist)
}

description = "ScapeEmulator Utility Library"