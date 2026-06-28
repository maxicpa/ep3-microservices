# MS-Analytics

Microservicio desarrollado para la evaluación de Integración de Plataformas.

## Descripción

Microservicio encargado de la gestión de indicadores y analíticas de Innovatech Solutions. Permite registrar, consultar, actualizar y eliminar indicadores mediante una API REST desarrollada con Spring Boot.

## Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Data MongoDB
- MongoDB
- Maven
- Lombok

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
| GET | /indicadores |
| GET | /indicadores/{id} |
| POST | /indicadores |
| PUT | /indicadores/{id} |
| DELETE | /indicadores/{id} |

## Instalación

Clonar el repositorio:

```bash
git clone https://github.com/Estebanguajardo/ms-analytics.git
```

Ingresar a la carpeta del proyecto:

```bash
cd ms-analytics
```

## Configuración

Antes de ejecutar el proyecto, asegúrate de que MongoDB esté en ejecución.

Verifica que el archivo:

```text
src/main/resources/application.properties
```

contenga una configuración similar a:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/innovatech_analytics
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
http://localhost:8084
```

## Autor

Esteban Guajardo