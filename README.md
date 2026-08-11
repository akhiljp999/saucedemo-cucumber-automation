# SauceDemo Cucumber Automation

UI automation framework for [SauceDemo](https://www.saucedemo.com) built with Selenium WebDriver, Cucumber, TestNG, Maven, and Allure reporting.

## Tech Stack

- Java 17
- Maven
- Selenium WebDriver
- Cucumber JVM
- TestNG
- PicoContainer for dependency injection
- Allure reports

## Project Structure

```text
src/main/java
  factory/          Driver and configuration setup
  pages/            Page Object classes

src/test/java
  context/          Shared scenario context
  hooks/            Cucumber hooks
  runner/           TestNG Cucumber runner
  stepDefinitions/  Step definition classes

src/test/resources
  Configs/          Test configuration
  features/         Cucumber feature files

Resources/Reports   Generated test reports
```

## Configuration

Test settings are maintained in:

```text
src/test/resources/Configs/configurations.properties
```

Common properties:

```properties
base.url=https://www.saucedemo.com
browser=chrome
headless=false
implicit.wait.seconds=5
explicit.wait.seconds=10
```

## Run Tests

Run the full Cucumber suite:

```powershell
mvn test
```

Run tests and regenerate the Allure report:

```powershell
mvn test
mvn allure:report
```

If old report data appears in the browser, clean generated reports first:

```powershell
Remove-Item -Recurse -Force Resources\Reports\allure-results, Resources\Reports\allure-report
mvn test
mvn allure:report
```

## Reports

Generated reports are written under:

```text
Resources/Reports
```

Report files:

- Cucumber HTML: `Resources/Reports/cucumber-report.html`
- Cucumber JSON: `Resources/Reports/cucumber-report.json`
- Allure results: `Resources/Reports/allure-results`
- Allure HTML: `Resources/Reports/allure-report/index.html`

To open Allure with a temporary local server:

```powershell
mvn allure:serve
```

## Screenshots And Logs

Failed scenario screenshots are attached from Cucumber hooks and are available in both Cucumber and Allure reports.

Text logs can be attached using:

```java
scenario.attach("Log message", "text/plain", "Info Log");
```

For logs inside a business step, attach the log from the step definition or through a shared report logger object injected by PicoContainer.

## Git Notes

Generated reports should not be committed to Git. They are build artifacts and should be regenerated locally or uploaded from CI.

The following path is ignored:

```text
Resources/Reports/
```

If report files were already added to Git, remove them from the Git index while keeping the files locally:

```powershell
git rm -r --cached Resources/Reports
```

Then commit the source changes, not the generated report output.

## CI/CD Recommendation

For GitHub Actions, run the tests in CI and upload report folders as workflow artifacts instead of committing them:

```text
Resources/Reports/cucumber-report.html
Resources/Reports/cucumber-report.json
Resources/Reports/allure-results
Resources/Reports/allure-report
```
