# MVP: Texsydo Web

## Running the App

Run `gradlew run` to run the CLI app.

Run with arguments: `gradlew run --args="{ args }"`.

See
[Automating the Platform Operations and Beyond (2023/08/31)](https://blog.mathsoftware.engineer/automating-the-platform-operations-and-beyond-2023-08-31)
for the original prototype commands and documentation.

## Testing

Run `gradlew test` to run all tests. It doesn't log testing information if all
tests passed.

## Deploying

Run `gradlew jpackage` to create a debian installer.

It will fail to build if the version name in `build.gradle.kts` has a hyphen,
like `0.1.0-dev`, so this build command is meant for releases.

The packaging configuration is at [jpackage](jpackage).

The package will be at [build/jpackage](build/jpackage) with the `.deb` and
`rpm` installers of the CLI app `tsd-web---prototype`.

## Project

### Updating Gradle

Run `gradlew wrapper --gradle-version latest` to set the Gradle version to the
latest in `gradle-wrapper.properties`.
