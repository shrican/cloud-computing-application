# Csye6225-fall2017

The application implements APIs using Java programming language and Spring boot framework with MySQL as the persistent backend data store for user account management. 

## Getting Started

Below are the instructions that will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
* Java JDK 1.8
* Spring Boot
* MySql
* Gradle
* Travis CI

### Installation

* Git Clone the required repository
* Import existing code source from Github/folder
* Import Gradle configuration along with Java JDK
* Make/Update changes to build.gradle and travis.yml
* Add MySQL database Configuration
* Build Application using Travis CI 

## Instructions to run unit, integration and/or load tests

For running the test cases: 
Change the property `spring.profiles.active` in application properties to test
This will run the Junit tests on travisCI.

* .jmx for Jmeter testing
* travis.yml for travis-ci testing
* JUnits for unit testing

### List of Test Cases


```
UserAccountEndpointControllerTest 
UserAccountServiceTest 
ensuireUsernameIsUnique

```

## Deployment

We are using Travis CI along with CodeDeploy to deploy the web project on AWS EC2 instance

## Build with Travis CI

* [Travis CI](https://travis-ci.com/) - Travis CI is a hosted, distributed continuous integration service used to build and test software projects hosted at GitHub.

Steps for Travis CI build

* Login to Travis CI (using github login)
* Activate your repository
* Flick the repository switch on
* Add .travis.yml file to your repository
* Trigger your first build with a git push

Travis build link for the project : [Click here for the link](https://travis-ci.com/shrican/csye6225-fall2017.svg?token=Bz5BxQbm4vVpwaJw2HRJ&branch=assignment3)

## Authors

* **Shrikant Mudholkar** 
* **Varsha Bhanushali**
* **Rahul Chandra**
* **Manish Patil**

## License

This project is licensed under the Northeastern University

## Acknowledgments

* Prof. Tejas Parikh who's initial code template was used
* TA Jiamin and Haozhong for guidance 
