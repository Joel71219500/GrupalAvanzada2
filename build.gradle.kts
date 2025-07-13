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
    
    // SPRING FRAMEWORK - CDI y Core
    implementation ("org.springframework:spring-context:6.1.5")
    implementation ("org.springframework:spring-core:6.1.5")
    implementation ("org.springframework:spring-beans:6.1.5")
    implementation ("org.springframework:spring-aop:6.1.5")
    implementation ("org.springframework:spring-expression:6.1.5")
    

    // SPRING DATA (JDBC + JPA)
    implementation ("org.springframework.data:spring-data-jdbc:3.2.5")
    implementation ("org.springframework:spring-jdbc:6.1.6")
    implementation("org.springframework.data:spring-data-jpa:3.2.5")
    implementation ("org.springframework:spring-tx:6.1.6")
    implementation("org.springframework:spring-orm:6.1.6")
    

    
    // CONNECTION POOLING
    implementation("com.zaxxer:HikariCP:5.0.1")
    
    // LOGGING (para mejor debugging)
    implementation ("org.slf4j:slf4j-api:2.0.9")
    implementation ("ch.qos.logback:logback-classic:1.4.11")
    
    // ANNOTATIONS (mejores anotaciones CDI)
    implementation ("jakarta.annotation:jakarta.annotation-api:2.1.1")
    
    // VALIDATION (para validación de datos)
    implementation ("org.springframework:spring-context-support:6.1.5")
    
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
//aqui cambias si quieres ejecutar main o testServicios con ./gradlew run
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

