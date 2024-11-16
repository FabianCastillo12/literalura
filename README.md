# 📚 **Literatura App** - Gestión de Libros

**Literatura App** es una aplicación diseñada para gestionar libros. Permite a los usuarios realizar operaciones como guardar libros, buscar por título, autor o idioma, y ver información detallada de cada libro. La aplicación está construida utilizando **Spring Boot** y **JPA**, con acceso a una base de datos relacional.

---

## 🚀 **Características Principales**

### 📖 **Gestión de Libros**
- Permite guardar nuevos libros en la base de datos.
- Cada libro puede incluir detalles como **Título**, **Autor(es)**, **Idioma**, **Tipo de Medio** y **Número de Descargas**.

### 🔍 **Búsqueda Avanzada**
- **Buscar por Título**: Encuentra un libro por su título exacto.
- Busqueda de autor por año

### 📊 **Visualización Detallada**
- Muestra información completa de cada libro, incluyendo su autor(es), idioma, número de descargas, y tipo de medio.
- Listado de libros registrados.
- Listado de autores registrados.

---

## 🧑‍💻 **Cómo Usar la Aplicación**

1. **Iniciar la aplicación**
   La aplicación se ejecuta en un servidor local. Una vez que la aplicación esté en marcha, podrás utilizar las funcionalidades a través de la consola o una interfaz web (si se extiende en el futuro).

2. **Buscar libros**
   Puedes buscar libros proporcionando el titulo.
   
3. **Consultar información**
   Tienes tres opciones para listar los datos:
   - **Libros**: Listar todos los libros guardados en la bd.
   - **Autores**: Listar todos los autores guardados en la bd.
   - **Idioma**: Listar todos los libros de un determinado idioma guardados en la bd.
     
---

## ⚙️ **Tecnologías Utilizadas**

- **Spring Boot**: Framework para el desarrollo rápido de aplicaciones en Java.
- **Spring Data JPA**: Para la integración con bases de datos relacionales.
- **H2 Database**: Base de datos en memoria utilizada para almacenamiento de datos (puede ser reemplazada por cualquier otro sistema de base de datos).
- **Maven**: Herramienta de construcción y gestión de dependencias.
- **Java 17**: Versión del lenguaje Java utilizado en el proyecto.

---

## 📦 **Instalación y Ejecución**

### Requisitos previos

- Java 11 o superior
- Maven
- IDE (IntelliJ IDEA, Eclipse, etc.)

Debes crear tu bd y colocar tu nombre de usuario y contraseña en el archivo de application.propierties para que funcione.
