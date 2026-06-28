/**
 * Tests básicos para notification-handler serverless function
 */

const { handler } = require("./index");

let passed = 0;
let failed = 0;

async function runTest(name, fn) {
  try {
    await fn();
    console.log(`  ✅ PASS: ${name}`);
    passed++;
  } catch (err) {
    console.log(`  ❌ FAIL: ${name}`);
    console.log(`     ${err.message}`);
    failed++;
  }
}

function assert(condition, message) {
  if (!condition) throw new Error(message || "Assertion failed");
}

async function runAllTests() {
  console.log("\n🧪 Running serverless function tests...\n");

  await runTest("Returns 200 for PROJECT_CREATED", async () => {
    const event = {
      body: JSON.stringify({ type: "PROJECT_CREATED", projectId: "p1", userId: "u1" }),
    };
    const res = await handler(event, { awsRequestId: "test-1" });
    assert(res.statusCode === 200, `Expected 200, got ${res.statusCode}`);
    const body = JSON.parse(res.body);
    assert(body.success === true, "Expected success: true");
    assert(body.notification.status === "delivered", "Expected status: delivered");
  });

  await runTest("Returns 200 for RESOURCE_ASSIGNED", async () => {
    const event = {
      body: JSON.stringify({ type: "RESOURCE_ASSIGNED", projectId: "p2", userId: "u2" }),
    };
    const res = await handler(event, {});
    assert(res.statusCode === 200, `Expected 200, got ${res.statusCode}`);
  });

  await runTest("Returns 200 for ANALYTICS_ALERT", async () => {
    const event = {
      body: JSON.stringify({ type: "ANALYTICS_ALERT", projectId: "p3" }),
    };
    const res = await handler(event, {});
    assert(res.statusCode === 200, `Expected 200, got ${res.statusCode}`);
    const body = JSON.parse(res.body);
    assert(body.notification.priority === "critical", "Expected priority: critical");
  });

  await runTest("Returns 200 for COLLABORATION_INVITE", async () => {
    const event = {
      body: JSON.stringify({ type: "COLLABORATION_INVITE", projectId: "p4", userId: "u4" }),
    };
    const res = await handler(event, {});
    assert(res.statusCode === 200, `Expected 200, got ${res.statusCode}`);
  });

  await runTest("Returns 400 when type is missing", async () => {
    const event = {
      body: JSON.stringify({ projectId: "p5" }),
    };
    const res = await handler(event, {});
    assert(res.statusCode === 400, `Expected 400, got ${res.statusCode}`);
    const body = JSON.parse(res.body);
    assert(body.success === false, "Expected success: false");
  });

  await runTest("Returns 200 for PROJECT_UPDATED with custom message", async () => {
    const event = {
      body: JSON.stringify({
        type: "PROJECT_UPDATED",
        projectId: "p6",
        userId: "u6",
        message: "Hitos actualizados",
      }),
    };
    const res = await handler(event, {});
    assert(res.statusCode === 200, `Expected 200, got ${res.statusCode}`);
    const body = JSON.parse(res.body);
    assert(body.notification.body === "Hitos actualizados", "Custom message not preserved");
  });

  await runTest("Response includes CORS headers", async () => {
    const event = {
      body: JSON.stringify({ type: "PROJECT_CREATED", projectId: "p7" }),
    };
    const res = await handler(event, {});
    assert(res.headers["Access-Control-Allow-Origin"] === "*", "Missing CORS header");
  });

  console.log(`\n────────────────────────────────`);
  console.log(`  Results: ${passed} passed, ${failed} failed`);
  console.log(`────────────────────────────────\n`);

  if (failed > 0) {
    process.exit(1);
  } else {
    console.log("✅ All tests passed!\n");
  }
}

runAllTests();
