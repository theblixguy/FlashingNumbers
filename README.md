# FlashingNumbers

This is an app I built as part of the interview process for a certain company. This app is written purely in **Kotlin** and uses **Dagger 2** for dependency injection, **Android Architecture Components** to manage ViewModel lifecycle, and **KotlinTest** for unit testing. The app built with *MVVM* plus **Data Binding**.

## Main requirements

* Create an app that shows six numbers (fields) on screen, laid out in two columns.
* Below these fields, a seventh field should show the sum of these six numbers.
* The user can tap on any of the six numbers and edit the number.
* Editing the number should cause the sum to be updated.
* Tapping on the sum should toggle flashing of this field, defined as follows: when flashing, the value should be shown for 500ms, then hidden for 500ms, repeated indefinitely; when not flashing, the value should be visible.
* Please include unit tests.

## Bonus requirements

* Demonstrate at least two different ways of implementing the flashing total, including at least one method not using RxJava.
* Use Dependency Injection to create the ViewModel for the Activity.