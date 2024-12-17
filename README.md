# Tastecode - A Modern Recipe Application

Tastecode is a cutting-edge recipe app developed using Android Jetpack Compose, Firebase Firestore, and Room Database. It simplifies recipe management, storage, and retrieval while ensuring reliability with Firebase Crashlytics for bug tracking. This document provides an overview of the app, the required technical specifications, and step-by-step instructions to set up and deploy the project.

---

## Features

- **Modern UI**: Built entirely with Jetpack Compose for a seamless and elegant user experience.
- **Recipe Inventory**: Search Recipes realtimewith keywords from the home screen.
- **Filtering**: Filter Recipes based on Category, Difficulty, Serving Size, Rating and Cooking time. Multiple filters can work at a time. 
- **Offline Support**: Uses Room Database for personalized Data Storage and Offline access to Favourite Recipes.
- **Profile** : A beautiful profile screen for display all the informations with editing option.
- **Real-time Updates**: Synchronize recipes in real-time across devices with Firebase Firestore.
- **Bug Tracking**: Integrated Firebase Crashlytics for monitoring and debugging.
- **Authentication**: User authentication via Firebase Authentication. Verification of user email address.

---

## Technical Specifications

- **Language**: Kotlin
- **UI Framework**: Android Jetpack Compose
- **Database**: Firebase Firestore and Room Database
- **Multithreading** : Courotines,LaunchedEffect,LiveData.
- **Bug Tracking**: Firebase Crashlytics.
- **Testing**: Android Profiler, Data Analyzer from Android Studio, Mannual Testing, Regression Testing.
- **Animation** : Lottie.
- **Minimum SDK**: 28 (Android 9.0 Pie)
- **Build System**: Gradle (build.kts)

---

## Prerequisites

Before setting up the project, ensure the following:

1. **Android Studio**: Download and install [Android Studio](https://developer.android.com/studio) (preferably the latest stable version).
2. **Firebase Project**:
   - Set up a Firebase project in the [Firebase Console](https://console.firebase.google.com/).
   - Enable Firestore Database.
   - Download the `google-services.json` file and place it in the `app/` directory of the project.
   - Enable Firebase Crashlytics.
3. **Environment**:
   - Kotlin 1.8+
   - Gradle 7.0+

---

## Installation Guide

### Step 1: Clone the Repository

```bash
git clone https://github.com/your-username/tastecode.git
cd tastecode
```

### Step 2: Open in Android Studio

1. Launch Android Studio.
2. Select **File > Open** and navigate to the `tastecode` directory.
3. Sync the project with Gradle by clicking **Sync Now** when prompted.

### Step 3: Configure Firebase

1. Place the `google-services.json` file in the `app/` directory.
2. Add Firebase dependencies to the `build.gradle` files if not already present (these are preconfigured in the repository):

#### In `project-level build.gradle`:
```gradle
classpath 'com.google.gms:google-services:4.3.15'
```

#### In `app-level build.gradle`:
```gradle
apply plugin: 'com.google.gms.google-services'

dependencies {
    implementation 'com.google.firebase:firebase-firestore-ktx:24.5.0'
    implementation 'com.google.firebase:firebase-crashlytics-ktx:18.4.2'
}
```

3. Enable Firestore and Crashlytics in the Firebase Console for your project.

### Step 4: Build and Run

1. Select the **Run > Run 'app'** option in Android Studio.
2. Connect your device or start an emulator.
3. Verify that the app builds and launches successfully.

### Step 5: Firebase Crashlytics Setup

1. Enable Crashlytics in the Firebase Console.
2. Trigger a test crash using the following code snippet in your app:

```kotlin
FirebaseCrashlytics.getInstance().log("Test crash log")
FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
throw RuntimeException("Test Crash")
```

3. Check the Firebase Console for the logged crash report.

### Step 6: Room Database

1. The app uses Room Database for offline storage.
2. Room is preconfigured, and migration scripts are automatically generated for schema updates.

---

## File Structure

```plaintext
|-- app/
|   |-- src/
|   |-- main/
|       |-- java/
|           |-- com.example.tastecode/
|               |-- ui/  # Jetpack Compose UI Components
|               |-- data/  # Room Entities, DAO, and Firestore Integration
|               |-- repository/  # Data Repositories
|               |-- viewmodel/  # ViewModels for UI
|       |-- res/  # Resources (XML, Drawables, etc.)
|   |-- google-services.json  # Firebase Configuration
|-- build.gradle  # Project Configuration
```

---

## Troubleshooting

### Common Issues

1. **Gradle Sync Failure**:
   - Ensure all dependencies are updated in `build.gradle`.
   - Check your internet connection.

2. **Firebase Connection Issues**:
   - Verify the `google-services.json` file is correctly placed.
   - Confirm that the Firebase project is properly set up.

3. **Crashlytics Not Logging**:
   - Check if Crashlytics is enabled in the Firebase Console.
   - Ensure the app is in debug mode to trigger test crashes.

---

## Contribution

We welcome contributions! To contribute:

1. Fork the repository.
2. Create a feature branch:
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes:
   ```bash
   git commit -m "Feature: Add new functionality"
   ```
4. Push to your fork:
   ```bash
   git push origin feature-name
   ```
5. Submit a pull request.

---

## License

This project is licensed under the [MIT License](LICENSE).

---

## Contact

For queries or support, contact us at [support@tastecodeapp.com](mailto:support@tastecodeapp.com).

