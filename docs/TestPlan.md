# 🧪 Test Plan - SauceDemo BDD Framework

## Objective
To validate core functionalities of https://www.saucedemo.com/ using a BDD-based test automation framework built with Selenium, Cucumber, TestNG, and ExtentReports.

---

## Scope of Testing
- Login and Logout functionality
- Adding products to the shopping cart
- Completing the checkout process
- Handling invalid login scenarios

---

## Testing Types
- Smoke Testing
- Regression Testing
- Negative Testing

---

## Tools Used
| Tool          | Purpose                  |
|---------------|---------------------------|
| Java 17       | Programming language     |
| Maven         | Dependency management    |
| Selenium      | UI automation            |
| TestNG        | Test execution & retry   |
| Cucumber      | BDD test case definition |
| ExtentReports | Reporting & logging      |

---

## Methodologies
- Behavior Driven Development (BDD)
- Page Object Model (POM)
- Retry Mechanism for flaky tests
- DOM readiness and waits for stability

---

## Execution Strategy
### Run All Tests
```bash
mvn clean test
```
### Run Tagged Tests (e.g., @smoke)
```bash
mvn clean test -Dcucumber.filter.tags=@smoke
```

---

## Browser Compatibility
| Browser | Supported | Notes       |
|---------|-----------|-------------|
| Chrome  | ✅         | Configurable from config.properties |
| Firefox | ✅         | Configurable from config.properties |
| Edge    | ✅         | Configurable from config.properties |
| Others  | ❌         | Not yet added (future enhancement) |

---
