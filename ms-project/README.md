# MS-Project

Microservicio desarrollado para la evaluación de Integración de Plataformas.

## Descripción

Este microservicio permite administrar proyectos dentro de la plataforma Innovatech Solutions.

## Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Git
- GitHub
- Postman

## Funcionalidades

- Crear proyectos
- Listar proyectos
- Buscar proyecto por ID
- Actualizar proyectos
- Eliminar proyectos
- Validación de datos
- Manejo global de excepciones

## Endpoints

| Método | Endpoint |
|---------|----------|
| GET | /proyectos |
| GET | /proyectos/{id} |
| POST | /proyectos |
| PUT | /proyectos/{id} |
| DELETE | /proyectos/{id} |

## Instalación

Clonar el repositorio:

```bash
git clone https://github.com/Estebanguajardo/ms-project.git
```

Ingresar a la carpeta del proyecto:

```bash
cd ms-project
```

## Configuración

Antes de ejecutar el proyecto, crea una base de datos en PostgreSQL llamada:

```text
innovatech_project
```

Luego abre el archivo:

```text
src/main/resources/application.properties
```

Y verifica que la configuración sea similar a la siguiente:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/innovatech_project
spring.datasource.username=postgres
spring.datasource.password=TU_CONTRASEÑA
```
## Ejecutar el proyecto

Desde la carpeta del proyecto, ejecutar los siguientes comandos:

```bash
mvn clean install
```

Luego iniciar la aplicación:

```bash
mvn spring-boot:run
```

El microservicio quedará disponible en:

```text
http://localhost:8081
```

## Autor

Esteban Guajardo