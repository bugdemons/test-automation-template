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
3. Microsoft Edge (param value: EDGE)

### - Running tests on grid (selenoid)

There are two ways to configure Selenide to run tests on Selenium Grid:

1. set `selenide.remote=grid_url` property
   in [configuration.properties](src/main/resources/configuration/configuration.properties)
2. use `-Dselenide.grid` property and set `selenide.grid.url=desired_hub_url` property
   in [configuration.properties](src/main/resources/configuration/configuration.properties)

#### - Running selenoid

WARN! be sure ports 8080 and 4444 are available on your machine (You do not use any other services on those ports)
If they are not then change mapping accordingly in [docker-compose.yaml](docker-compose.yml) to some free ports.

If you have docker installed you can use `docker-compose up` to configure selenoid with newest (latest) version of
chrome. Then you can set `selenide.grid.url=http://localhost:4444/wd/hub` to launch test on chrome in docker. Sessions
will be available by default under `http://localhost:8080/#/`.

To enable other browser add desired image in [docker-compose.yaml](docker-compose.yml). Please follow list:
[images](https://aerokube.com/images/latest/)

Additional configuration can be made via [browsers.json](files/browsers.json). For instructions looks
[here](https://aerokube.com/selenoid/latest/#_browsers_configuration_file)

### - Creating custom Selenide commands

To optimize some steps it is possible to create custom commands (methods).
See [example](https://github.com/selenide/selenide/tree/master/statics/src/test/java/integration/customcommands).

Basic implementation with two commands:

- [CustomSelenideCommands](src/main/java/pl/bugdemons/ui/selenide/CustomSelenideCommands.java)
- [commands](src/main/java/pl/bugdemons/ui/selenide/customcommand)

## - Docker

WARNING! currently

There are two ways to build your own docker image:

### - Easier - Not all dependencies pre-cached

In CLI in project's root type: `docker build . --rm --no-cache -t image-name:latest`

After that your image will be ready. to use it type: `docker run --rm --name container-name image-name`

It will download missing part of dependencies.

### - Harder -fully pre-cached dependencies

1. Build project with custom settings `mvn clean install -Dmaven.repo.local=temp\deps`
2. Swap files' names `Dockerfile` with `DockerFileAlt`
3. In CLI in project's root type: `docker build . --rm --no-cache -t image-name:latest`
4. After that your image will be ready. to use it type: `docker run --rm --name container-name image-name`

In this case tests will be run with `-o` parameter (offline mode) and no dependencies will be downloaded.


