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

cd updater 

./gradlew build

cd retriever 

./gradlew build

From the root directory:

	docker-compose build

	docker-compose up

Initial script should be picked up automatically in case it's not the changes can be done via

	docker exec -it mysql_container_name bash
  
	mysql -uroot -p < init.sql.  #with "password"
	
Wait for the containers to start, usually it takes a minute for mysql first time. Sequential runs will reuse the database created at first run.

## accessing endpoints
The services are mapped to 443 (retriever) and 8443 (updater) ports.

Sample calls are like:

https://localhost/retrieve/preference/by/?user=12345

https://localhost:8443/update/preference/?user=12345&sms=True&post=true&email=true

default credentials admin/admin will be required upon calling the endpoints. 

To call https endpoints from localhost sometimes changing in chrome browser are required like:

chrome://flags/#allow-insecure-localhost

### Swagger

Swagger is accessible via

https://localhost/swagger-ui/index.html

https://localhost:8443/swagger-ui/index.html

## Architectural decisions:

In controllers ResponseBody is not used to have better control of what is returned.

The exceptions are logged and not propagated as in high load systems this propagation is expensive (performance wise).

## K8s part

K8s yml files are located in k8s folder but it's only a template which should be adjusted to how cluster is set up. Providing only yml's for updater and receiver as usually mysql is used from helm or rds.
