JAVA_FILES=$(shell find AsteroidsLibGDX -type f -name "*.java")
PACKAGE=bin/AsteroidsLibGDX-1.0-SNAPSHOT.jar

install: AsteroidsLibGDX/pom.xml
	mvn install -f AsteroidsLibGDX/pom.xml

test: ${JAVA_FILES}
	mvn test -f AsteroidsLibGDX/pom.xml

${PACKAGE}:
	mvn package -f AsteroidsLibGDX/pom.xml

package: ${PACKAGE}

run: ${PACKAGE}
	java -jar ${PACKAGE}

.DEFAULT:run
