# Faire android-code-challenge

### Rafael da Silva Costa

This repo was made for the code challenge of the company Faire, it is a simple forecast weather client. 

#### Architecture

This app uses the MVVM pattern to separate the business logic from the user interface presentation. This means that business logic is kept in a ViewModel object, while the user interface is kept in fragment. Additionally, we use clean architecture to ensure that business logic is easily testable and maintainable.

### Development steps
1 - I created the mvvm structure, the dependency injection modules, the api client and the use case to get the api data. I also added some utils that I usually use in development
2 - I created the ForecastViewObject and the ForecastWeatherFragment fragment now displays its content.
3 - I added all consolidationWeather to a list in forecastViewObject and started to display them in a horizontal recyclerview.
4 - I added a resource provider to use the app's strings in repositories and use cases.
5 - added a lottie animation to show a loading state when the api is being requested.
6 - added the error and loading states in the ForecastWeatherFragment
7 - added test cases for WeatherUseCase and its dependencies

#### Build instructions
Just sync gradle and run, you may need to set the JRE path in android studio.
