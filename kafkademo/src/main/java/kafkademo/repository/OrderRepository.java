package kafkademo.repository;

import kafkademo.model.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface OrderRepository extends MongoRepository<Orders, String>
{
    @Query("{orderId :?0}") //SQL Equivalent : SELECT * FROM Order WHERE OrderId=?
    Optional<Orders> getOrderById(String orderId);
}
