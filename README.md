# EventDiscovery

Android app written in Kotlin. This app uses Restful api call to fetch evenit detilas form an Disvoery API, once events information is receveied it stores its details in Room databse. App has a single screen which have 2 UI components
1. Edit Text - to search event
2. RecylerView - to show event(s) details

Android Components/Libraries used
1. MVVM design pattern
   Data Binding: MVVM often utilizes data binding libraries like Android Data Binding or Jetpack's ViewBinding to establish a connection between the View and ViewModel, allowing automatic updating of the UI based on data changes.

Observable Properties or LiveData: The ViewModel exposes observable properties or LiveData objects to the View. This enables the View to observe changes in the data and automatically update itself.

Testability: MVVM promotes testability by separating the business logic from the UI. Unit tests can be written for the ViewModel to ensure that the logic and data transformations are working correctly.
3. Room database - to store event data
4. Retrofit - s a popular open-source library for Android app development that simplifies the process of making HTTP requests and handling network communication. It is widely used for building RESTful APIs and retrieving data from web services.
5. Glide - to load images
6. Dagger-Hilt for dependency injection
7. Coroutines - you can write asynchronous code in a sequential and more readable manner. Coroutines use suspending functions, which can be paused and resumed, allowing you to write code that looks like sequential execution even though it's asynchronous.
8. JUnit, expresso - for UI/Unit testing


Improvements (TODO)
1. Add more unit tests
2. Delete event details from database after certain time to fetch new event details (using WorkManger)
3. UI enhancement
