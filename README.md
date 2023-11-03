# WexProject

## Add Transaction:
Route: POST /api/transactions/add
Description: This route allows you to add information about a purchase transaction. Transaction details, such as description, date, and purchase amount, are provided in the request body in JSON format. The transaction is recorded in the system, and a unique ID is generated.
Example of request body:

{
"description": "Example purchase",
"transactionDate": "2023-11-01",
"purchaseAmount": 100.00
}

Example of successful response:

Transaction added successfully. ID: 1

## Get Transaction by ID and Calculate Exchange Rate:
Route: GET /api/transactions/{id}/{currency}
Description: This route allows you to retrieve information about a specific purchase transaction based on its ID. Additionally, you can specify the desired currency (currency) to calculate the converted value of the purchase using the relevant exchange rate.
Example request:
GET /api/transactions/1/Dollars

Where {id} is the ID of the transaction you want to retrieve, and {currency} is the destination currency code for the conversion.
Example of a successful response:
Identifier: 1
Description: Example purchase
Transaction Date: 2023-11-01
Purchase Amount in Dollars: U$100.00
Conversion Rate: 2.00
Converted Amount: 200.00

In case of success, detailed transaction information, including the exchange rate and converted value, is returned in the response.

If the transaction is not found, the API will return a 404 status (Not Found).

## Configuration
The API is built based on the Spring Boot framework and uses Java. You need to configure the Java environment and project dependencies to run the API.

## Usage
Make sure the API is up and running, and then use the routes mentioned above to add transactions and calculate exchange rates for existing transactions.

## Database
A database was created in MySQL to store the information. To use the database for testing, follow the steps below:

CREATE SCHEMA wex_project;

CREATE TABLE `transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(50) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `amount` decimal(38,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

## POSTMAN

POST

curl --location 'http://localhost:8080/api/transactions/add' \
--header 'Content-Type: application/json' \
--data '{
    "description": "Minha transação",
    "transactionDate": "2023-11-01",
    "purchaseAmount": 100.0
}'

GET
curl --location 'http://localhost:8080/api/transactions/1/Dollar' \
--data ''
