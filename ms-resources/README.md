# MS-Resources

Microservicio desarrollado para la evaluación de Integración de Plataformas.

## Descripción

Microservicio encargado de la gestión de recursos de Innovatech Solutions. Permite registrar, consultar, actualizar y eliminar recursos mediante una API REST desarrollada con Spring Boot.

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
| GET | /recursos |
| GET | /recursos/{id} |
| POST | /recursos |
| PUT | /recursos/{id} |
| DELETE | /recursos/{id} |

## Instalación

Clonar el repositorio:

```bash
git clone https://github.com/Estebanguajardo/ms-resources.git
```

Ingresar a la carpeta del proyecto:

```bash
cd ms-resources
```

## Configuración

Antes de ejecutar el proyecto, crea una base de datos en PostgreSQL llamada:

```text
innovatech_resources
```

Luego abre el archivo:

```text
src/main/resources/application.properties
```

Y verifica que la configuración sea similar a la siguiente:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/innovatech_resources
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
http://localhost:8082
```

## Autor

Esteban Guajardo