#
# Build image
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY files/settings.xml /usr/share/maven/ref/
COPY . /usr/project
COPY pom.xml /usr/project
RUN mvn -B -f /usr/project/pom.xml -s /usr/share/maven/ref/settings.xml clean install -DskipTests -Dcheckstyle.skip
CMD ["mvn", "-f","/usr/project/pom.xml", "test", "-s","/usr/share/maven/ref/settings.xml","test","-Dcodegen.skip",  "-Dselenide.grid=true", "-Dselenide.grid.url=http://selenide:4444/wd/hub"]