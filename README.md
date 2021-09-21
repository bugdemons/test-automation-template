#Test Automation Template
### This project should contain 3 main parts:
* UI - for UI tests,
* REST - for rest tests,
* Utils - utils which will be used in both - UI and REST module.

### Used plugins/tools
#### - maven-checkstyle-plugin
This plugin generates a report regarding the code style used by developers.
Plugin page: https://maven.apache.org/plugins/maven-checkstyle-plugin/index.html
Pom configuration documentation: https://maven.apache.org/plugins/maven-checkstyle-plugin/check-mojo.html

This plugin use xml file with information about which code fragments and how should check your code.
Checkstyle file configuration documentation: https://checkstyle.sourceforge.io/config.html#Checker
Standard checks: https://checkstyle.sourceforge.io/checks.html