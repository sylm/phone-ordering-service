# Phone ordering service

Service stands for saving phones order. Which actually consists of list of phones and customer data.
Service is calling Phone catalog service to validate if correct data was passed in order.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Java 10

Gradle 5

Docker 1.8

### Installing

1. Clone this project
2. Go to service root folder
3. Run "gradle build docker" in your command line
4. Run "docker-compose build"
5. Run "docker-compose up"

## Running the tests
1. Call phone catalog service to get available phone

2. Copy some phones and paste to request below, overwrite "Paste phones here" (Validation checks if phones are available)

curl -X POST http://yourhost/order-service/v1/order
  -d '{
  "customer": {
    "firtsName": "eo",
    "secondName": "sylm",
    "email": "email"
  },
  "phones": [
    Paste phones here
  ]
}'


## Deployment

Spring actuator was configured so /health, /info etc are available for heartbeats check
e.g. http://your host/actuator/health

## Built With

* [Spring boot](https://spring.io/projects/spring-boot) - The web framework used
* [Gradle](https://gradle.org/) - Dependency Management


## Authors

* **Bohdan Biloskursky** - *Initial work* - (https://github.com/sylm)

## Acknowledgments

* Created to proof knowledge and skills, or unproof :)


