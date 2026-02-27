# TapeFive Looper v3

A scratch looper application for Android, created as part of an Advanced Programming for Mobile Devices university module. This app brings beat-triggered looping capabilities to mobile devices, inspired by flash looper programs built in SwishMax 4.

## About the App

TapeFive Looper allows you to trigger beats by clicking on icons, with each beat's BPM displayed on screen when selected. A session timer tracks the duration of your scratch sessions, with a convenient stop button to halt playback.

Clicking the TapeFive logo directs you to the developer's Soundcloud, which contains additional looper beats and DJ mixes.

## Features

### TapeFive Looper
- Trigger beats with icon-based controls
- Real-time BPM display for each beat
- Session timer to monitor recording duration
- Quick access to developer's Soundcloud
- Tempo adjustment buttons for dynamic tempo control (-50%, -25%, +25%, +50%)

### Cutfast Looper
- Alternative looper interface with redesigned UI
- 6 additional looped beats
- Same core functionality with updated design
- Full tempo control support

## Interface Improvements (v3)

- **Responsive Design** - Optimized layouts with ConstraintLayout guidelines for all screen sizes
- **Enhanced Tempo Control** - Quick adjustment buttons for real-time tempo manipulation
- **Modern Android Support** - Full compatibility with Android 15 (API 35)
- **Main Menu** - Select between TapeFive Looper or Cutfast Looper modes
- **Top Controls** - Stop button (top left) and timer (top right)
- **Auto Stop** - Beats stop when back button is pressed
- **Dark Mode Support** - Automatic dark theme for compatible Android devices

## Requirements

- Android SDK 21 (minSdkVersion) or higher
- Target SDK 35
- Compile SDK 35
- Java 17 or higher
- Gradle 8.1 or higher

## Building

See [SETUP.md](SETUP.md) for detailed build and setup instructions.

```bash
./gradlew build
```

## Installing

```bash
./gradlew installDebug
```

## Project Structure

- `app/` - Main Android application module
- `app/src/main/` - Source code and resources
- `app/src/test/` - Unit tests
- `app/src/androidTest/` - Android instrumentation tests

## Author

Sum Boy Development

## License

All rights reserved.
