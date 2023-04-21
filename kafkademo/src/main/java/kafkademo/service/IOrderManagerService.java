package kafkademo.service;

import kafkademo.model.JoybuyEnums;
import kafkademo.model.Orders;

import java.util.Date;
import java.util.List;

public interface IOrderManagerService
{
    // Create a new order
    public String createOrder(Orders orders);

    public JoybuyEnums.OrderStatus getOrderStatus(String orderId);
    // Update an existing order
    public Orders updateOrder(Orders orders);

    // Cancel an order
    public void cancelOrder(String orderId);

    // Get the details of an order by ID
    public Orders getOrderById(String orderId);

    // Get the list of all orders
    public List<Orders> getAllOrders();

    // Get the list of orders by customer ID
    public List<Orders> getOrdersByCustomerId(String customerId, int numberOfRecords);

    // Get the list of orders by date range
    public List<Orders> getOrdersByDateRange(Date startDate, Date endDate);

    public List<Orders> getOrdersByPhNumber(Integer phoneNum);
}
