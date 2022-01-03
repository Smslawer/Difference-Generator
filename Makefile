install:
	./gradlew clean	install

run-dist:
	./gradlew run

check-updates:
	./gradlew dependencyUpdates

lint:
	./gradlew checkstyleMain

.PHONY: build

build:
	./gradlew clean build

.PHONY: test

test:
	./gradlew test

test-Jacoco:
	./gradlew jacocoTestReport