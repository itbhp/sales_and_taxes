plugins {
  kotlin("jvm") version "1.7.21"
  java
}

val javaVersion = "17"

repositories {
  mavenCentral()
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(javaVersion))
  }
}

kotlin {
  jvmToolchain {
    languageVersion.set(JavaLanguageVersion.of(javaVersion))
  }
}

dependencies {
  implementation("org.slf4j:slf4j-api:2.0.4")

  testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
  testImplementation("org.hamcrest:hamcrest-library:2.2")
}


tasks.test {
  useJUnitPlatform()

  reports {
    junitXml.required.set(false)
    html.required.set(true)
  }

  testLogging {
    events("standardOut", "started", "passed", "skipped", "failed")
  }
}
