# tarabut-demo

The project to update and retrieve preferences for the user

# description

Two different services updater and retriever connects to the same mysql database.

## Updater

The service with has "update/preference" endpoint with mandatory request parameter user which will take userIdentifier and optional parameters for sms, email and post which will be mapped to boolean value whether user has prefer this method of communication. Multiple choices are allowed. If some of these parameters are not provided the default value false will be taken.

The upadter uses INSERT strategy as to always keep track of the preference state user had in some point of time and at the same time avoid sql update.

The endpoint is secured with default credentials admin/admin and self signed certificate to expose only https protocol.

## Retriever

The service with has "retrieve/preference/by" endpoint with mandatory request parameter user which will take userIdentifier.
The service returns last known preferences for the user in JSON format or empty json if there are no preferences for this user or in case of checked exception.

The endpoint is secured with default credentials admin/admin and self signed certificate to expose only https protocol.

# Running application

From te root folder build updater and retriver project. 

./updater/gradlew build

./retriever/gradlew build

	docker-compose build

	docker-compose up

To run initial sql script:

	docker exec -it mysql_container_name bash
  
	mysql -uroot -p < init.sql.  #with "password"

## accessing endpoints
The services are mapped to 443 (retriever) and 8443 (updater) ports.

Sample calls are like:

https://localhost/retrieve/preference/by/?user=12345

https://localhost:8443/update/preference/?user=12345&sms=True&post=true&email=true

default credentials admin/admin will be required upon calling the endpoints. 

To call https endpoints from localhost sometimes changing in chrome browser are required like:

chrome://flags/#allow-insecure-localhost

Also sometimes it's required to accept self signed certificate
