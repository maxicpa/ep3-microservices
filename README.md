# EP3 - JVY0101 Java Cloud Native Microservices
## Diseño y Construcción de Soluciones Nativas en Nube

---

## 🏗️ Arquitectura

| Microservicio | Puerto | Descripción |
|---|---|---|
| ms-project | 8080 | Gestión de proyectos |
| ms-resources | 8081 | Gestión de recursos |
| ms-analytics | 8082 | Analíticas y métricas |
| ms-collaboration | 8083 | Colaboración entre equipos |
| notification-handler | FaaS | Función serverless de notificaciones |

---

## 📁 Estructura del Repositorio

```
.
├── ms-project/
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── ms-resources/
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── ms-analytics/
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── ms-collaboration/
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── serverless/
│   ├── index.js          ← Función FaaS
│   ├── test.js           ← Tests
│   └── package.json
├── .github/
│   └── workflows/
│       └── ci-cd.yml     ← Pipeline CI/CD
├── docker-compose.yml    ← Compatible con Docker Swarm
└── README.md
```

---

## 🚀 Cómo ejecutar localmente

### Con Docker Compose:
```bash
docker-compose up --build
```

### Verificar servicios:
```bash
curl http://localhost:8080/actuator/health   # ms-project
curl http://localhost:8081/actuator/health   # ms-resources
curl http://localhost:8082/actuator/health   # ms-analytics
curl http://localhost:8083/actuator/health   # ms-collaboration
```

### Función Serverless local:
```bash
cd serverless
node index.js
```

---

## ⚡ Función Serverless

La función `notification-handler` implementa el patrón FaaS (Function as a Service).

**Tipos de notificación soportados:**
- `PROJECT_CREATED` - Nuevo proyecto creado
- `PROJECT_UPDATED` - Proyecto actualizado
- `RESOURCE_ASSIGNED` - Recurso asignado (ms-resources)
- `ANALYTICS_ALERT` - Alerta de métricas (ms-analytics)
- `COLLABORATION_INVITE` - Invitación de colaboración (ms-collaboration)

**Ejemplo de invocación:**
```json
POST /notify
{
  "type": "PROJECT_CREATED",
  "projectId": "proj-001",
  "userId": "user-123",
  "message": "Proyecto iniciado"
}
```

---

## 🔄 Pipeline CI/CD

El pipeline en `.github/workflows/ci-cd.yml` ejecuta:

1. **Build & Test** - Compila y testea cada microservicio con Maven (matriz paralela)
2. **Docker Build & Push** - Construye y publica imágenes en Docker Hub
3. **Serverless Deploy** - Valida y despliega la función serverless
4. **Summary** - Resumen del despliegue

**Secrets requeridos en GitHub:**
- `DOCKER_USERNAME` - Tu usuario de Docker Hub
- `DOCKER_PASSWORD` - Tu token de Docker Hub

---

## 🐳 Docker Swarm

El `docker-compose.yml` está configurado con:
- Red overlay (`microservices-net`) para comunicación entre servicios
- Política de reinicio automático (`on-failure`)
- Límites de memoria por contenedor (512MB)
- Health checks en todos los servicios

### Inicializar el clúster (nodo manager):
```bash
docker swarm init
```

### Agregar un nodo worker:
```bash
# En el nodo manager, obtener el token:
docker swarm join-token worker

# En el nodo worker, ejecutar:
docker swarm join --token <TOKEN> <IP_MANAGER>:2377
```

### Agregar un nodo manager adicional:
```bash
docker swarm join-token manager
docker swarm join --token <TOKEN> <IP_MANAGER>:2377
```

### Desplegar el stack en Swarm:
```bash
docker stack deploy -c docker-compose.yml ep3
docker stack services ep3
```

### Escalar servicios dinámicamente:
```bash
docker service scale ep3_ms-collaboration=3
docker service scale ep3_ms-project=2
docker service ls
```

### Ver estado del clúster:
```bash
docker node ls
docker service ls
docker service ps ep3_ms-collaboration
```

---

## ☁️ Servicios Cloud AWS

### Cola SQS (innovatech-sqs)
- **ARN:** `arn:aws:sqs:us-east-1:596287682820:innovatech-sqs`
- **URL:** `https://sqs.us-east-1.amazonaws.com/596287682820/innovatech-sqs`
- **Propósito:** Comunicación asíncrona entre microservicios. Cuando ms-project crea un proyecto, envía un mensaje a la cola que es consumido por ms-notification para disparar la función serverless.

### API Gateway (innovatech-api)
- **URL:** `https://u0nb7rzff7.execute-api.us-east-1.amazonaws.com/prod`
- **Propósito:** Punto de entrada unificado y seguro para los microservicios. Controla el acceso externo al backend.

---

## 🎓 Duoc UC - JVY0101
**Evaluación Parcial 3 (EP3) - 40%**
