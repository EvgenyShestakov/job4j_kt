plugins {
    kotlin("jvm") version "2.0.21"
    id("io.gitlab.arturbosch.detekt") version "1.23.8"
}

group = "ru.job4j"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-dbcp2:2.7.0")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.1")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("com.h2database:h2:2.2.224")
}

tasks.test {
    useJUnitPlatform()
}

detekt {
    toolVersion = "1.23.8"
    config.from("$projectDir/config/detekt/detekt.yml")
    buildUponDefaultConfig = true
    allRules = false
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
    reports {
        html.required.set(true)
        xml.required.set(true)
        txt.required.set(false)
    }
}