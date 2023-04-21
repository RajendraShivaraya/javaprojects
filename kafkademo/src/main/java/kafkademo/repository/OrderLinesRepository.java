package kafkademo.repository;

import kafkademo.model.OrderLines;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderLinesRepository extends MongoRepository<OrderLines, Long>
{
}
