# SauceDemo Cucumber Automation

UI tests for [SauceDemo](https://www.saucedemo.com), built with Selenium WebDriver, Cucumber JVM, TestNG, Maven, PicoContainer, and Allure.

## Prerequisites

- JDK 17 or later (the project targets Java 17).
- Maven available on your terminal's PATH, or IntelliJ IDEA's bundled Maven.
- Chrome, Edge, or Firefox installed for the selected browser.
- Network access to SauceDemo and, on the first run, Maven dependencies and Selenium-managed browser drivers.

Import `pom.xml` as a Maven project in IntelliJ IDEA and select the project JDK.

## Project structure

```text
src/main/java
  factory/          Browser driver and configuration setup
  models/           Captured product data
  pages/            Page objects

src/test/java
  context/          Scenario-specific shared state
  hooks/            Browser lifecycle and failure screenshots
  runner/           TestNG Cucumber runner
  stepDefinitions/  Step definitions and assertions

src/test/resources
  Configs/          Test configuration
  features/         Cucumber feature files
  allure.properties Allure results configuration

Resources/Reports/  Generated Cucumber and Allure reports
```

## Configuration

Settings are loaded from `src/test/resources/Configs/configurations.properties`:

```properties
base.url=https://www.saucedemo.com
browser=chrome
adminUser=standard_user
adminPassword=secret_sauce
headless=false
explicit.wait.seconds=10
```

Supported browser values are `chrome`, `edge`, and `firefox`. JVM system properties override the configuration file. The `implicit.wait.seconds` property is currently unused; the framework uses explicit waits.

## Run tests

From the project root, run the full suite:

```powershell
mvn test
```

Run in headless Chrome:

```powershell
mvn test "-Dbrowser=chrome" "-Dheadless=true"
```

Run login scenarios or the tagged smoke scenario:

```powershell
mvn test "-Dcucumber.filter.tags=@login"
mvn test "-Dcucumber.filter.tags=@smoke"
```

Run a specific scenario by its name:

```powershell
mvn test "-Dcucumber.filter.name=^Display the selected items in the shopping cart$"
```

Validate feature syntax and step bindings without launching a browser:

```powershell
mvn test "-Dcucumber.execution.dry-run=true"
```

If `mvn` is unavailable in your terminal, use IntelliJ's Maven tool window to run the same goals and properties, or add Maven's `bin` directory to PATH.

### Run from IntelliJ IDEA

Run `src/test/java/runner/SauceDemoRunner.java` to use its configured plugins and execute the full suite.

For the green Run icon beside a feature or scenario, install/enable IntelliJ's Cucumber for Java and Gherkin plugins. Direct feature runs bypass the runner's `@CucumberOptions`. To collect Allure results, open **Run > Edit Configurations**, select the **Cucumber Java** configuration, and append this to **Program arguments**:

```text
--plugin io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm
```

Keep any existing arguments and set the working directory to the project root. The Allure output path is configured in `src/test/resources/allure.properties`. Cucumber HTML and JSON output requires the corresponding plugins when running features directly; the TestNG runner already configures them.

## Reports

| Output | Location |
| --- | --- |
| Cucumber HTML | `Resources/Reports/cucumber-report.html` |
| Cucumber JSON | `Resources/Reports/cucumber-report.json` |
| Allure raw results | `Resources/Reports/allure-results/` |
| Allure generated HTML | `Resources/Reports/allure-report/index.html` |
| Maven test results | `target/surefire-reports/` |

A test run writes raw Allure results. It does **not** regenerate the HTML report automatically.

Generate the HTML report after a run:

```powershell
mvn allure:report
```

Or run tests and generate the report in one command (report generation runs if the tests succeed):

```powershell
mvn test allure:report
```

Generate a fresh temporary report and view it through a local server:

```powershell
mvn allure:serve
```

Keep the server running while viewing the report; press Ctrl+C to stop it. Serve Allure through a local server rather than opening `index.html` directly.

After Maven has installed the bundled Allure CLI, these PowerShell commands are also available from the project root (the installed version is currently 2.30.0):

```powershell
# Generate a temporary report from the latest raw results and open it.
.\.allure\allure-2.30.0\bin\allure.bat serve .\Resources\Reports\allure-results

# View an already generated HTML report without regenerating it.
.\.allure\allure-2.30.0\bin\allure.bat open .\Resources\Reports\allure-report
```

### Start a report with only new results

Allure results accumulate between runs. `mvn clean` removes `target/`, but does not remove `Resources/Reports/`. To exclude earlier runs, delete the results and generated Allure report directories **before** rerunning tests. From the project root in PowerShell:

```powershell
Remove-Item -LiteralPath .\Resources\Reports\allure-results, .\Resources\Reports\allure-report -Recurse -Force -ErrorAction SilentlyContinue
mvn test
mvn allure:report
```

## Coverage and assertions

The features cover login, logout, home page layout, menu options, the shopping cart, and checkout. Cart and overview assertions compare the selected products' names, descriptions, prices, quantities, and duplicate counts against values captured on the home page. This checks consistency across pages; it does not independently validate catalog prices.

Checkout totals are calculated from the captured prices and quantities. The expected tax rule is explicitly set to 8%, rounded to cents with `HALF_UP`.

## Screenshots and logs

For failed scenario steps, the hooks attach a screenshot to the Cucumber scenario. The configured Allure adapter includes these attachments in its report.

Attach text logs with:

```java
scenario.attach("Log message", "text/plain", "Info Log");
```

## GitHub Actions

The workflow in `.github/workflows/automation.yml` runs the full suite in headless Chrome for pushes and pull requests targeting `main`. It generates an Allure report and uploads `Resources/Reports/` and `target/surefire-reports/` as the `test-reports` artifact, including when tests fail. Test failures cause the workflow to fail after report collection.

For pushes to `main` in a public repository, the workflow also attempts to publish the Allure report to GitHub Pages. Configure the repository's Pages source as GitHub Actions and allow deployment through the `github-pages` environment.

Generated reports, `target/`, and the downloaded `.allure/` installation are ignored by Git.
