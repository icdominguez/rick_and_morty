# Rick and morty APP

This is an app build using Kotlin that consumes [Rick and Morty API] (https://rickandmortyapi.com) to display a gallery of characters, locations and episodes. 
It has been done following the Clean Architecture Principle, Multi-modularized and with MVI Architecture in the presentation layer.


## Table Of Content.

- [Demo](#demo)
- [Prerequisite](#prerequisite)
- [Architecture](#architecture)
  - [What is Clean Architecture](##why-clean-architecture)
  - [Why Clean Architecture](##why-clean-architecture)
  - [Modules](##layers)
- [Teck Stack](#techstack)
  - [Libraries](##libraries)
- [Helpful Resources](#helpful-resources)
  
## Demo
These are the app screenshots and video:

<h5> Note: The App is still in progress and only the characters module is fully done</h5>

<table align="center">
	<tr>
		<td><img src="https://github.com/icordondominguez/rick_and_morty/assets/56835908/ea4dc591-7d0e-4e15-b911-d96077ee3abd" width="350" title="hover text"></td>
		<td><img src="https://github.com/icordondominguez/rick_and_morty/assets/56835908/8e8e4545-18cd-4882-8fe3-5e29fc86873b" width="350" title="hover text"></td>
	</tr>
	<tr>
		<td><img src="https://github.com/icordondominguez/rick_and_morty/assets/56835908/9be493a5-4a7f-492d-8ef3-17fbe906c2cf" width="350" title="hover text"></td>
		<td><img src="https://github.com/icordondominguez/rick_and_morty/assets/56835908/ca5b8dea-9305-4bcc-b8d5-cecff0255a12" width="350" title="hover text"></td>
	</tr>
  <tr>
  	<td><img src="https://github.com/icordondominguez/rick_and_morty/assets/56835908/c3b0ed4c-a27f-4bab-b9b8-edfbfe75ea89" width="350" title="hover text"></td>
  	<td><img src="https://github.com/icordondominguez/rick_and_morty/assets/56835908/4347eb6a-ab7b-46a5-9e2a-100f73373402" width="350" title="hover text"></td>
  </tr>
</table>

[Screen_recording_20230914_164932.webm](https://github.com/icordondominguez/rick_and_morty/assets/56835908/d9eea4fe-1a02-42f4-b2bb-9979b3b96a4b)

## Prerequisite
<p>In order to be able to build the application you'll need Android Studio Minimum version Android Studio Giraffe May Build</p>
<p>Java version 17 jdk is used</p>

## Architecture.

### What is Clean Architecture?

A well planned architecture is extremely important for an app to scale and all architectures have one common goal- to manage complexity of your app. This isn't something to be worried about in smaller apps however it may prove very useful when working on apps with longer development lifecycle and a bigger team.

Clean architecture was proposed by [Robert C. Martin](https://en.wikipedia.org/wiki/Robert_C._Martin) in 2012 in the [Clean Code Blog](http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) and it follow the SOLID principle.
![architecturecircles](https://github.com/icordondominguez/rick_and_morty/assets/56835908/2db28bfb-96a9-444b-8ffe-6b5fb5e3b82e)

The circles represent different layers of your app. Note that:

- The center circle is the most abstract, and the outer circle is the most concrete. This is called the [Abstraction Principle](https://en.wikipedia.org/wiki/Abstraction_principle_(computer_programming)). The Abstraction Principle specifies that inner circles should contain business logic, and outer circles should contain implementation details.

- Another principle of Clean Architecture is the [Dependency Inversion](https://en.wikipedia.org/wiki/Dependency_inversion_principle). This rule specifies that each circle can depend only on the nearest inward circle ie. low-level modules do not depend on high-level modules but the other way around.

### Why Clean Architecture?
- Loose coupling between the code - The code can easily be modified without affecting any or a large part of the app's codebase thus easier to scale the application later on.
- Easier to test code.
- Separation of Concern - Different modules have specific responsibilities making it easier for modification and maintenance.
### S.O.L.I.D Principles.

- [__Single Responsibility__](https://en.wikipedia.org/wiki/Single-responsibility_principle): Each software component should have only one reason to change – one responsibility.

- [__Open-Closed__](https://en.wikipedia.org/wiki/Open%E2%80%93closed_principle#:~:text=In%20object%2Doriented%20programming%2C%20the,without%20modifying%20its%20source%20code.): You should be able to extend the behavior of a component, without breaking its usage, or modifying its extensions.

- [__Liskov Substitution__](https://en.wikipedia.org/wiki/Liskov_substitution_principle): If you have a class of one type, and any subclasses of that class, you should be able to represent the base class usage with the subclass, without breaking the app.

- [__Interface Segregation__](https://en.wikipedia.org/wiki/Interface_segregation_principle): It’s better to have many smaller interfaces than a large one, to prevent the class from implementing the methods that it doesn’t need.

- [__Dependency Inversion__](https://en.wikipedia.org/wiki/Dependency_inversion_principle): Components should depend on abstractions rather than concrete implementations. Also higher level modules shouldn’t depend on lower level modules.

### Modules
![image](https://github.com/icordondominguez/rick_and_morty/assets/56835908/d58d7eaf-71ca-4b18-92a0-a462761fbe73)

# Tech Stack

## Libraries
- [Hilt](https://dagger.dev/hilt/) - Dependency Injection library.
- [Compose Navigation](https://developer.android.com/jetpack/compose/navigation) -Component that allows easier implementation of navigation from composables.
- [Retrofit](https://square.github.io/retrofit/) - Type-safe http client 
and supports coroutines out of the box.  

- [OkHttp-Logging-Interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - Logs HTTP request and response data.
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines.
- [Flow](https://developer.android.com/kotlin/flow) - Flows are built on top of coroutines and can provide multiple values. A flow is conceptually a stream of data that can be computed asynchronously.
- [Material3](https://m3.material.io/get-started) - New way of designing apps with Material 3, it's absolutely more easy to implement, it has support to dark and light themes

  - [MockWebserver](https://github.com/square/okhttp/tree/master/mockwebserver) - A scriptable web
server for testing HTTP clients.
  - [Junit](https://junit.org/junit4/) - JUnit is a simple framework
to write repeatable tests
  - [Hamcrest](http://hamcrest.org/) - Matchers that can be combined to
create flexible expressions of intent
  - [Coroutines Testing](https://developer.android.com/kotlin/coroutines/test) - Test utilities for
kotlinx.coroutines.

##Helpful resources

1. [Now in android repository](https://github.com/android/nowinandroid)
2. [Guide to Android app modularization](https://www.youtube.com/watch?v=16SwTvzDO0A&ab_channel=AndroidDevelopers)
