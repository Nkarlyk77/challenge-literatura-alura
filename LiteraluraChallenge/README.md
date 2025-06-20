# Proyecto Literalura

**Literalura** es un proyecto de **Back End** desarrollado como parte del curso de Oracle. El objetivo del proyecto es crear una aplicación que permita buscar y gestionar libros y autores mediante una API externa y una base de datos. Este desafío incluye funcionalidades como la búsqueda de libros por título, autores vivos en un año específico, y más.

## Requisitos

- Java 17 o superior
- Dependencias de Spring Boot (para la persistencia de datos)
- Acceso a Internet para consumir datos de la API externa [Gutendex](https://gutendex.com/)

## Funcionalidades

1. **Buscar libros por título**: Permite buscar un libro a través de su título y guardarlo en la base de datos.
2. **Buscar autores por nombre**: Busca autores por nombre e imprime los resultados.
3. **Mostrar libros registrados**: Lista todos los libros almacenados en la base de datos.
4. **Mostrar autores registrados**: Lista todos los autores almacenados en la base de datos.
5. **Mostrar autores vivos en un año**: Muestra los autores que estuvieron vivos en un año específico.
6. **Mostrar libros por idioma**: Permite filtrar los libros registrados por idioma (es, en, fr, pt).
7. **Mostrar Top 10 libros por descargas**: Muestra los 10 libros más descargados.

## Estructura del Proyecto

- **Modelo**: Contiene las entidades `Book` y `Author`, con sus respectivas relaciones.
- **Repositorio**: `AuthorRepository` y `BookRepository` para la gestión de datos.
- **Servicios**: `APIconsume` para consumir la API externa y `dataConverter` para convertir los datos obtenidos.
- **Interfaz de Usuario**: Menú interactivo basado en consola para interactuar con el sistema.

## Uso

### Iniciar el sistema

1. Clona el repositorio en tu máquina local.
2. Compila y ejecuta el proyecto con tu IDE o utilizando Maven/Gradle.
3. Una vez ejecutado, el sistema te permitirá interactuar mediante un menú en la consola.


