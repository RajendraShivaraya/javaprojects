# My Spring Boot Project

This is a Spring Boot project that demonstrates how to build a RESTful web service.
This is inventoryservices 

## Docker sequence

# Create volumes for daa & config
docker volume create mysql_data
docker volume create mysql_config
# Create network that used by MySQL & APPS to connect
docker network create mysqlnet

# Run the MySQL Image in network along with envrionment variables such as user id & passwords 
docker run -d --network mysqlnet --name mysqlserver-container -e MYSQL_USER=rajendra -e MYSQL_PASSWORD=1234 -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=joybuy -p 3306:3306 mysql:latest

# Create App image
docker build --tag inventoryservices .

# Run APP image as container
docker run -d --name inventoryservices-container --network mysqlnet -p 8080:8090 inventoryservices

# Test the Application, below URL is executed from local
# Local port 8080 is used which will connect to docker port 8090
http://localhost:8080/