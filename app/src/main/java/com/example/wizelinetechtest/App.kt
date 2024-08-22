package com.example.wizelinetechtest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * This class is annotated with @HiltAndroidApp, which triggers Hilt's code generation
 * and serves as the app-level dependency container. By extending `Application`,
 * it allows Hilt to manage dependency injection throughout the entire application lifecycle.
 */
@HiltAndroidApp
class App : Application() {
}