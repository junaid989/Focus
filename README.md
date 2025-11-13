Focus Gradle Wrapper v8.10 package
=================================

Files provided:
- gradlew
- gradlew.bat
- gradle/wrapper/gradle-wrapper.properties

Note: gradle-wrapper.jar (binary) is NOT included in this package.
If you want to include gradle-wrapper.jar manually, download it from:
https://raw.githubusercontent.com/gradle/gradle/v8.10/subprojects/wrapper/src/main/resources/gradle/wrapper/gradle-wrapper.jar

If you prefer not to add the JAR manually, ensure your GitHub Actions workflow contains a step
to download the gradle-wrapper.jar before running ./gradlew. Example step (bash):

  mkdir -p gradle/wrapper
  curl -L -o gradle/wrapper/gradle-wrapper.jar \
    https://raw.githubusercontent.com/gradle/gradle/v8.10/subprojects/wrapper/src/main/resources/gradle/wrapper/gradle-wrapper.jar

This package is named Focus-Gradle-Wrapper-v8.10.zip
