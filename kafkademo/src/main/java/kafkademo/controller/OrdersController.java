package kafkademo.controller;

import kafkademo.model.APIResponseManager;
import kafkademo.model.JoybuyEnums;
import kafkademo.model.Orders;
import kafkademo.model.ResponseModel;
import kafkademo.service.implementation.OrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrdersController
{
    @Autowired
    OrderManager orderManager;
    @Autowired
    APIResponseManager responseManager;

    @GetMapping("/")
    public ResponseModel Home()
    {
        try
        {
            Object object = "<h1>Welcome to Joybuy - Kafka Demo</h1>";
            return responseManager.buildAPIResponse("Executed Successfully", object);
        }
        catch (Exception ex)
        {
            return responseManager.buildAPIException(ex.getMessage());
        }
    }
    @PostMapping("/createorder")
    public ResponseModel placeOrder(@RequestBody Orders orders)
    {
        try {
            Object object = orderManager.createOrder(orders);
            return responseManager.buildAPIResponse("Executed Successfully", object);
        }
        catch (Exception ex)
        {
            return responseManager.buildAPIException(ex.getMessage());
        }
    }

    @GetMapping("/orders/{OrderId}")
    public ResponseModel getOrderById(@PathVariable String OrderId)
    {
        try {
            Object object = orderManager.getOrderById(OrderId);
            return responseManager.buildAPIResponse("Executed Successfully", object);
        }catch (Exception ex)
        {
            return responseManager.buildAPIException(ex.getMessage());
        }
    }

    @GetMapping("/orderstatus/{OrderId}")
    public ResponseModel getOrderStatusById(@PathVariable String OrderId)
    {
        try
        {
            Object object = orderManager.getOrderStatus(OrderId);
            return responseManager.buildAPIResponse("Executed Successfully", object);
        }
        catch (Exception ex)
        {
            return responseManager.buildAPIException(ex.getMessage());
        }
    }
    @GetMapping("/custorders/{CustId}")
    public ResponseModel getOrderByCustomerId(@PathVariable String CustId)
    {
        try
        {
            Object object = orderManager.getOrdersByCustomerId(CustId, 10);
            return responseManager.buildAPIResponse("Executed Successfully", object);
        }
        catch (Exception ex)
        {
            return responseManager.buildAPIException(ex.getMessage());
        }
    }

    @PostMapping("/kafka/sendorder/{orderid}")
    public ResponseModel sendOrderToKafka(@PathVariable String orderid)
    {
        try
        {
            orderManager.sendKafkaMessage(orderid);
            Object object = "Order " + orderid + " sent to Kafka";
            return responseManager.buildAPIResponse("Executed Successfully", object);
        }
        catch (Exception ex)
        {
            return responseManager.buildAPIException(ex.getMessage());
        }
    }
}
