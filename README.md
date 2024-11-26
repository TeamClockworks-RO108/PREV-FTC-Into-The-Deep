## Android Studio Settings

Please use the following settings when opening the project, or else gradle might fail:

1. File -> Settings -> Build, Execution, Deployment -> Build Tools -> Gradle
   * Distribution: Wrapper
   * Gradle JDK: Select a Java 17 JDK or install one if not present. To install one, select "Download JDK" then select version 17 and vendor "Azul Zulu Community", then OK.
2. File -> Project Structure -> Project
   * Android Gradle Plugin Version: 7.2.0
   * Gradle Version: 7.4.2



Errors like `incompatible Gradle JVM` or `Unsupported major class file version 65` are the result of misconfigured above parameters.