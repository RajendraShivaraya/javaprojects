# Create docker image of payment service
docker build --tag paymentservices .

# Run the image as container
docker run -d --name paymentservices-container --network mysqlnet -p 8082:8092 paymentservices