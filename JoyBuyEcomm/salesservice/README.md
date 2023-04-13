# Create App image
docker build --tag salesservices .

# Run APP image as container
docker run -d --name salesservices-container --network mysqlnet -p 8081:8091 salesservices