# Playwright Hybrid Automation Framework (FinTech Domain)

A scalable, production-ready Hybrid-Driven test automation framework architected using **Java, Playwright, and TestNG**. Designed for high-volume FinTech payment switches and transactional dashboards.

## 🚀 Architecture Highlights
* **Thread-Safe Parallel Execution:** Utilizes `ThreadLocal` for independent `BrowserContext` isolation.
* **Hybrid-Driven (POM + Data-Driven):** Page Object Model coupled with dynamic test datasets via Apache POI.
* **Web-First Assertions:** Built-in auto-waiting completely eliminates flaky `Thread.sleep()` commands.

## ⚙️ Execution
To run the standard regression suite:
`mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml`