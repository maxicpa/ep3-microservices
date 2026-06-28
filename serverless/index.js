/**
 * Serverless Function: notification-handler
 * Descripción: Función FaaS que envía notificaciones cuando
 *              se crean o actualizan proyectos en el sistema.
 *
 * Puede desplegarse en: AWS Lambda, Google Cloud Functions, Vercel, etc.
 * Runtime: Node.js 18
 */

/**
 * Handler principal de la función serverless
 * @param {Object} event - Evento HTTP de entrada
 * @param {Object} context - Contexto de ejecución (Lambda/GCF)
 * @returns {Object} Respuesta HTTP
 */
exports.handler = async (event, context) => {
  console.log("⚡ Serverless function invoked:", JSON.stringify(event));

  try {
    // Parsear body del request
    const body =
      typeof event.body === "string" ? JSON.parse(event.body) : event.body || {};

    const { type, projectId, userId, message } = body;

    // Validación básica
    if (!type) {
      return buildResponse(400, {
        success: false,
        error: "Missing required field: type",
        validTypes: [
          "PROJECT_CREATED",
          "PROJECT_UPDATED",
          "RESOURCE_ASSIGNED",
          "ANALYTICS_ALERT",
          "COLLABORATION_INVITE",
        ],
      });
    }

    // Procesar notificación según tipo
    const notification = await processNotification({ type, projectId, userId, message });

    return buildResponse(200, {
      success: true,
      notification,
      timestamp: new Date().toISOString(),
      executionId: context?.awsRequestId || generateId(),
    });
  } catch (error) {
    console.error("❌ Error processing notification:", error);
    return buildResponse(500, {
      success: false,
      error: "Internal server error",
      details: error.message,
    });
  }
};

/**
 * Procesa la notificación según su tipo
 */
async function processNotification({ type, projectId, userId, message }) {
  const templates = {
    PROJECT_CREATED: {
      title: "🚀 Nuevo Proyecto Creado",
      body: message || `El proyecto ${projectId} ha sido creado exitosamente.`,
      priority: "normal",
      microservice: "ms-project",
    },
    PROJECT_UPDATED: {
      title: "📝 Proyecto Actualizado",
      body: message || `El proyecto ${projectId} ha sido actualizado.`,
      priority: "normal",
      microservice: "ms-project",
    },
    RESOURCE_ASSIGNED: {
      title: "👤 Recurso Asignado",
      body: message || `Un recurso ha sido asignado al proyecto ${projectId}.`,
      priority: "high",
      microservice: "ms-resources",
    },
    ANALYTICS_ALERT: {
      title: "📊 Alerta de Analytics",
      body: message || `Se detectó una anomalía en las métricas del proyecto ${projectId}.`,
      priority: "critical",
      microservice: "ms-analytics",
    },
    COLLABORATION_INVITE: {
      title: "🤝 Invitación de Colaboración",
      body: message || `El usuario ${userId} te ha invitado a colaborar en el proyecto ${projectId}.`,
      priority: "high",
      microservice: "ms-collaboration",
    },
  };

  const template = templates[type];
  if (!template) {
    throw new Error(`Unknown notification type: ${type}`);
  }

  // Simular envío de notificación (email/push/webhook)
  const result = await simulateSend({
    ...template,
    recipientId: userId,
    projectId,
    type,
  });

  return result;
}

/**
 * Simula el envío de la notificación
 * En producción aquí iría: SES, SNS, FCM, Twilio, etc.
 */
async function simulateSend(notification) {
  // Simular latencia de red
  await new Promise((resolve) => setTimeout(resolve, 50));

  console.log("📨 Notification sent:", notification);

  return {
    id: generateId(),
    type: notification.type,
    title: notification.title,
    body: notification.body,
    priority: notification.priority,
    microservice: notification.microservice,
    recipientId: notification.recipientId || "system",
    projectId: notification.projectId || "N/A",
    status: "delivered",
    channel: "in-app",
  };
}

/**
 * Construye la respuesta HTTP estándar
 */
function buildResponse(statusCode, body) {
  return {
    statusCode,
    headers: {
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*",
      "Access-Control-Allow-Methods": "POST, OPTIONS",
    },
    body: JSON.stringify(body),
  };
}

/**
 * Genera un ID único simple
 */
function generateId() {
  return `notif-${Date.now()}-${Math.random().toString(36).substr(2, 9)}`;
}

// ──────────────────────────────────────────────
// Para pruebas locales: node index.js
// ──────────────────────────────────────────────
if (require.main === module) {
  const testEvent = {
    body: JSON.stringify({
      type: "PROJECT_CREATED",
      projectId: "proj-001",
      userId: "user-123",
      message: "Proyecto de prueba creado desde EP3",
    }),
  };

  exports.handler(testEvent, { awsRequestId: "local-test" }).then((res) => {
    console.log("\n✅ Test Result:");
    console.log(JSON.stringify(JSON.parse(res.body), null, 2));
  });
}
