# WeatherApp - Android Weather Application

[![Kotlin](https://img.shields.io/badge/kotlin-1.9.20-blue.svg)](https://kotlinlang.org/)
[![Android Studio](https://img.shields.io/badge/Android%20Studio-2023.1.1-green.svg)](https://developer.android.com/studio)
[![API](https://img.shields.io/badge/API-21%2B-yellow.svg)](https://developer.android.com/about/versions/android-5.0)

A modern and feature-rich weather application for Android, built with Kotlin, leveraging a clean architecture and modern Android development practices. This app provides current weather conditions, hourly forecasts, and extended forecasts for the next seven days.

<div align="center">
  <img src="https://github.com/user-attachments/assets/a8ddba2d-dab3-4590-b58b-03546cb66c5b" width="300" alt="WeatherApp-pic1" style="margin-right: 20px;">
  <img src="https://github.com/user-attachments/assets/8019fa37-cd47-4caf-9736-b8ed2ea9133b" width="300" alt="WeatherApp-pic2">
</div>


## Key Features

*   **Current Weather:** Displays current temperature, feels like temperature, conditions, rainfall, wind, and humidity for the user's current location or searched location.
*   **Hourly Forecast:** Provides detailed hourly forecasts for the current day and the following day using a RecyclerView.
*   **Seven Day Forecast:** Offers a comprehensive 7-day forecast including daily highs, rain, wind, and humidity, with expandable details for each day.
*   **Location Search:** Implements a `SearchView` with auto-complete suggestions for easy location searching and selection.
*   **Real-time Updates:** Uses Google Location Services to get device location and updates weather information in real-time.
*   **Shimmer effect:** Implementes shimmer effects during data loading for a better user experience.
*   **Clean UI/UX:**  Utilizes Material Design components, smooth animations, and is designed for a pleasant and user-friendly experience.
*   **Modern Architecture:** Built using a modern Android development approach with a lean towards MVVM principles, focusing on modularity, testability, and maintainability.
*   **Kotlin First:** Written entirely in Kotlin, leveraging its concise syntax, null safety features, and modern language constructs.
*   **Third-Party Libraries:** Uses popular libraries such as Retrofit for network requests, Google Location Services for location updates, and Material Design components.

## Architecture and Technologies

This app is built using the following technologies and architectural patterns:

*   **Kotlin:** Primary language for the project.
*   **Retrofit:** Used for efficient and clean API calls.
*   **Google Location Services:** Provides accurate device location and location updates.
*   **Material Design Components:** Used to create a consistent and visually appealing user interface.
*   **View Binding:** For safe and efficient view access.
*   **ViewPager2 & TabLayout:** For navigating between the "Today" and "Tomorrow" forecast tabs.
*   **RecyclerView:** Used for displaying hourly forecast and the 7-day forecast.
*    **Shimmer:** Used for providing a shimmer effect during API calls.

## Code Highlights

Here are some of the key implementation details:

*   **MainActivity.kt**:
    *   Handles the overall app flow, including location updates, data loading, tab navigation, and the search functionality.
    *   Utilizes `SearchView` for location searching with autocomplete suggestions.
    *   Manages `TodayFragment`, `TomorrowFragment`, and `SearchResultFragment` with proper visibility changes, animations and transitions.
*   **NextSevenForecast.kt:**
    *   Displays an extended 7-day forecast in a `RecyclerView` with expandable detail views.
*   **HourAdapter.kt:**
    *   Handles the presentation of hourly forecast data in a horizontal `RecyclerView`.
    *   Properly formats temperatures with a superscript degree symbol and relative size spans.
*   **NextSevenAdapter.kt:**
    *   Manages the display of the 7-day forecast, with clickable cards to expand and show more information.
    *   Implements smooth animations using `LayoutTransition` for an improved user experience.
*   **LocationManager.kt:**
    *   A singleton class for managing location permission and retrieving location updates with the Google location services.
    *   Provides callbacks to update the UI based on the location updates.
*   **RetrofitClient.kt:**
    *   A singleton class for handling Retrofit API calls, to provide a single instance for all API calls.
*   **WeatherAPI.kt:**
    *   Interface that defines all endpoints from the Weather API
*   **Fragments:**
    *   `TodayFragment` and `TomorrowFragment` display the hourly forecasts for the current and next day.
    *   `SearchResultFragment` shows location results from the search API.
*   **Data classes:**
    *   All API responses are mapped to data classes such as `WeatherResponse`, `LocationSearchResult`, `HourForecast`, and `ForecastDay`.


## How to Run

1.  Clone the repository: `git clone [repository-url]`
2.  Open the project in Android Studio.
3.  Build and run the app on an emulator or a physical device.

