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

curl -X POST http://yourhost/order-service/v1/order
  -d '{
  "customer": {
    "firtsName": "eo",
    "secondName": "sylm",
    "email": "email"
  },
  "phones": [
    {
      "id": "5c3dbf864465b4d16b7ed2ca",
      "name": "Samsung Galaxy A8+",
      "description": "Samsung Galaxy A8+ (Gold, 6GB RAM, 64GB Storage) with Offers",
      "photoLink": "https://images-na.ssl-images-amazon.com/images/I/41NgHRZne2L.jpg",
      "price": 555
    },
    {
      "id": "5c3dbf864465b4d16b7ed2cb",
      "name": "Nokia 6.1",
      "description": "Nokia 6.1 (Blue-Gold, 4GB RAM, 64GB Storage)",
      "photoLink": "https://images-na.ssl-images-amazon.com/images/I/31Nbg-oB52L.jpg",
      "price": 365
    },
    {
      "id": "5c3dbf864465b4d16b7ed2cc",
      "name": "Moto G6 Plus ",
      "description": "Moto G6 Plus (Indigo Black, 6+64 GB)",
      "photoLink": "https://images-na.ssl-images-amazon.com/images/I/31L9Lj9HBCL.jpg",
      "price": 445
    }
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


