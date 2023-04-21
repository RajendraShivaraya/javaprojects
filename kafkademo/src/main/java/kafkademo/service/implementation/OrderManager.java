package kafkademo.service.implementation;

import kafkademo.model.JoybuyEnums;
import kafkademo.model.Orders;
import kafkademo.repository.OrderRepository;
import kafkademo.service.IOrderManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderManager implements IOrderManagerService
{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    @Transactional
    public String createOrder(Orders orders)
    {
        try
        {
            Orders newOrders = orderRepository.save(orders);
            // Publish order to kafka
            return newOrders.orderId;
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }
    }

    @Override
    public JoybuyEnums.OrderStatus getOrderStatus(String orderId)
    {
        try
        {
            Query query = new Query();
            query.fields().include(Orders.Fields.orderId).include(Orders.Fields.orderStatus);
            Orders orders = mongoTemplate.findOne(query, Orders.class, "Orders");
            return orders.orderStatus;
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    @Override
    public Orders updateOrder(Orders orders) {
        return null;
    }

    @Override
    public void cancelOrder(String orderId)
    {

    }

    @Override
    public Orders getOrderById(String orderId)
    {
        Optional<Orders> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent())
            return optionalOrder.get();
        else
            return null;
    }

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Orders> getOrdersByCustomerId(String customerId, int numberOfRecords)
    {
        try
        {
            Query query = new Query();
            query.addCriteria(
                                Criteria
                                        .where(Orders.Fields.customerId)
                                        .is(customerId)
                             );
            query.limit(numberOfRecords);
            List<Orders> orders = mongoTemplate.find(query, Orders.class, "Orders");
            return orders;
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    @Override
    public List<Orders> getOrdersByDateRange(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<Orders> getOrdersByPhNumber(Integer phoneNum) {
        return null;
    }
}
