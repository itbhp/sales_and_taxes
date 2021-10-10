plugins{
  kotlin("jvm") version "1.5.31"
}

apply(plugin = "java")

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.slf4j:slf4j-api:1.7.32")

  testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
  testImplementation("org.hamcrest:hamcrest-library:2.2")
}


tasks.test {
  useJUnitPlatform()
}
