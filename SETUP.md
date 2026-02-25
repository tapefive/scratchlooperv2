# Setup Guide - ScratchLooper v2

A comprehensive guide to setting up and building the ScratchLooper v2 Android application.

## Table of Contents
- [System Requirements](#system-requirements)
- [Installation](#installation)
- [Building the Project](#building-the-project)
- [Testing the Build](#testing-the-build)
- [Troubleshooting](#troubleshooting)

## System Requirements

### 1. Java Development Kit (JDK) 17

The project requires **Java 17** for Gradle 7.5 compatibility.

#### Download Java 17
- **Eclipse Adoptium (Recommended)**
  - URL: https://adoptium.net/temurin/releases/?version=17
  - Choose: LTS Release → Version 17 → Your OS

- **Oracle JDK 17**
  - URL: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html

#### Install Java 17

**Windows:**
1. Run the installer
2. Note the installation path (e.g., `C:\Program Files\Java\jdk-17`)
3. Set JAVA_HOME environment variable (see below)

**macOS:**
```bash
# Using Homebrew
brew install openjdk@17

# Or download from Eclipse Adoptium
# Follow installer instructions
```

**Linux (Ubuntu/Debian):**
```bash
sudo apt-get install openjdk-17-jdk
```

#### Set JAVA_HOME Environment Variable

**Windows (Command Prompt):**
```batch
setx JAVA_HOME "C:\path\to\jdk-17"
```

**Windows (PowerShell):**
```powershell
[Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\path\to\jdk-17", "User")
```

**macOS/Linux:**
```bash
# Add to ~/.bashrc, ~/.zshrc, or ~/.bash_profile
export JAVA_HOME=$(/usr/libexec/java_home -v 17)

# Apply changes
source ~/.bashrc  # or source ~/.zshrc
```

**Verify Installation:**
```bash
java -version
# Should show: openjdk version "17.x.x"
```

### 2. Android SDK 34

Required for compiling against Android 14 API.

#### Installation Options

**Option A: Android Studio (Recommended)**
1. Download Android Studio: https://developer.android.com/studio
2. Launch Android Studio
3. Go to: Settings → SDK Manager → SDK Platforms
4. Check "Android 14 (API 34)"
5. Click "Apply" → "OK"

**Option B: Command Line**
```bash
# Requires Android SDK Command-line Tools first
sdkmanager "platforms;android-34"
```

#### Locate Android SDK Path

**Find SDK Location:**
- Android Studio: Settings → About → SDK path
- Usually: `~/Library/Android/sdk` (macOS/Linux) or `C:\Users\<username>\AppData\Local\Android\Sdk` (Windows)

#### Set ANDROID_HOME (Optional but Recommended)

**Windows:**
```batch
setx ANDROID_HOME "C:\Users\<username>\AppData\Local\Android\Sdk"
```

**macOS/Linux:**
```bash
export ANDROID_HOME=$HOME/Library/Android/sdk
```

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/scratchlooperv2.git
cd scratchlooperv2
```

### 2. Verify Java Installation

```bash
java -version
# Expected: openjdk version "17.x.x"

javac -version
# Expected: javac 17.x.x
```

## Building the Project

The project uses **Gradle 8.1** with the **Gradle Wrapper**, so no manual Gradle installation needed!

### Clean Build

**Windows:**
```batch
gradlew.bat clean build
```

**macOS/Linux:**
```bash
./gradlew clean build
```

### Debug Build (for testing)

**Windows:**
```batch
gradlew.bat assembleDebug
```

**macOS/Linux:**
```bash
./gradlew assembleDebug
```

### Release Build

**Windows:**
```batch
gradlew.bat assembleRelease
```

**macOS/Linux:**
```bash
./gradlew assembleRelease
```

## Testing the Build

### Locate Generated APK

**Debug APK:**
```
app/build/outputs/apk/debug/app-debug.apk
```

**Release APK:**
```
app/build/outputs/apk/release/app-release-unsigned.apk
```

### Install on Android Device

```bash
# Requires Android device with USB debugging enabled
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Run Unit Tests

```bash
./gradlew test          # macOS/Linux
gradlew.bat test        # Windows
```

### View Test Reports

After running tests:
```
app/build/reports/tests/debug/index.html
```

## Build Configuration

### Project Structure
```
scratchlooperv2/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/                          # Source code
│   │       ├── res/                           # Resources
│   │       │   ├── values/                    # Light mode themes
│   │       │   └── values-night/              # Dark mode themes
│   │       └── AndroidManifest.xml
│   └── build.gradle                           # App configuration
├── gradle/
│   └── wrapper/                               # Gradle wrapper (auto-downloads Gradle)
├── build.gradle                               # Root configuration
├── gradle.properties                          # Build properties
├── settings.gradle                            # Module configuration
├── gradlew                                    # Gradle wrapper script (Unix)
├── gradlew.bat                                # Gradle wrapper script (Windows)
└── local.properties                           # Local SDK path (NOT committed)
```

### Build Versions
- **Gradle**: 8.1
- **Android Gradle Plugin (AGP)**: 8.1.0
- **compileSdkVersion**: 34
- **minSdkVersion**: 21
- **targetSdkVersion**: 34
- **Java**: 17

### Dependencies
All dependencies are automatically downloaded from `mavenCentral()`:
- AndroidX libraries (modern replacement for support libraries)
  - `androidx.appcompat:appcompat:1.6.1`
  - `androidx.constraintlayout:constraintlayout:2.1.4`
  - Additional AndroidX libraries (see app/build.gradle)
- JUnit 4.13.2 for testing
- Espresso 3.5.1 for Android testing

## Troubleshooting

### Error: "Could not find method jcenter()"
**Solution**: The project uses `mavenCentral()` instead of deprecated `jcenter()`
```gradle
repositories {
    google()
    mavenCentral()
}
```

### Error: "Unsupported class file major version 65"
**Solution**: Ensure you have Java 17 installed and JAVA_HOME is set correctly
```bash
java -version
# Should be Java 17, NOT Java 21+
```

### Error: "compileSdkVersion not specified"
**Solution**: Ensure Android SDK 34 is installed
```bash
sdkmanager "platforms;android-34"
```

### Error: "SDK location not found"
**Solution**: Create `local.properties` in project root:
```properties
sdk.dir=/path/to/Android/Sdk
```

For Android Studio SDK path, see [Locate Android SDK Path](#locate-android-sdk-path)

### Build is slow on first run
**Normal behavior**: First build downloads Gradle, dependencies, and SDKs. Subsequent builds are much faster.

### "Permission denied" on gradlew (macOS/Linux)
**Solution**: Make gradlew executable
```bash
chmod +x gradlew
./gradlew build
```

## Development Workflow

### Using Android Studio

1. Open Android Studio
2. Select: File → Open → Navigate to project root
3. Android Studio will:
   - Import Gradle project
   - Download dependencies
   - Index code
4. Click: Build → Build Project (or Ctrl+F9)

### Using Command Line (VSCode, Sublime, etc.)

```bash
# Navigate to project directory
cd scratchlooperv2

# Build
./gradlew build

# Run tests
./gradlew test

# Generate APK
./gradlew assembleDebug

# Clean build (removes all artifacts)
./gradlew clean build
```

## Project Features

- **Gradle**: 8.1 (auto-downloaded via wrapper)
- **Java 17**: Modern Java features with Java 17 language level
- **AndroidX**: Modern Android framework with latest dependencies
- **Multi-resolution Support**: Devices from API 21 to 34
- **Dark Mode**: System theme-aware UI
  - Light mode: Standard light theme colors
  - Dark mode: Dark theme optimized for low light environments
- **Sound Looping**: Audio playback with SoundPool
- **UI**: Constraint layout with custom styling

## Getting Help

### Common Issues
- Check [Troubleshooting](#troubleshooting) section above
- Review build output for specific error messages
- Ensure all prerequisites are installed correctly

### Gradle Documentation
- https://docs.gradle.org/8.1/userguide/userguide.html

### Android Development
- https://developer.android.com/docs

### Java 17 Features
- https://docs.oracle.com/en/java/javase/17/

### AndroidX Migration
- https://developer.android.com/jetpack/androidx

## Next Steps

1. ✅ Install Java 17 and Android SDK 33
2. ✅ Clone the repository
3. ✅ Run `./gradlew build`
4. ✅ Open in Android Studio or your preferred IDE
5. ✅ Start developing!

---

**Happy Building!** 🚀
