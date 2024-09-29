# E-Commerce Application

A simple e-commerce app built using Kotlin and Android Jetpack components for managing online product listings, shopping cart functionality, and user authentication.

## Description

This project is an Android e-commerce application designed to allow users to browse products, add them to a shopping cart, and proceed to checkout. The app is built using the MVVM architecture, leveraging Firebase for backend services like user authentication and data storage. It provides a streamlined shopping experience, demonstrating modern Android development practices.

## Getting Started

### Dependencies

* Android Studio
* Kotlin 1.4+
* Android SDK 21+
* Firebase Authentication and Firestore
* Google Play Services (for google-services.json)
* Internet connection (for Firebase services)

### Installing

* Clone the repository:
 ```
git clone https://github.com/your-username/e-commerce-app.git
```
*Open the project in Android Studio.
*Set up Firebase in your app:
*Create a Firebase project and add an Android app to the project.
*Download the google-services.json file and place it in the app directory.
*Build and run the project in an emulator or on a physical device.

### Executing program

*To run the program, follow these steps:
*Open the project in Android Studio.
*Sync the project with Gradle to download dependencies.
*Run the app on an emulator or connected device
```
./gradlew installDebug
```
*The app will launch with the main product listing screen.

## Help

If you encounter any issues with Firebase setup, refer to the official Firebase Documentation. For common problems, try the following command to check for build issues:
```
./gradlew build
```

## Authors

Amber Raj Saxena
amber24march@gmail.com

## Version History

*0.2
  *Bug fixes and performance optimizations
  *Improved checkout flow
*0.1
  *Initial release with user authentication, product listings, and shopping cart

## Acknowledgments

Inspiration, code snippets, etc.
* [Android Jetpack Components](https://developer.android.com/jetpack)
* [Firebase Documentation](https://firebase.google.com/docs)
