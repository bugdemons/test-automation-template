# Test Automation Template

### This project should contain 3 main parts:

* UI - for UI tests,
* REST - for rest tests,
* Utils - utils which will be used in both - UI and REST module.

### Used plugins/tools

#### - maven-checkstyle-plugin

This plugin generates a report regarding the code style used by developers. Plugin
page: https://maven.apache.org/plugins/maven-checkstyle-plugin/index.html
Pom configuration documentation: https://maven.apache.org/plugins/maven-checkstyle-plugin/check-mojo.html

This plugin use xml file with information about which code fragments and how should check your code. Checkstyle file
configuration documentation: https://checkstyle.sourceforge.io/config.html#Checker
Standard checks: https://checkstyle.sourceforge.io/checks.html

## Usage

### - Init Selenide browser config

To init Selenide configuration it is necessary to use `BrowserConfig.initBrowser()` method before tests execution.

### - Setting desired browser

There are two ways to set desired browser:

1. set `selenide.browser=desired_browser` property
   in [configuration.properties](src/main/resources/configuration/configuration.properties)
2. use `-Dbrowser=desired_browser` property

There are three available browsers:

1. Chrome (param value: CHROME)
2. Firefox (param value: FIREFOX)
3. Internet Explorer (param value: IE)

### - Running tests on grid

There are two ways to configure Selenide to run tests on Selenium Grid:

1. set `selenide.remote=grid_url` property
   in [configuration.properties](src/main/resources/configuration/configuration.properties)
2. use `-Dgrid` property and set `hub.url=desired_hub_url` property
   in [configuration.properties](src/main/resources/configuration/configuration.properties)

### - Creating custom Selenide commands

To optimize some steps it is possible to create custom commands (methods).
See [example](https://github.com/selenide/selenide/tree/master/statics/src/test/java/integration/customcommands).

Basic implementation with two commands:

- [CustomSelenideCommands](src/main/java/pl/bugdemons/ui/selenide/CustomSelenideCommands.java)
- [commands](src/main/java/pl/bugdemons/ui/selenide/customcommand)