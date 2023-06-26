# EventDiscovery

Android app written in Kotlin. This app uses Restful api call to fetch evenit detilas form an Disvoery API, once events information is receveied it stores its details in Room databse. App has a single screen which have 2 UI components
1. Edit Text - to search event
2. RecylerView - to show event(s) details

Android Components/Libraries used
1. MVVM design pattern
2. Room database
3. Retrofit
4. Glide - to show images
5. Dagger-Hilt for dependency injection
6. Coroutimes
7. JUnit, expresso - for UI/Unit testing


Improvements (TODO)
1. Add more unit tests
2. Delete event details from database after certain time to fetch new event details (using WorkManger)
3. UI enhancement
