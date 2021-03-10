# PET DEMO STORE API CHECKS

Test runner with Serenity and Gradle

## Getting Started

### Prerequisites

Software to clone this repo (e.g. Git installed locally, SourceTree ...)
Java 8

### Installing

Clone this repo
Import as gradle project

## Running the tests 

1. Now run ./gradlew :build

NOTES: The best way to assert that the record has been created is to assert that it can be pulled with a get.
In this case that check was not possible because the ID parameter is compromised.