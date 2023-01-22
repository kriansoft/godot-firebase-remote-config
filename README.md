# Firebase Remote Config plugin for Godot

## Installation

1. Copy `GodotFirebaseRemoteConfig.gdap` file from project root into your Godot app's `./android/plugins` folder.
2. Build this project by running `./gradlew build`.
3. Copy `./app/build/outputs/aar/GodotFirebaseRemoteConfig.release.aar` file into your Godot app's `./android/plugins` folder.
4. Copy your `google-services.json` file into the `./android/build` folder within your Godot app (can be downloaded from Firebase Console)
5. Edit the file `./android/build/build.gradle` within your Godot app as follows:
   1. Add the following line in `buildscript` --> `dependencies` block (right before the `//CHUNK_BUILDSCRIPT_DEPENDENCIES_BEGIN` line): `classpath 'com.google.gms:google-services:4.3.15'`
   2. Add the following line at the bottom of the file (right before the `//CHUNK_GLOBAL_BEGIN` line): `apply plugin: 'com.google.gms.google-services'`

