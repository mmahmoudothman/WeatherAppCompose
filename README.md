# 🌤️ Weather Forecast App

A simple Android app that allows users to enter a city name and view the 5-day weather forecast using the [OpenWeatherMap](https://openweathermap.org/) API.

## 📱 Features

* 🔍 Search by city name
* 📋 Display list of weather forecasts (temperature & weather status)
* 📄 Detailed view with:

  * Temperature
  * Feels like
  * Weather
  * Weather description

## 🔗 API

* **Endpoint:**
  `https://api.openweathermap.org/data/2.5/forecast?q={city}&appid={API_KEY}`
* **API Key Used:**
  ``

## 🛠️ Tech Stack

* Language: **Kotlin**
* Architecture: **MVI**
* UI: **Jetpack Compose**
* Dependency Injection: **Hilt**
* HTTP Client: **Retrofit**
* State Handling: **StateFlow**

## 🧪 Testing

* Unit tests with `JUnit` & `MockK`
* Tested ViewModel business logic and state changes

## 📸 Screens

| Search Screen                     | Forecast List                 | Detail View                       |
| --------------------------------- | ----------------------------- | --------------------------------- |
| ![Search](https://github.com/user-attachments/assets/e70136dd-742d-44fb-8ba7-87b8bc0af580) | ![List](https://github.com/user-attachments/assets/54c85217-ad93-46d9-94ee-76fa214fd46c) | ![Detail](https://github.com/user-attachments/assets/818bbc7e-2cc2-411e-9ec9-f224da985dec) |



https://github.com/user-attachments/assets/f440c4df-5047-4628-bcd7-cd4111a60276




## 🚀 Getting Started

1. Clone the repo:

   ```bash
   git clone https://github.com/your-username/weather-forecast-app.git
   ```

2. Add your API key to `local.properties`:

   ```properties
   WEATHER_API_KEY=your_api_key_here
   ```

3. Build & run using Android Studio (min SDK 21)
