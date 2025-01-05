# WeatherApp - Modern Android Weather Application

[![Kotlin](https://img.shields.io/badge/kotlin-1.9.20-blue.svg)](https://kotlinlang.org/)
[![Android Studio](https://img.shields.io/badge/Android%20Studio-2023.1.1-green.svg)](https://developer.android.com/studio)
[![Node.js](https://img.shields.io/badge/Node.js-18.x-yellowgreen.svg)](https://nodejs.org/)
[![Express.js](https://img.shields.io/badge/Express.js-4.x-blueviolet.svg)](https://expressjs.com/)
[![API](https://img.shields.io/badge/API-21%2B-yellow.svg)](https://developer.android.com/about/versions/android-5.0)

A feature-rich and meticulously crafted weather application for Android, built with Kotlin and a Node.js backend. This app offers real-time weather updates, detailed hourly forecasts, and extended 7-day forecasts, utilizing modern development practices.

<div align="center">
  <img src="https://github.com/user-attachments/assets/a8ddba2d-dab3-4590-b58b-03546cb66c5b" width="300" alt="WeatherApp-pic1" style="margin-right: 20px;">
  <img src="https://github.com/user-attachments/assets/8019fa37-cd47-4caf-9736-b8ed2ea9133b" width="300" alt="WeatherApp-pic2">
</div>

## ✨ Key Features

*   **Current Weather:** Displays real-time temperature, feels like temperature, weather conditions, rainfall, wind, and humidity for the user's location or searched cities.
*   **Hourly Forecasts:** Provides detailed hourly forecasts for the current day and the next day in an easy to use horizontal scrolling `RecyclerView`.
*   **7-Day Forecast:** Offers a comprehensive 7-day forecast with daily highs, rain, wind, and humidity. Expandable views to show more details for each day.
*   **Location Search:** Features a `SearchView` with auto-complete suggestions for easy and fast location selection.
*   **Background Updates:** Uses `WorkManager` to fetch weather data periodically in the background.
*   **Real-time Location Updates:** Uses Google Location Services for accurate location updates when the app is used.
*   **Shimmer Loading Effect:** Implements shimmer effects during API calls for a smooth user experience.
*   **Clean UI/UX:** Utilizes Material Design components, smooth animations, custom icons, and a clear presentation of the weather information for an engaging user experience.
*   **Modern Architecture:** Built using modern Android development with a clean MVC architecture in the backend.
*   **Kotlin First:** Written entirely in Kotlin, leveraging its concise syntax, null safety features, and modern language constructs.
*   **Node.js Backend:** Utilizes a custom-built Node.js backend with Express.js to handle API requests and data processing.
*   **Third-Party Libraries:** Uses popular libraries such as Retrofit for network requests, Google Location Services for location updates, Material Design components, and `WorkManager` for background tasks.

## ⚙️ Architecture and Technologies

This project is built using the following technologies and architectural patterns:

*   **Android (Frontend):**
    *   **Kotlin:** The primary language.
    *   **Retrofit:** For efficient and clean communication with the server.
    *   **Google Location Services:** Provides accurate device location updates.
    *   **Material Design Components:** For consistent UI/UX.
    *   **View Binding:** For safe and efficient view access.
    *   **ViewPager2 & TabLayout:** For tab navigation.
    *   **RecyclerView:** For displaying the lists.
    *    **WorkManager:** For scheduling background data fetches.
    *    **Shimmer:** For the shimmer loading animation
*   **Backend (Server):**
    *  **Node.js:** As the runtime environment for the server.
    *   **Express.js:** For building the web server and APIs.
    *   **Axios:** For making requests to the external weather API.

## 💻 Code Highlights

Here are some of the key implementation details:

*   **`frontend` Folder:**
    *   **`MainActivity.kt`:** Manages main app logic, location, and tab navigation. It's also responsible for checking for cached data and updates the UI accordingly.
    *  **`TodayFragment.kt` and `TomorrowFragment.kt`:** Responsible for displaying the hourly forecast data, and now are directly responsible for their UI and update logic.
    *   **`LocationManager.kt`:** Manages device location using Google Location Services.
    *   **`RetrofitClient.kt`:** Sets up the Retrofit client, with the local configuration for dynamic base urls.
    *   **`WeatherAPI.kt`:** Defines the interface for API calls, which are now targeting your local backend.
    *   **`WeatherWorker.kt`:** Fetches data in the background using `WorkManager` and stores the data locally.
    *   **Adapters:** `HourAdapter`, `NextSevenAdapter`, `SearchResultsAdapter`, and `ViewNavAdapter` for displaying your data using `RecyclerViews` and `ViewPagers`.
*   **`backend` Folder:**
    *   **`index.js`:** Sets up the server with express, and manages the routes.
    *   **Controllers:** Contains all the controller logic for the api calls, separating the data fetching logic from the implementation.
    *   **Models:** Contains the logic for making the external API calls.
    *   **Routes:** Define the different endpoints that will be used by your mobile application.

## 🚀 How to Run

1.  Clone the repository: `git clone [repository-url]`
2.  Open the `frontend` folder in Android Studio.
3.  Open the `backend` folder in your preferred code editor (such as VS Code or IntelliJ).
4.  Start the Node.js backend server: navigate to the `backend` folder in your terminal and run: `node index.js`
5.  Build and run the Android app from Android Studio on an emulator or a physical device. Make sure your android device or emulator is in the same network as the machine where you are running your server.

## ✅ Areas for Improvement
*   **Testing:** Adding Unit and UI tests for all areas of the application.

