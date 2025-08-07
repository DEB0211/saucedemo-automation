# 🧪 SauceDemo BDD Automation Framework 🚀

This is a complete UI automation framework built using **Selenium**, **Cucumber**, **TestNG**, and **ExtentReports** to test the core workflows of [SauceDemo](https://www.saucedemo.com/).

It supports BDD scenarios, Page Object Model, HTML reports with screenshots, retry logic, and much more.

---

## 🎯 Objective

> Build a robust and scalable UI test suite for SauceDemo using BDD principles.

Covered scenarios:
- ✅ User login/logout
- ✅ Add products to shopping cart
- ✅ Complete checkout flow
- ✅ Handle negative login attempts

---

## 🏗️ Tech Stack

| Tool             | Purpose                         |
|------------------|---------------------------------|
| Java 17          | Programming language            |
| Maven            | Build and dependency management |
| Selenium WebDriver | Browser automation           |
| Cucumber         | BDD with Gherkin                |
| TestNG           | Test runner and retry logic     |
| ExtentReports    | Rich HTML reports               |

---

## 🧪 Features

✅ BDD using Gherkin syntax  
✅ Page Object Model (POM)  
✅ Step-wise logging with `test.info()`  
✅ Screenshot on failure  
✅ Retry logic (5 attempts per failed test)  
✅ DOM Ready check before execution  
✅ Implicit wait (10 seconds)  
✅ Configurable browser & URL via `config.properties`  
✅ HTML ExtentReports with screenshots, logs, tags, and system info  
✅ Tag-based execution with Cucumber CLI filter

---

## 🚦 Folder Structure

.
├── src/
├── ├── main/java/pages/ # Page classes
├── └── test/java/
├── ├── stepdefs/ # Step definitions
├── ├── hooks/ # Hooks for setup/teardown
├── ├── runners/ # TestNG runner
├── └── utils/ # Driver, Logger, Retry utils
├── resources/
├── ├── features/ # Gherkin .feature files
├── └── config/config.properties
├── docs/
├── ├── TestPlan.md # Test strategy
├── └── BugScenario.md # Bug reproduction steps
├── test-output/
└── └── extent-report.html # Final HTML test report

## ⚙️ Configuration

In `src/test/resources/config/config.properties`:

```properties
browser=chrome
url=https://www.saucedemo.com/
username=standard_user
password=secret_sauce
```
## ▶️ How to Run
### Run All Scenarios
```
mvn clean test
```
### Run Only Smoke Scenarios
```
mvn clean test -Dcucumber.filter.tags=@smoke
```

## 📊 Reporting
After test execution, open:
```
test-output/extent-report.html
```

Report contains:

- ✅ Step-by-step logs

- ✅ Tags in title

- ✅ Screenshots on failure

- ✅ Retry attempt info

- ✅ Browser & OS details

## 📄 License

This project is licensed under the [MIT License](./LICENSE).

## 👨‍💻 Author

**Debasish Singh**  
📧 debasishsingh119@gmail.com  
💼 ZopSmart | SSDET | ISTQB CTFL v4.0 | SSDET | Quality Assurance | Agile Methodologies | Selenium | Karate | Rest Assured | Security | Performance Testing

