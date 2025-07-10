plugins {
    id("java")
    id("application")
}

group = "com.avanzada2.practicaCDI"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // SPRING
    implementation ("org.springframework:spring-context:6.1.5")
    implementation ("org.springframework.data:spring-data-jdbc:3.2.5")

    // POSTGRESQL
    runtimeOnly ("org.postgresql:postgresql:42.7.3")

    // JAVAFX
    implementation ("org.openjfx:javafx-controls:21")
    implementation ("org.openjfx:javafx-fxml:21")

}

application {
    mainClass.set("com.tuempresa.springfxapp.MainApp")
}


tasks.test {
    useJUnitPlatform()
}

