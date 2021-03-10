# Demoblaze: FE automation for the Adidas Challenge

## Solution used

Serenoty BDD + Graddle

## About Serenity

Serenity BDD is a library that makes it easier to write high quality automated acceptance tests, with powerful reporting and living documentation features. It has strong support for both web testing with Selenium, and API testing using RestAssured.

Serenity strongly encourages good test automation design, and supports several design patterns, including classic Page Objects, the newer Lean Page Objects/ Action Classes approach, and the more sophisticated and flexible Screenplay pattern.

### Prerequisites

Software to clone this repo (Git, Sourcetree)
Java 8

### Installing

Clone this repo
Import as gradle project

## Running the tests

1. Open terminal and navigate to the demoblaze-automation root folder
2. Run './gradlew :build'

## Reports

The execution report can be found in /target/site/serenity

## TO DOs:

1. Code refactor
All actions called from SearchStepDefinition should be modified to be called as Performable instead of void

2. Optimization of XPaths
Search by text() should be used as little as possible. Instead the elements should be defined by other more constant parameters in the XPath (id, class e.t.c)

3. If possible optimize the wait and click in code with implicitlywait and fluentwait

