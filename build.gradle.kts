plugins {
    id("java")
    id("application")
    id("io.freefair.lombok") version "8.14"
    id("org.openjfx.javafxplugin") version "0.1.0"
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
    implementation ("org.openjfx:javafx-base:21")

}

// Configuración de JavaFX
javafx {
    version = "21"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("com.avanzada.grupal.Main")
}

// Configuración de tareas de ejecución
tasks.named<JavaExec>("run") {
    jvmArgs = listOf(
        "--add-modules", "javafx.controls,javafx.fxml",
        "--add-exports", "javafx.graphics/com.sun.javafx.application=ALL-UNNAMED"
    )
}


tasks.test {
    useJUnitPlatform()
}

