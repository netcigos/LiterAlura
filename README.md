<h1 align="center">Aplicación Challenger Literatura AluraLatam</h1>

Esta aplicación, basada en **Java 17**, esta desarrollado para el Challenger Literatura Alura Latam. Aplica los conceptos de persistencia de datos en el framework Spring Boot 3.3 . 
Se uso Spring Boot 3.3, JPA , JPQL , Jackson JSON , Maven , Motor Base Datos Postgres.  
usando JPA , JPQL y Hibernate 
Hace uso de las api https://gutendex.com/ para la consulta de libros . 

## Características

 *** Funcionalidades:**
  1. Busqueda de un Libro por Nombre en la Api Gutendex y se guarda en la base de datos local postgres.
  2. Ver todos los libros registrados en la base de datos local.
  3. Se almacenan los Autores de los Libros en la base de datos local.
  4. Ver todos los autores registrados. Busqueda de Autores vivos para un año ingresado por el usuario.
  5. Ver todos los libros para un idioma ingresado por el usuario.
  6. Muestra estadisticas simples como cantidad de libros encontrados , promedio de descargas .
  7. Salir de la aplicación.

## Requisitos

- **Java 17** 
- Biblioteca **Jackson** para procesamiento de JSON.
- Apis https://www.gutendex.com/ Consulta de libros
- Framework Spring Boot . Dependencias JPA , Postgres Driver .
- Motor Base Datos Posgres. 

## Instalación

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/netcigos/LiterAlura.git

2. Build en IDE de desarrollo .
## Pantallas

| Pantalla                      | Vista                                                                                   |
|-------------------------------|-----------------------------------------------------------------------------------------|
| Principal                     | ![Principal](https://github.com/user-attachments/assets/9ec69531-7d91-4d32-a0c4-90b3af243ec1) |
| Búsqueda Libro API            | ![Búsqueda Libro](https://github.com/user-attachments/assets/33d07ed5-15e4-4640-8a0e-54b085769be3) |
| Lista de libros registrados   | ![Libros Registrados](https://github.com/user-attachments/assets/25b118b6-cd04-4198-b11a-3151bc59ca21) |
| Lista de autores registrados  | ![Autores Registrados](https://github.com/user-attachments/assets/84c3e9a3-7e82-4af4-8c3e-62f1e3004482) |
| Lista de autores vivos        | ![Autores Vivos](https://github.com/user-attachments/assets/940e6297-f365-4e90-b8a9-547204eac8e5) |
| Lista de libros por idioma    | ![Idiomas](https://github.com/user-attachments/assets/58a828ae-5329-4962-ab10-2b51145994d1) |

