# 📚 Proyecto Grupal Avanzada 2 - Online Bookstore

Sistema de gestión de librería online desarrollado con **Spring Framework**, **JavaFX** y **PostgreSQL**.

## 🚀 Tecnologías Utilizadas

- **Java 21** - Lenguaje de programación principal
- **Spring Framework 6.1.5** - Framework de backend
- **Spring Data JDBC 3.2.5** - Acceso a datos
- **JavaFX 21** - Interfaz gráfica de usuario
- **PostgreSQL** - Base de datos
- **Gradle** - Herramienta de construcción
- **Lombok** - Reducción de código boilerplate



### Verificar Instalación:
```bash
java -version  # Debe mostrar Java 21+
psql --version # Verificar PostgreSQL
```

## ⚙️ Configuración del Proyecto

### 1. Configuración de Base de Datos
es la base de datos author books de clases


## 🏃‍♂️ Instrucciones de Ejecución

### Opción 1: Usando Gradle (Recomendado)
```bash
# Windows
gradlew.bat run

# Linux/Mac
./gradlew run
```


## 🎨 Guía para Desarrollo Frontend con JavaFX

### 📐 Scene Builder - Herramienta Recomendada

**Descargar Scene Builder:**
- [Gluon Scene Builder](https://gluonhq.com/products/scene-builder/) (Gratis)
- Integración perfecta con JavaFX y FXML

### 🏗️ Estructura de Archivos FXML

**Ubicación:** `src/main/resources/views/`



### Problemas Comunes:

1. **Error de conexión a BD:**
   - Verificar que PostgreSQL esté ejecutándose
   - Revisar credenciales en `application.properties`

2. **JavaFX no carga:**
   - Verificar que los módulos JavaFX estén en el classpath
   - Revisar la configuración en `build.gradle.kts`

3. **Scene Builder no abre FXML:**
   - Verificar que el archivo FXML esté bien formado
   - Comprobar que el controlador existe


## 📝 Notas Adicionales

- Mantener separación entre lógica de negocio (Service) y UI (Controller)
- Usar inyección de dependencias de Spring en los controladores
- Validar datos en el frontend antes de enviar al backend
- Implementar manejo de errores en la UI
- Considerar internacionalización (i18n) para textos


¡Happy Coding! 🚀 Si tienes problemas, revisa este README primero, luego busca ayuda del equipo.
