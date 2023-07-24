# EventDiscovery

<p>Single screen app written in Kotlin</p>

<p>
<ul><b>This app showcase capabilities of follwoing android components/libraries used</b>
<li>MVVM design pattern
<li>Observable Properties or LiveData: The ViewModel exposes observable properties or LiveData objects to the View. This enables the View to observe changes in the data and automatically update itself.</li>

<li>Testability: MVVM promotes testability by separating the business logic from the UI. Unit tests can be written for the ViewModel to ensure that the logic and data transformations are working correctly.</li></ul>
<li>Room database - to store event data</li>
<li>Retrofit - s a popular open-source library for Android app development that simplifies the process of making HTTP requests and handling network communication. It is widely used for building RESTful APIs and retrieving data from web services.</li>
<li>Glide - to load images</li>
<li>Dagger-Hilt for dependency injection</li>
<li>Coroutines - you can write asynchronous code in a sequential and more readable manner. Coroutines use suspending functions, which can be paused and resumed, allowing you to write code that looks like sequential execution even though it's asynchronous.</li>
<li>JUnit, expresso - for UI/Unit testing</li></ul>
</p>
<br>
<p>
<ol>Improvements (TODO)
<li>API returns lots of information regarding events, create a user journey to show all information</li>
<li>Add more unit tests</li>
<li>Delete event details from database after certain time to fetch new event details (using WorkManger)</li>
<li>UI enhancement</li></ol>
</p>
