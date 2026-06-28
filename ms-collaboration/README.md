# MS-Collaboration

Microservicio desarrollado para la evaluación de Integración de Plataformas.

## Descripción

Microservicio encargado de la gestión de mensajes y colaboración entre los equipos de Innovatech Solutions. Permite registrar, consultar y eliminar mensajes mediante una API REST desarrollada con Spring Boot y MongoDB.

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
| GET | /mensajes |
| GET | /mensajes/{id} |
| POST | /mensajes |
| DELETE | /mensajes/{id} |

## Instalación

Clonar el repositorio:

```bash
git clone https://github.com/Estebanguajardo/ms-collaboration.git
```

Ingresar a la carpeta del proyecto:

```bash
cd ms-collaboration
```

## Configuración

Antes de ejecutar el proyecto, asegúrate de que MongoDB esté en ejecución.

Verifica que el archivo:

```text
src/main/resources/application.properties
```

contenga una configuración similar a:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/innovatech_collaboration
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
http://localhost:8083
```

## Autor

Esteban Guajardo